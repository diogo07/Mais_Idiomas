package br.com.maisidiomas.view;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import br.com.maisidiomas.R;
import br.com.maisidiomas.controller.ControllerFase2;
import br.com.maisidiomas.controller.ControllerFase3;

public class Fase3Activity extends ModeloActivity {

    private TextView tvQuestao, tvNivel, tvPontosQuestao, tvPergunta, tvScore;
    private Button btProximo;
    private RadioGroup radioGroup;
    private RadioButton rbOpc1, rbOpc2, rbOpc3, rbOpc4;
    private AlertDialog alerta;
    private ProgressDialog progressDialog;
    private String login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fase_3);

        login = getIntent().getStringExtra("login");

        tvQuestao = findViewById(R.id.tv_questao_numero_fase3);
        tvNivel = findViewById(R.id.tv_nivel_fase3);
        tvPontosQuestao = findViewById(R.id.tv_pontos_questao_fase3);
        tvPergunta = findViewById(R.id.tv_pergunta_fase3);
        tvScore = findViewById(R.id.tv_score_fase3);
        btProximo = findViewById(R.id.bt_proximo_fase3);

        radioGroup = findViewById(R.id.radioGroup);
        rbOpc1 = findViewById(R.id.rb_opc1);
        rbOpc2 = findViewById(R.id.rb_opc2);
        rbOpc3 = findViewById(R.id.rb_opc3);
        rbOpc4 = findViewById(R.id.rb_opc4);

        tvQuestao.setTypeface(getFont());
        tvNivel.setTypeface(getFont());
        tvPontosQuestao.setTypeface(getFont());
        tvPergunta.setTypeface(getFont());
        tvScore.setTypeface(getFont());
        btProximo.setTypeface(getFont());
        rbOpc1.setTypeface(getFont());
        rbOpc2.setTypeface(getFont());
        rbOpc3.setTypeface(getFont());
        rbOpc4.setTypeface(getFont());

        new ControllerFase3(this);
    }


    public void exibirFase3(String mensagemInicio, String mensagemFim, boolean acertou, final ControllerFase3 controllerFase3) {

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
                controllerFase3.inserirQuestoes();

            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("");
        builder.setView(view);
        alerta = builder.create();
        alerta.show();
    }

    public void exibirMensagemGanhouJogo(){
        progressDialog = ProgressDialog.show(this, "", "Parabéns, você passou todas as fases!", true);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                progressDialog.setMessage("Voltando para tela de fases ...");
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        progressDialog.dismiss();
                        finish();
                    }
                }, 2000);

            }
        }, 2000);
    }

    public TextView getTvQuestao() {
        return tvQuestao;
    }

    public void setTvQuestao(TextView tvQuestao) {
        this.tvQuestao = tvQuestao;
    }

    public TextView getTvNivel() {
        return tvNivel;
    }

    public void setTvNivel(TextView tvNivel) {
        this.tvNivel = tvNivel;
    }

    public TextView getTvPontosQuestao() {
        return tvPontosQuestao;
    }

    public void setTvPontosQuestao(TextView tvPontosQuestao) {
        this.tvPontosQuestao = tvPontosQuestao;
    }

    public TextView getTvPergunta() {
        return tvPergunta;
    }

    public void setTvPergunta(TextView tvPergunta) {
        this.tvPergunta = tvPergunta;
    }

    public Button getBtProximo() {
        return btProximo;
    }

    public void setBtProximo(Button btProximo) {
        this.btProximo = btProximo;
    }

    public RadioButton getRbOpc1() {
        return rbOpc1;
    }

    public void setRbOpc1(RadioButton rbOpc1) {
        this.rbOpc1 = rbOpc1;
    }

    public RadioButton getRbOpc2() {
        return rbOpc2;
    }

    public void setRbOpc2(RadioButton rbOpc2) {
        this.rbOpc2 = rbOpc2;
    }

    public RadioButton getRbOpc3() {
        return rbOpc3;
    }

    public void setRbOpc3(RadioButton rbOpc3) {
        this.rbOpc3 = rbOpc3;
    }

    public RadioButton getRbOpc4() {
        return rbOpc4;
    }

    public void setRbOpc4(RadioButton rbOpc4) {
        this.rbOpc4 = rbOpc4;
    }

    public RadioGroup getRadioGroup() {
        return radioGroup;
    }

    public void setRadioGroup(RadioGroup radioGroup) {
        this.radioGroup = radioGroup;
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
