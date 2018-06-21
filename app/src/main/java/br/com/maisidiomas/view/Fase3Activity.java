package br.com.maisidiomas.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import br.com.maisidiomas.R;

public class Fase3Activity extends ModeloActivity {

    private TextView tvQuestao, tvNivel, tvPontosQuestao, tvPergunta, tvScore;
    private Button btProximo;
    private RadioButton rbOpc1, rbOpc2, rbOpc3, rbOpc4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fase_3);

        tvQuestao = findViewById(R.id.tv_questao_numero_fase3);
        tvNivel = findViewById(R.id.tv_nivel_fase3);
        tvPontosQuestao = findViewById(R.id.tv_pontos_questao_fase3);
        tvPergunta = findViewById(R.id.tv_pergunta_fase3);
        tvScore = findViewById(R.id.tv_score_fase3);
        btProximo = findViewById(R.id.bt_proximo_fase3);
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
}
