package br.com.maisidiomas.controller;

import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;

import br.com.maisidiomas.R;

import br.com.maisidiomas.model.dao.ConexaoSQLite;
import br.com.maisidiomas.model.dao.QuestaoNivel3DAOSQLite;
import br.com.maisidiomas.model.dao.UsuarioDAOSQLite;
import br.com.maisidiomas.model.vo.Fase3;
import br.com.maisidiomas.model.vo.QuestaoNivel3;
import br.com.maisidiomas.model.vo.Usuario;
import br.com.maisidiomas.view.Fase3Activity;

public class ControllerFase3 implements View.OnClickListener {

    private Fase3Activity fase3Activity;
    private ArrayList<QuestaoNivel3> questoes;
    private Fase3 fase3;
    private Usuario usuario;

    public ControllerFase3(Fase3Activity fase3Activity) {
        this.fase3Activity = fase3Activity;
        this.fase3Activity.getBtProximo().setOnClickListener(this);
        this.questoes = new QuestaoNivel3DAOSQLite(ConexaoSQLite.getInstance(this.fase3Activity)).listar();
        this.usuario = new UsuarioDAOSQLite(ConexaoSQLite.getInstance(this.fase3Activity)).findByLogin(this.fase3Activity.getLogin());
        if(questoes != null){
            fase3 = new Fase3(questoes);
            inserirQuestoes();
        }

    }

    public void inserirQuestoes() {
        if(fase3.getQuestaoAtual() <= 10){
            if(this.usuario != null){
                this.fase3Activity.getTvScore().setText("SCORE: "+usuario.getPontuacao());
            }
            this.fase3Activity.getTvQuestao().setText("QUESTÃO "+fase3.getQuestaoAtual());
            fase3Activity.getRbOpc1().setText(fase3.getQuestoes().get(fase3.getQuestaoAtual()-1).getPalavras()[0]);
            fase3Activity.getRbOpc2().setText(fase3.getQuestoes().get(fase3.getQuestaoAtual()-1).getPalavras()[1]);
            fase3Activity.getRbOpc3().setText(fase3.getQuestoes().get(fase3.getQuestaoAtual()-1).getPalavras()[2]);
            fase3Activity.getRbOpc4().setText(fase3.getQuestoes().get(fase3.getQuestaoAtual()-1).getPalavras()[3]);
        }else{
            this.fase3Activity.exibirMensagemGanhouJogo();
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.bt_proximo_fase3){
            verificarRespota();
        }
    }

    private void verificarRespota() {
        RadioButton radioButtonSelecionado = this.fase3Activity.findViewById(this.fase3Activity.getRadioGroup().getCheckedRadioButtonId());
        if(radioButtonSelecionado.getText().toString().equals(fase3.getQuestoes().get(fase3.getQuestaoAtual()-1).getPalavras()[fase3.getQuestoes().get(fase3.getQuestaoAtual()-1).getIndiceResposta()])){
           this.fase3Activity.exibirFase3("Parabéns, você acertou", "A palavra "+radioButtonSelecionado.getText().toString()+" não tem relação com as demais!", true, this);
           this.fase3.setQuestaoAtual(this.fase3.getQuestaoAtual()+1);
           if(this.usuario != null) {
               usuario.setPontuacao(usuario.getPontuacao() + 30);
               try {
                   new UsuarioDAOSQLite(ConexaoSQLite.getInstance(this.fase3Activity)).update(this.usuario);
               } catch (Exception e) {
                   e.printStackTrace();
               }
           }
        }else{
            this.fase3Activity.exibirFase3("Que pena, não foi dessa vez!", "A opção correta é "+fase3.getQuestoes().get(fase3.getQuestaoAtual()-1).getPalavras()[fase3.getQuestoes().get(fase3.getQuestaoAtual()-1).getIndiceResposta()], false, this);
            this.fase3.setQuestaoAtual(this.fase3.getQuestaoAtual()+1);
        }

    }

    public void iniciarQuestao() {
    }
}
