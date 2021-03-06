package br.com.maisidiomas.view;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v7.app.ActionBar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import br.com.maisidiomas.R;
import br.com.maisidiomas.controller.ControllerFase1;
import br.com.maisidiomas.controller.ControllerFase2;
import br.com.maisidiomas.utils.UtilsParametros;

public class FaseActivity extends ModeloActivity {

    private TextView tvQuestao, tvNivel, tvPontosQuestao, tvPergunta, tvPalavra, tvScore;
    private Button btProximo;
    private ImageView imgOpc1, imgOpc2, imgOpc3, imgOpc4;
    private String nivel, login;
    private AlertDialog alerta;
    private ProgressDialog progressDialog;
    private FloatingActionMenu floatingActionMenu;
    private static int VIBRATOR_VALUE = 1000;
    private Vibrator vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        vibrator = (Vibrator) getApplicationContext().getSystemService(this.VIBRATOR_SERVICE);

        nivel = getIntent().getStringExtra("nivel");
        login = getIntent().getStringExtra("login");

        setContentView(R.layout.activity_fase);


        tvQuestao = findViewById(R.id.tv_questao_numero);
        tvNivel = findViewById(R.id.tv_nivel);
        tvPontosQuestao = findViewById(R.id.tv_pontos_questao);
        tvPergunta = findViewById(R.id.tv_pergunta);
        tvPalavra = findViewById(R.id.tv_palavra);
        tvScore = findViewById(R.id.tv_score);
        btProximo = findViewById(R.id.bt_proximo);
        imgOpc1 = findViewById(R.id.img_opc1);
        imgOpc2 = findViewById(R.id.img_opc2);
        imgOpc3 = findViewById(R.id.img_opc3);
        imgOpc4 = findViewById(R.id.img_opc4);

        removerFoco();

        floatingActionMenu = findViewById(R.id.menu_yellow);

        floatingActionMenu.hideMenuButton(true);

        tvNivel.setText("Nível "+nivel);

        if(Integer.parseInt(nivel) == 1){
            tvPontosQuestao.setText("Pontos da questão: 10");
            new ControllerFase1(this);
        }

        if(Integer.parseInt(nivel) == 2){
            tvPontosQuestao.setText("Pontos da questão: 20");
            new ControllerFase2(this);
        }

        if(Integer.parseInt(nivel) == 3){
            tvPontosQuestao.setText("Pontos da questão: 30");
        }

        tvQuestao.setTypeface(getFont());
        tvNivel.setTypeface(getFont());
        tvPontosQuestao.setTypeface(getFont());
        tvPergunta.setTypeface(getFont());
        tvPalavra.setTypeface(getFont());
        tvScore.setTypeface(getFont());
        btProximo.setTypeface(getFont());


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return true;
    }

    public void vibrate() {
        vibrator.vibrate(VIBRATOR_VALUE);
    }

    public void removerFoco(){
        imgOpc1.setFocusable(false);
        imgOpc1.setBackgroundResource(R.drawable.estilo_borda);
        imgOpc2.setFocusable(false);
        imgOpc2.setBackgroundResource(R.drawable.estilo_borda);
        imgOpc3.setFocusable(false);
        imgOpc3.setBackgroundResource(R.drawable.estilo_borda);
        imgOpc4.setFocusable(false);
        imgOpc4.setBackgroundResource(R.drawable.estilo_borda);
    }

    public void inserirFocoImagemNivel(int nivel){
        switch (nivel){
            case 1:
                imgOpc1.setFocusable(true);
                imgOpc1.setBackgroundResource(R.drawable.estilo_opcao_questao);
                imgOpc2.setBackgroundResource(R.drawable.estilo_borda);
                imgOpc2.setFocusable(false);
                imgOpc3.setBackgroundResource(R.drawable.estilo_borda);
                imgOpc3.setFocusable(false);
                imgOpc4.setBackgroundResource(R.drawable.estilo_borda);
                imgOpc4.setFocusable(false);
                break;
            case 2:
                imgOpc1.setFocusable(false);
                imgOpc1.setBackgroundResource(R.drawable.estilo_borda);
                imgOpc2.setBackgroundResource(R.drawable.estilo_opcao_questao);
                imgOpc2.setFocusable(true);
                imgOpc3.setBackgroundResource(R.drawable.estilo_borda);
                imgOpc3.setFocusable(false);
                imgOpc4.setBackgroundResource(R.drawable.estilo_borda);
                imgOpc4.setFocusable(false);
                break;
            case 3:
                imgOpc1.setFocusable(false);
                imgOpc1.setBackgroundResource(R.drawable.estilo_borda);
                imgOpc2.setBackgroundResource(R.drawable.estilo_borda);
                imgOpc2.setFocusable(false);
                imgOpc3.setBackgroundResource(R.drawable.estilo_opcao_questao);
                imgOpc3.setFocusable(true);
                imgOpc4.setBackgroundResource(R.drawable.estilo_borda);
                imgOpc4.setFocusable(false);
                break;
            case 4:
                imgOpc1.setFocusable(false);
                imgOpc1.setBackgroundResource(R.drawable.estilo_borda);
                imgOpc2.setBackgroundResource(R.drawable.estilo_borda);
                imgOpc2.setFocusable(false);
                imgOpc3.setBackgroundResource(R.drawable.estilo_borda);
                imgOpc3.setFocusable(false);
                imgOpc4.setBackgroundResource(R.drawable.estilo_opcao_questao);
                imgOpc4.setFocusable(true);
                break;
        }
    }

    public int getAlternativaSelecionada(){
        if(imgOpc1.isFocusable()){
            return 0;
        }else if(imgOpc2.isFocusable()){
            return 1;
        }else if(imgOpc3.isFocusable()){
            return 2;
        }else if(imgOpc4.isFocusable()){
            return 3;
        }else{
            return 4;
        }
    }

    public void exibirMensagem(String mensagem){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);
        alertDialogBuilder.setTitle("");
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

    public void exibirMensagemCompraFase1(final ControllerFase1 controllerFase, final String ajuda){

        LayoutInflater li = getLayoutInflater();

        View view = li.inflate(R.layout.modelo_compra, null);
        TextView tvTextoCima, tvTextoBaixo;
        tvTextoCima = view.findViewById(R.id.tvPerg);
        tvTextoBaixo = view.findViewById(R.id.tvInform);
        tvTextoCima.setTypeface(getFont());
        tvTextoBaixo.setTypeface(getFont());
        Button btSim, btNao;
        btSim = view.findViewById(R.id.btConfirm);
        btNao = view.findViewById(R.id.btCancel);

        btSim.setTypeface(getFont());
        btNao.setTypeface(getFont());


        btSim.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                vibrate();

                Animation deslocamento = new TranslateAnimation(0, -1000, 0, 0);
                deslocamento.setDuration(1000);

                floatingActionMenu.setAnimation(deslocamento);

                FloatingActionButton fab1 = new FloatingActionButton(FaseActivity.this);
                fab1.setImageResource(R.drawable.ic_light);
                fab1.setId(controllerFase.getFase().getQuestaoAtual());
                fab1.setLabelText(ajuda);
                floatingActionMenu.addMenuButton(fab1);
                floatingActionMenu.showMenuButton(true);
                controllerFase.getUsuario().setPontuacao(controllerFase.getUsuario().getPontuacao()-8);
                alerta.dismiss();
                exibirMensagem("Você pode visualizar a tradução no botão laranja abaixo.");

            }
        });

        btNao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alerta.dismiss();
            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("");
        builder.setCancelable(false);
        builder.setView(view);
        alerta = builder.create();
        alerta.show();

    }

    public void exibirMensagemCompraFase2(final ControllerFase2 controllerFase, final String ajuda){

        LayoutInflater li = getLayoutInflater();

        View view = li.inflate(R.layout.modelo_compra, null);
        TextView tvTextoCima, tvTextoBaixo;
        tvTextoCima = view.findViewById(R.id.tvPerg);
        tvTextoBaixo = view.findViewById(R.id.tvInform);
        tvTextoCima.setTypeface(getFont());
        tvTextoBaixo.setTypeface(getFont());
        Button btSim, btNao;
        btSim = view.findViewById(R.id.btConfirm);
        btNao = view.findViewById(R.id.btCancel);

        btSim.setTypeface(getFont());
        btNao.setTypeface(getFont());


        btSim.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                vibrate();

                Animation deslocamento = new TranslateAnimation(0, -1000, 0, 0);
                deslocamento.setDuration(1000);

                floatingActionMenu.setAnimation(deslocamento);

                FloatingActionButton fab1 = new FloatingActionButton(FaseActivity.this);
                fab1.setImageResource(R.drawable.ic_light);
                fab1.setId(controllerFase.getFase().getQuestaoAtual());
                fab1.setLabelText(ajuda);
                floatingActionMenu.addMenuButton(fab1);
                floatingActionMenu.showMenuButton(true);
                controllerFase.getUsuario().setPontuacao(controllerFase.getUsuario().getPontuacao()-8);
                getTvScore().setText("Score: "+controllerFase.getUsuario().getPontuacao());
                alerta.dismiss();

            }
        });

        btNao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alerta.dismiss();
            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("");
        builder.setCancelable(false);
        builder.setView(view);
        alerta = builder.create();
        alerta.show();

    }


    public void exibirMensagemFase1(String mensagemInicio, String mensagemFim, boolean acertou, final ControllerFase1 controllerFase) {

        /*LayoutInflater li = getLayoutInflater();

        View view = li.inflate(R.layout.alerta, null);
        TextView tvTextoCima, tvTextoBaixo;
        ImageView img = view.findViewById(R.id.img_alerta);
        tvTextoCima = view.findViewById(R.id.tvTextoCima);
        tvTextoBaixo = view.findViewById(R.id.tvTextoBaixo);
        tvTextoCima.setTypeface(getFont());
        tvTextoBaixo.setTypeface(getFont());
        Button btProx = view.findViewById(R.id.bt_prox);
        btProx.setTypeface(getFont());
*/
            Toast toast = Toast.makeText(this, mensagemInicio +"\n"+mensagemFim, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL, 0, 0);
            toast.show();
            removerFoco();
            getTvScore().setText("Score: "+ UtilsParametros.getUsuarioLogado().getPontuacao());
            controllerFase.iniciarQuestao();

        /*
        tvTextoCima.setText(mensagemInicio);
        tvTextoBaixo.setText(mensagemFim);

        btProx.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                alerta.dismiss();
                controllerFase.iniciarQuestao();

            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("");
        builder.setCancelable(false);
        builder.setView(view);
        alerta = builder.create();
        alerta.show();

        */
    }

    public void exibirMensagemFase2(String mensagemInicio, String mensagemFim, boolean acertou, final ControllerFase2 controllerFase) {

       /* LayoutInflater li = getLayoutInflater();

        View view = li.inflate(R.layout.alerta, null);
        TextView tvTextoCima, tvTextoBaixo;
        ImageView img = view.findViewById(R.id.img_alerta);
        tvTextoCima = view.findViewById(R.id.tvTextoCima);
        tvTextoBaixo = view.findViewById(R.id.tvTextoBaixo);
        tvTextoCima.setTypeface(getFont());
        tvTextoBaixo.setTypeface(getFont());
        Button btProx = view.findViewById(R.id.bt_prox);
        btProx.setTypeface(getFont());

        if(acertou){
            img.setImageResource(R.mipmap.ic_acertou);
        }else{
            img.setImageResource(R.mipmap.ic_errou);
        }

        tvTextoCima.setText(mensagemInicio);
        tvTextoBaixo.setText(mensagemFim);

        btProx.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                alerta.dismiss();
                controllerFase.iniciarQuestao();

            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("");
        builder.setView(view);
        builder.setCancelable(false);
        alerta = builder.create();
        alerta.show();

        */

        Toast toast = Toast.makeText(this, mensagemInicio, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL, 0, 0);
        toast.show();
        removerFoco();
        getTvScore().setText("Score: "+ UtilsParametros.getUsuarioLogado().getPontuacao());
        controllerFase.iniciarQuestao();
    }

    public void exibirMensagemUltimaQuestao(String mensagemInicio, String mensagemFim, boolean acertou) {

        LayoutInflater li = getLayoutInflater();

        View view = li.inflate(R.layout.alerta, null);
        TextView tvTextoCima, tvTextoBaixo;
        ImageView img = view.findViewById(R.id.img_alerta);
        tvTextoCima = view.findViewById(R.id.tvTextoCima);
        tvTextoBaixo = view.findViewById(R.id.tvTextoBaixo);
        tvTextoCima.setTypeface(getFont());
        tvTextoBaixo.setTypeface(getFont());
        Button btProx = view.findViewById(R.id.bt_prox);
        btProx.setTypeface(getFont());

        if(acertou){
            img.setImageResource(R.mipmap.ic_acertou);
        }else{
            img.setImageResource(R.mipmap.ic_errou);
        }

        tvTextoCima.setText(mensagemInicio);
        tvTextoBaixo.setText(mensagemFim);

        btProx.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                alerta.dismiss();
                progressDialog = ProgressDialog.show(FaseActivity.this, "", "Carregando ...", true);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                            progressDialog.setMessage("Iniciando nova fase...");
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                public void run() {
                                    progressDialog.dismiss();
                                    if(nivel.equals("1")){
                                        Intent i = new Intent(FaseActivity.this, FaseActivity.class);
                                        finish();
                                        i.putExtra("nivel", ""+2);
                                        i.putExtra("login", ""+login);
                                        startActivity(i);
                                    }else{
                                        Intent i = new Intent(FaseActivity.this, Fase3Activity.class);
                                        finish();
                                        i.putExtra("nivel", ""+3);
                                        i.putExtra("login", ""+login);
                                        startActivity(i);
                                    }
                                }
                            }, 2000);

                    }
                }, 2000);

            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("");
        builder.setCancelable(false);
        builder.setView(view);
        alerta = builder.create();
        alerta.show();
    }

    public int idImagemNivel1(String endImg) {

        if (endImg.equals("ic_abacate")) {
            return R.mipmap.ic_abacate;
        }

        if (endImg.equals("ic_abacaxi")) {
            return R.mipmap.ic_abacaxi;
        }

        if (endImg.equals("ic_abelha")) {
            return R.mipmap.ic_abelha;
        }

        if (endImg.equals("ic_arvore")) {
            return R.mipmap.ic_arvore;
        }

        if (endImg.equals("ic_aviao")) {
            return R.mipmap.ic_aviao;
        }

        if (endImg.equals("ic_barata")) {
            return R.mipmap.ic_barata;
        }

        if (endImg.equals("ic_barco")) {
            return R.mipmap.ic_barco;
        }

        if (endImg.equals("ic_besouro")) {
            return R.mipmap.ic_besouro;
        }

        if (endImg.equals("ic_boca")) {
            return R.mipmap.ic_boca;
        }

        if (endImg.equals("ic_bola")) {
            return R.mipmap.ic_bola;
        }

        if (endImg.equals("ic_bolsa")) {
            return R.mipmap.ic_bolsa;
        }

        if (endImg.equals("ic_bone")) {
            return R.mipmap.ic_bone;
        }

        if (endImg.equals("ic_borracha")) {
            return R.mipmap.ic_borracha;
        }

        if (endImg.equals("ic_burro")) {
            return R.mipmap.ic_burro;
        }

        if (endImg.equals("ic_cadeira")) {
            return R.mipmap.ic_cadeira;
        }

        if (endImg.equals("ic_caixa")) {
            return R.mipmap.ic_caixa;
        }

        if (endImg.equals("ic_cama")) {
            return R.mipmap.ic_cama;
        }

        if (endImg.equals("ic_camisa")) {
            return R.mipmap.ic_camiseta;
        }

        if (endImg.equals("ic_carro")) {
            return R.mipmap.ic_carro;
        }

        if (endImg.equals("ic_carteira")) {
            return R.mipmap.ic_carteira;
        }

        if (endImg.equals("ic_casa")) {
            return R.mipmap.ic_casa;
        }

        if (endImg.equals("ic_casaco")) {
            return R.mipmap.ic_casaco;
        }

        if (endImg.equals("ic_celular")) {
            return R.mipmap.ic_celular;
        }

        if (endImg.equals("ic_cenoura")) {
            return R.mipmap.ic_cenoura;
        }

        if (endImg.equals("ic_cerveja")) {
            return R.mipmap.ic_cerveja;
        }

        if (endImg.equals("ic_cesta_de_lixo")) {
            return R.mipmap.ic_cesta_de_lixo;
        }

        if (endImg.equals("ic_chapeu")) {
            return R.mipmap.ic_chapeu;
        }

        if (endImg.equals("ic_chave")) {
            return R.mipmap.ic_chave;
        }

        if (endImg.equals("ic_colher")) {
            return R.mipmap.ic_colher;
        }

        if (endImg.equals("ic_copo")) {
            return R.mipmap.ic_copo;
        }

        if (endImg.equals("ic_coracao")) {
            return R.mipmap.ic_coracao;
        }

        if (endImg.equals("ic_dedo")) {
            return R.mipmap.ic_dedo;
        }

        if (endImg.equals("ic_espelho")) {
            return R.mipmap.ic_espelho;
        }

        if (endImg.equals("ic_faca")) {
            return R.mipmap.ic_faca;
        }

        if (endImg.equals("ic_frango")) {
            return R.mipmap.ic_frango;
        }

        if (endImg.equals("ic_galo")) {
            return R.mipmap.ic_galo;
        }

        if (endImg.equals("ic_garfo")) {
            return R.mipmap.ic_garfo;
        }

        if (endImg.equals("ic_garrafa")) {
            return R.mipmap.ic_garrafa;
        }

        if (endImg.equals("ic_goiaba")) {
            return R.mipmap.ic_goiaba;
        }

        if (endImg.equals("ic_guarda-chuva")) {
            return R.mipmap.ic_guarda_chuva;
        }

        if (endImg.equals("ic_helicoptero")) {
            return R.mipmap.ic_helicoptero;
        }

        if (endImg.equals("ic_janela")) {
            return R.mipmap.ic_janela;
        }

        if (endImg.equals("ic_lapis_de_cera")) {
            return R.mipmap.ic_lapis_de_cera;
        }

        if (endImg.equals("ic_lua")) {
            return R.mipmap.ic_lua;
        }

        if (endImg.equals("ic_mao")) {
            return R.mipmap.ic_mao;
        }

        if (endImg.equals("ic_meias")) {
            return R.mipmap.ic_meias;
        }

        if (endImg.equals("ic_melancia")) {
            return R.mipmap.ic_melancia;
        }

        if (endImg.equals("ic_mochila")) {
            return R.mipmap.ic_mochila;
        }

        if (endImg.equals("ic_morango")) {
            return R.mipmap.ic_morango;
        }

        if (endImg.equals("ic_nariz")) {
            return R.mipmap.ic_nariz;
        }

        if (endImg.equals("ic_navio")) {
            return R.mipmap.ic_navio;
        }

        if (endImg.equals("ic_oculos")) {
            return R.mipmap.ic_oculos;
        }

        if (endImg.equals("ic_olho")) {
            return R.mipmap.ic_olho;
        }

        if (endImg.equals("ic_orelha")) {
            return R.mipmap.ic_orelha;
        }

        if (endImg.equals("ic_ovelha")) {
            return R.mipmap.ic_ovelha;
        }

        if (endImg.equals("ic_ovo")) {
            return R.mipmap.ic_ovo;
        }

        if (endImg.equals("ic_pato")) {
            return R.mipmap.ic_pato;
        }

        if (endImg.equals("ic_pe")) {
            return R.mipmap.ic_pe;
        }

        if (endImg.equals("ic_peixe")) {
            return R.mipmap.ic_peixe;
        }

        if (endImg.equals("ic_pera")) {
            return R.mipmap.ic_pera;
        }

        if (endImg.equals("ic_porta")) {
            return R.mipmap.ic_porta;
        }

        if (endImg.equals("ic_prato")) {
            return R.mipmap.ic_prato;
        }

        if (endImg.equals("ic_raiz")) {
            return R.mipmap.ic_raiz;
        }

        if (endImg.equals("ic_regua")) {
            return R.mipmap.ic_regua;
        }

        if (endImg.equals("ic_relogio_de_parede")) {
            return R.mipmap.ic_relogio_de_parede;
        }

        if (endImg.equals("ic_relogio_de_pulso")) {
            return R.mipmap.ic_relogio_de_pulso;
        }

        if (endImg.equals("ic_saia")) {
            return R.mipmap.ic_saia;
        }

        if (endImg.equals("ic_sapato")) {
            return R.mipmap.ic_sapato;
        }

        if (endImg.equals("ic_sapo")) {
            return R.mipmap.ic_sapo;
        }

        if (endImg.equals("ic_sol")) {
            return R.mipmap.ic_sol;
        }

        if (endImg.equals("ic_telefone")) {
            return R.mipmap.ic_telefone;
        }

        if (endImg.equals("ic_tenis")) {
            return R.mipmap.ic_tenis;
        }

        if (endImg.equals("ic_tres")) {
            return R.mipmap.ic_tres;
        }

        if (endImg.equals("ic_uva")) {
            return R.mipmap.ic_uva;
        }

        if (endImg.equals("ic_vaca")) {
            return R.mipmap.ic_vaca;
        }

        if (endImg.equals("ic_vassoura")) {
            return R.mipmap.ic_vassoura;
        }

        if(endImg.equals("ic_vestido")){
            return R.mipmap.ic_vestido;
        }

        else {
            return 0;
        }
    }


    public int idImagemNivel2(String endImg) {
        if (endImg.equals("ic_open_the_door")) {
            return R.mipmap.ic_nivel2_open_the_door;
        }
        if (endImg.equals("ic_push_the_table")) {
            return R.mipmap.ic_nivel2_push_the_table;
        }
        if (endImg.equals("ic_pull_the_table")) {
            return R.mipmap.ic_nivel2_pull_the_table;
        }
        if (endImg.equals("ic_kick_the_ball")) {
            return R.mipmap.ic_nivel2_kick_the_ball;
        }
        if (endImg.equals("ic_open_the_book")) {
            return R.mipmap.ic_nivel2_open_the_book;
        }
        if (endImg.equals("ic_open_the_mind")) {
            return R.mipmap.ic_nivel2_open_the_mind;
        }
        if (endImg.equals("ic_open_the_window")) {
            return R.mipmap.ic_nivel2_open_the_window;
        }
        if (endImg.equals("ic_the_woman_is_cooking")) {
            return R.mipmap.ic_nivel2_the_woman_is_cooking;
        }
        if (endImg.equals("ic_writing_on_the_sheet")) {
            return R.mipmap.ic_nivel2_writing_on_the_sheet;
        }
        if (endImg.equals("ic_hit_the_target")) {
            return R.mipmap.ic_nivel2_hit_the_target;
        }
        if (endImg.equals("ic_dance_the_music")) {
            return R.mipmap.ic_nivel2_dance_the_music;
        }
        if (endImg.equals("ic_i_am_swimming")) {
            return R.mipmap.ic_nivel2_i_am_swimming;
        }
        if (endImg.equals("ic_i_am_running")) {
            return R.mipmap.ic_nivel2_i_am_running;
        }
        if (endImg.equals("ic_turn_on_the_light")) {
            return R.mipmap.ic_nivel2_turn_on_the_light;
        }
        if (endImg.equals("ic_take_care_of_the_tree")) {
            return R.mipmap.ic_nivel2_take_care_of_the_tree;
        }
        if (endImg.equals("ic_take_a_bath")) {
            return R.mipmap.ic_nivel2_take_a_bath;
        }
        if (endImg.equals("ic_climb_the_ladder")) {
            return R.mipmap.ic_nivel2_climb_the_ladder;
        }
        if (endImg.equals("ic_drive_the_car")) {
            return R.mipmap.ic_nivel2_drive_the_car;
        }
        if (endImg.equals("ic_press_the_ringer")) {
            return R.mipmap.ic_nivel2_press_the_ringer;
        }
        if (endImg.equals("ic_drink_water")) {
            return R.mipmap.ic_nivel2_drink_water;
        }
        if (endImg.equals("ic_raise_the_hand")) {
            return R.mipmap.ic_nivel2_raise_the_hand;
        }
        if (endImg.equals("ic_wash_the_dishes")) {
            return R.mipmap.ic_nivel2_wash_the_dishes;
        }
        if (endImg.equals("ic_watch_tv")) {
            return R.mipmap.ic_nivel2_watch_tv;
        }
        if (endImg.equals("ic_drink_beer")) {
            return R.mipmap.ic_nivel2_drink_beer;
        }
        if (endImg.equals("ic_think")) {
            return R.mipmap.ic_nivel2_think;
        }
        else{
            return R.mipmap.ic_nivel2_put_sugar_in_the_coffee;
        }

    }

    public TextView getTvQuestao() {
        return tvQuestao;
    }

    public TextView getTvNivel() {
        return tvNivel;
    }

    public TextView getTvPontosQuestao() {
        return tvPontosQuestao;
    }

    public TextView getTvPergunta() {
        return tvPergunta;
    }

    public TextView getTvPalavra() {
        return tvPalavra;
    }

    public Button getBtProximo() {
        return btProximo;
    }

    public ImageView getImgOpc1() {
        return imgOpc1;
    }

    public ImageView getImgOpc2() {
        return imgOpc2;
    }

    public ImageView getImgOpc3() {
        return imgOpc3;
    }

    public ImageView getImgOpc4() {
        return imgOpc4;
    }

    public TextView getTvScore() {
        return tvScore;
    }

    public void setTvScore(TextView tvScore) {
        this.tvScore = tvScore;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public FloatingActionMenu getFloatingActionMenu() {
        return floatingActionMenu;
    }

    public void setFloatingActionMenu(FloatingActionMenu floatingActionMenu) {
        this.floatingActionMenu = floatingActionMenu;
    }
}
