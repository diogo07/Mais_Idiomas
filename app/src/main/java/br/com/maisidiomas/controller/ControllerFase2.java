package br.com.maisidiomas.controller;

import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import br.com.maisidiomas.R;
import br.com.maisidiomas.model.dao.ConexaoSQLite;
import br.com.maisidiomas.model.dao.Fachada;
import br.com.maisidiomas.model.dao.PalavraDAOSQLite;
import br.com.maisidiomas.model.dao.UsuarioDAOSQLite;
import br.com.maisidiomas.model.vo.Fase;
import br.com.maisidiomas.model.vo.Palavra;
import br.com.maisidiomas.model.vo.Usuario;
import br.com.maisidiomas.view.FaseActivity;

public class ControllerFase2 implements View.OnClickListener{

    private FaseActivity faseActivity;
    private Usuario usuario;
    private Fase fase;

    public ControllerFase2(FaseActivity faseActivity) {
        this.faseActivity = faseActivity;
        try {
            this.usuario = Fachada.findByLogin(faseActivity, faseActivity.getLogin());
        } catch (Exception e) {
            faseActivity.exibirMensagem("O sistema encontrou problemas ao iniciar a fase");
        }
        ArrayList<Palavra> p = null;
        try {
            p = new PalavraDAOSQLite(ConexaoSQLite.getInstance(this.faseActivity)).listByLevel(2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(p == null){
           this.faseActivity.exibirMensagem("Não foi possível localizar as questões!");
       }else{
           this.fase = new Fase(usuario.getLogin(),0,p);
           this.fase.gerarQuestoesNivel2();
           iniciarQuestao();
       }
       this.faseActivity.getImgOpc1().setOnClickListener(this);
       this.faseActivity.getImgOpc2().setOnClickListener(this);
       this.faseActivity.getImgOpc3().setOnClickListener(this);
       this.faseActivity.getImgOpc4().setOnClickListener(this);
       this.faseActivity.getBtProximo().setOnClickListener(this);
    }

    public void iniciarQuestao() {
        faseActivity.getImgOpc1().setImageResource(faseActivity.idImagemNivel2(fase.getQuestoes().get(fase.getQuestaoAtual()).getPalavras()[0].getNomeSemAcento()));
        faseActivity.getImgOpc2().setImageResource(faseActivity.idImagemNivel2(fase.getQuestoes().get(fase.getQuestaoAtual()).getPalavras()[1].getNomeSemAcento()));
        faseActivity.getImgOpc3().setImageResource(faseActivity.idImagemNivel2(fase.getQuestoes().get(fase.getQuestaoAtual()).getPalavras()[2].getNomeSemAcento()));
        faseActivity.getImgOpc4().setImageResource(faseActivity.idImagemNivel2(fase.getQuestoes().get(fase.getQuestaoAtual()).getPalavras()[3].getNomeSemAcento()));

        faseActivity.getTvQuestao().setText("QUESTÃO "+(fase.getQuestaoAtual()+1));
        if(usuario.getPontuacao() == 0){
            faseActivity.getTvScore().setText("SCORE: 0");
        }else{
            faseActivity.getTvScore().setText("SCORE: "+usuario.getPontuacao());
        }
        faseActivity.getTvPergunta().setText(fase.getQuestoes().get(fase.getQuestaoAtual()).getPergunta());
        faseActivity.getTvPalavra().setText(fase.getQuestoes().get(fase.getQuestaoAtual()).getPalavras()[fase.getQuestoes().get(fase.getQuestaoAtual()).getNumeroResposta()].getNome());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_opc1:
                this.faseActivity.inserirFocoImagemNivel(1);
                break;
            case R.id.img_opc2:
                this.faseActivity.inserirFocoImagemNivel(2);
                break;
            case R.id.img_opc3:
                this.faseActivity.inserirFocoImagemNivel(3);
                break;
            case R.id.img_opc4:
                this.faseActivity.inserirFocoImagemNivel(4);
                break;
            case R.id.bt_proximo:
                if(this.fase.verificarAlternativa(this.faseActivity.getAlternativaSelecionada())){
                    if(fase.getQuestaoAtual() < 5){
                        fase.setQuestaoAtual(fase.getQuestaoAtual()+1);
                        this.faseActivity.exibirMensagemFase2("Parabéns, você acertou!!!", "A tradução de "+fase.getQuestoes().get(fase.getQuestaoAtual()-1).getPalavras()[fase.getQuestoes().get(fase.getQuestaoAtual()-1).getNumeroResposta()].getNome()+" é "+fase.getQuestoes().get(fase.getQuestaoAtual()-1).getPalavras()[fase.getQuestoes().get(fase.getQuestaoAtual()-1).getNumeroResposta()].getTraducao()+"", true, this);
                        usuario.setPontuacao(usuario.getPontuacao()+20);
                        try {
                            Fachada.atualizarUsuario(faseActivity, usuario);
                        } catch (Exception e) {
                            faseActivity.exibirMensagem("Problemas na atualização da pontuação");
                        }
                    }else{
                        fase.setQuestaoAtual(fase.getQuestaoAtual()+1);
                        this.faseActivity.exibirMensagemUltimaQuestao("Parabéns, você acertou!!!", "A tradução de "+fase.getQuestoes().get(fase.getQuestaoAtual()-1).getPalavras()[fase.getQuestoes().get(fase.getQuestaoAtual()-1).getNumeroResposta()].getNome()+" é "+fase.getQuestoes().get(fase.getQuestaoAtual()-1).getPalavras()[fase.getQuestoes().get(fase.getQuestaoAtual()-1).getNumeroResposta()].getTraducao()+"", true);
                        usuario.setPontuacao(usuario.getPontuacao()+20);
                        try {
                            Fachada.atualizarUsuario(faseActivity, usuario);
                        } catch (Exception e) {
                            faseActivity.exibirMensagem("Problemas na atualização da pontuação");
                        }
                    }
                }else {
                    if (fase.getQuestaoAtual() < 5) {
                        fase.setQuestaoAtual(fase.getQuestaoAtual() + 1);
                        this.faseActivity.exibirMensagemFase2("Infelizmente você errou!!!", "A tradução de "+fase.getQuestoes().get(fase.getQuestaoAtual()-1).getPalavras()[fase.getQuestoes().get(fase.getQuestaoAtual()-1).getNumeroResposta()].getNome()+" é "+fase.getQuestoes().get(fase.getQuestaoAtual()-1).getPalavras()[fase.getQuestoes().get(fase.getQuestaoAtual()-1).getNumeroResposta()].getTraducao()+"", false, this);
                    }else{
                        fase.setQuestaoAtual(fase.getQuestaoAtual() + 1);
                        this.faseActivity.exibirMensagemUltimaQuestao("Infelizmente você errou!!!", "A tradução de "+fase.getQuestoes().get(fase.getQuestaoAtual()-1).getPalavras()[fase.getQuestoes().get(fase.getQuestaoAtual()-1).getNumeroResposta()].getNome()+" é "+fase.getQuestoes().get(fase.getQuestaoAtual()-1).getPalavras()[fase.getQuestoes().get(fase.getQuestaoAtual()-1).getNumeroResposta()].getTraducao()+"", true);
                    }
                }
                break;
        }
    }
}
