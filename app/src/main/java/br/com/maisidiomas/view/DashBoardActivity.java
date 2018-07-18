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
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import br.com.maisidiomas.R;
import br.com.maisidiomas.controller.ControllerDashBoard;
import br.com.maisidiomas.utils.UtilsParametros;

public class DashBoardActivity extends ModeloActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private String nome, login, avatar, pontuacao;
    private ControllerDashBoard controllerDashBoard;
    private int id;
    private ImageView imgAvatar;
    private TextView tvScore;


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
        controllerDashBoard = new ControllerDashBoard(this, id);

        TextView novaFonte = (TextView) findViewById(R.id.tv_ranking);
        novaFonte.setTypeface(getFont());


        View headerView = navigationView.getHeaderView(0);

        TextView tvNome = headerView.findViewById(R.id.tv_nome);
        tvNome.setText(nome);
        tvNome.setTypeface(getFont());

        TextView tvLogin = headerView.findViewById(R.id.tv_login);
        tvLogin.setText(login);
        tvLogin.setTypeface(getFont());

        tvScore = findViewById(R.id.tvScore);
        tvScore.setText("SCORE: "+pontuacao);
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
            startActivity(i);
        } else if (id == R.id.nav_add_quest) {
            Intent i = new Intent(DashBoardActivity.this, InserirQuestaoActivity.class);
            startActivity(i);
        }else if (id == R.id.nav_sair) {
            Intent i = new Intent(DashBoardActivity.this, LoginActivity.class);
            startActivity(i);
            finish();
        } else if (id == R.id.nav_exemplo) {

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
