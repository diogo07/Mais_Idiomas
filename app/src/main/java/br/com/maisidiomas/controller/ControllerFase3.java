package br.com.maisidiomas.controller;

import android.view.View;
import android.widget.RadioButton;

import java.util.ArrayList;

import br.com.maisidiomas.R;

import br.com.maisidiomas.model.dao.sqlite.ConexaoSQLite;
import br.com.maisidiomas.model.dao.Fachada;
import br.com.maisidiomas.model.dao.sqlite.QuestaoNivel3DAOSQLite;
import br.com.maisidiomas.model.vo.Fase3;
import br.com.maisidiomas.model.vo.QuestaoNivel3;
import br.com.maisidiomas.model.vo.Usuario;
import br.com.maisidiomas.utils.UtilsParametros;
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
        try {
            this.usuario = Fachada.findByLogin(fase3Activity, fase3Activity.getLogin());
        } catch (Exception e) {
            e.printStackTrace();
        }
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
               fase3Activity.getTvScore().setText("SCORE: "+usuario.getPontuacao());
               try {
                   Fachada.atualizarUsuario(fase3Activity, usuario);
                   UtilsParametros.carregarUsuario(usuario);
               } catch (Exception e) {
                   fase3Activity.exibirMensagem("Problemas na atualização da pontuação");
               }
           }
        }else{
            this.fase3Activity.exibirFase3("Que pena, não foi dessa vez!", "A opção correta é "+fase3.getQuestoes().get(fase3.getQuestaoAtual()-1).getPalavras()[fase3.getQuestoes().get(fase3.getQuestaoAtual()-1).getIndiceResposta()], false, this);
            this.fase3.setQuestaoAtual(this.fase3.getQuestaoAtual()+1);
        }

    }
}
