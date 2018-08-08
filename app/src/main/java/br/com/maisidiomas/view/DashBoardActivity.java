package br.com.maisidiomas.view;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.internal.NavigationMenuItemView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import br.com.maisidiomas.R;
import br.com.maisidiomas.controller.ControllerDashBoard;
import br.com.maisidiomas.model.dao.Fachada;
import br.com.maisidiomas.model.vo.Usuario;
import br.com.maisidiomas.utils.FirebaseConecty;
import br.com.maisidiomas.utils.UtilsParametros;

public class DashBoardActivity extends ModeloActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private String nome, login, avatar, pontuacao;
    private ControllerDashBoard controllerDashBoard;
    private int id;
    private int posicaoAtual;
    private ArrayList<String> info = new ArrayList<>();
    private ImageView imgAvatar;
    private TextView tvScore, tvNome, tvLogin;
    private AlertDialog alerta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        nome = getIntent().getStringExtra("nome");
        login = getIntent().getStringExtra("login");
        avatar = getIntent().getStringExtra("avatar");
        pontuacao = getIntent().getStringExtra("pontuacao");
        String i = getIntent().getStringExtra("id").toString();
        id = Integer.parseInt(i);


        setContentView(R.layout.activity_dash_board);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        controllerDashBoard = new ControllerDashBoard(this);

        TextView novaFonte = (TextView) findViewById(R.id.tv_ranking);
        novaFonte.setTypeface(getFont());


        View headerView = navigationView.getHeaderView(0);

        tvNome = headerView.findViewById(R.id.tv_nome);
        tvNome.setText(nome);
        tvNome.setTypeface(getFont());

        tvLogin = headerView.findViewById(R.id.tv_login);
        tvLogin.setText(login);
        tvLogin.setTypeface(getFont());

        tvScore = findViewById(R.id.tvScore);
        tvScore.setText("SEU SCORE: "+pontuacao);
        tvScore.setTypeface(getFont());

        imgAvatar = headerView.findViewById(R.id.imageView);


        alterarAvatar();
    }

    @Override
    public void onResume(){
        super.onResume();
    }

    private void alterarAvatar() {
        if(avatar != null && imgAvatar != null){
            switch (avatar){
                case "avatar1":
                    imgAvatar.setImageResource(R.mipmap.ic_avatar1);
                    break;
                case "avatar2":
                    imgAvatar.setImageResource(R.mipmap.ic_avatar2);
                    break;
                case "avatar3":
                    imgAvatar.setImageResource(R.mipmap.ic_avatar3);
                    break;
                case "avatar4":
                    imgAvatar.setImageResource(R.mipmap.ic_avatar4);
                    break;
                case "avatar5":
                    imgAvatar.setImageResource(R.mipmap.ic_avatar5);
                    break;
                case "avatar6":
                    imgAvatar.setImageResource(R.mipmap.ic_avatar6);
                    break;
                case "avatar7":
                    imgAvatar.setImageResource(R.mipmap.ic_avatar7);
                    break;
                case "avatar8":
                    imgAvatar.setImageResource(R.mipmap.ic_avatar8);
                    break;
            }
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_jogar) {
            Intent i = new Intent(DashBoardActivity.this, LevelActivity.class);
            i.putExtra("id", ""+id);
            i.putExtra("login", ""+login);
            startActivity(i);
        } else if (id == R.id.nav_sobre) {
            Intent i = new Intent(DashBoardActivity.this, SobreActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_conf) {
            Intent i = new Intent(DashBoardActivity.this, ConfiguracoesActivity.class);
            i.putExtra("avatar", avatar);
            i.putExtra("login", login);
            startActivity(i);
        } else if (id == R.id.nav_creditos) {
            Intent i = new Intent(DashBoardActivity.this, CreditosActivity.class);
            startActivity(i);
        }else if (id == R.id.nav_sair) {
            finish();
        } else if (id == R.id.nav_exemplo) {
            Intent intent = new Intent(this, AjudaActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_dicionario) {
            Intent intent = new Intent(this, DicionarioActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        tvScore.setText("SEU SCORE: "+UtilsParametros.getUsuarioLogado().getPontuacao());
        avatar = UtilsParametros.getUsuarioLogado().getFoto();
        alterarAvatar();
        tvNome.setText(UtilsParametros.getUsuarioLogado().getNome());
        tvLogin.setText(UtilsParametros.getUsuarioLogado().getLogin());
        FirebaseConecty.salvar(UtilsParametros.getUsuarioLogado());
        controllerDashBoard.atualizarPontuacao();
        controllerDashBoard.inserirRanking();
    }

    public void exibirMensagem(String mensagem){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);
        alertDialogBuilder.setTitle("Mensagem");
        alertDialogBuilder
                .setMessage(mensagem)
                .setCancelable(false)
                .setPositiveButton("Ok",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }


    public void exibirMensagemInicio(){
        if(UtilsParametros.getUsuarioLogado().getAjuda().equals("sim")){
            posicaoAtual = 0;

            info.add("Bem-vindo ao jogo Mais Idiomas. Este é um jogo de perguntas de inglês, onde você vai relacionar palavras com imagens!");
            info.add("Para jogar, você deve tocar no menu acima e escolher a opção jogar!");
            info.add("Para consultar informações de traduções, você deve tocar no menu acima e escolher a opção dicionário!");
            info.add("Para saber mais informações, você deve tocar no menu acima e escolher a opção ajuda!");

            LayoutInflater li = getLayoutInflater();

            View view = li.inflate(R.layout.modelo_informacoes, null);

            final TextView tvTitle, tvInfo;
            tvTitle = view.findViewById(R.id.tvTitle);
            tvInfo = view.findViewById(R.id.tvinfo1);
            tvTitle.setTypeface(getFont());
            tvInfo.setTypeface(getFont());

            tvInfo.setText(info.get(0));

            final Button btIr, btVoltar;
            btIr = view.findViewById(R.id.btSeguir);
            btVoltar = view.findViewById(R.id.btAnterior);

            btIr.setTypeface(getFont());
            btVoltar.setTypeface(getFont());

            final CheckBox checkBox;
            checkBox = view.findViewById(R.id.checkBox);
            checkBox.setTypeface(getFont());


            btIr.setOnClickListener(new View.OnClickListener() {
                public void onClick(View arg0) {
                    if(posicaoAtual < 2){
                        btVoltar.setEnabled(true);
                        posicaoAtual++;
                        tvInfo.setText(info.get(posicaoAtual));
                        return;
                    }
                    if(posicaoAtual == 2){
                        posicaoAtual++;
                        tvInfo.setText(info.get(posicaoAtual));
                        btIr.setText("Fechar");
                        return;
                    }
                    if(posicaoAtual == 3){
                        if(checkBox.isChecked()){
                            Usuario u = UtilsParametros.getUsuarioLogado();
                            u.setAjuda("nao");
                            try {
                                Fachada.atualizarUsuario(DashBoardActivity.this, u);
                                FirebaseConecty.salvar(u);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        alerta.dismiss();
                    }
                }
            });

            btVoltar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(posicaoAtual > 1){
                        posicaoAtual--;
                        tvInfo.setText(info.get(posicaoAtual));
                        btIr.setText("Próximo");
                        return;
                    }
                    if(posicaoAtual == 1){
                        posicaoAtual--;
                        tvInfo.setText(info.get(posicaoAtual));
                        btVoltar.setEnabled(false);
                        return;
                    }
                }
            });

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("");
            builder.setCancelable(false);
            builder.setView(view);
            alerta = builder.create();
            alerta.show();
        }
    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public TextView getTvScore() {
        return tvScore;
    }

    public void setTvScore(TextView tvScore) {
        this.tvScore = tvScore;
    }
}
