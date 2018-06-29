package br.com.maisidiomas.controller;

import android.view.View;

import java.util.ArrayList;

import br.com.maisidiomas.R;
import br.com.maisidiomas.model.dao.ConexaoSQLite;
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
            this.usuario = new UsuarioDAOSQLite(ConexaoSQLite.getInstance(this.faseActivity)).findByLogin(this.faseActivity.getLogin());
        } catch (Exception e) {
            e.printStackTrace();
        }
        ArrayList<Palavra> p = new PalavraDAOSQLite(ConexaoSQLite.getInstance(this.faseActivity)).listar(2);
       if(p == null){
           this.faseActivity.exibirMensagem("lista vazia");
       }else{
           this.fase = new Fase(usuario.getId(),0,p);
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
        faseActivity.getImgOpc1().setImageResource(faseActivity.idImagemNivel2(retirarCaracteresEspeciais(fase.getQuestoes().get(fase.getQuestaoAtual()).getPalavras()[0].getNome())));
        faseActivity.getImgOpc2().setImageResource(faseActivity.idImagemNivel2(retirarCaracteresEspeciais(fase.getQuestoes().get(fase.getQuestaoAtual()).getPalavras()[1].getNome())));
        faseActivity.getImgOpc3().setImageResource(faseActivity.idImagemNivel2(retirarCaracteresEspeciais(fase.getQuestoes().get(fase.getQuestaoAtual()).getPalavras()[2].getNome())));
        faseActivity.getImgOpc4().setImageResource(faseActivity.idImagemNivel2(retirarCaracteresEspeciais(fase.getQuestoes().get(fase.getQuestaoAtual()).getPalavras()[3].getNome())));

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
                        this.faseActivity.exibirFase2("Parabéns, você acertou!!!", "A tradução de "+fase.getQuestoes().get(fase.getQuestaoAtual()-1).getPalavras()[fase.getQuestoes().get(fase.getQuestaoAtual()-1).getNumeroResposta()].getNome()+" é "+fase.getQuestoes().get(fase.getQuestaoAtual()-1).getPalavras()[fase.getQuestoes().get(fase.getQuestaoAtual()-1).getNumeroResposta()].getTraducao()+"", true, this);
                        //fase.setPontuacao(fase.getPontuacao()+20);
                        usuario.setPontuacao(usuario.getPontuacao()+20);
                        try {
                            new UsuarioDAOSQLite(ConexaoSQLite.getInstance(this.faseActivity)).update(usuario);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }else{
                        fase.setQuestaoAtual(fase.getQuestaoAtual()+1);
                        this.faseActivity.exibirUltimo("Parabéns, você acertou!!!", "A tradução de "+fase.getQuestoes().get(fase.getQuestaoAtual()-1).getPalavras()[fase.getQuestoes().get(fase.getQuestaoAtual()-1).getNumeroResposta()].getNome()+" é "+fase.getQuestoes().get(fase.getQuestaoAtual()-1).getPalavras()[fase.getQuestoes().get(fase.getQuestaoAtual()-1).getNumeroResposta()].getTraducao()+"", true);
                        //fase.setPontuacao(fase.getPontuacao()+20);
                        usuario.setPontuacao(usuario.getPontuacao()+20);
                        try {
                            new UsuarioDAOSQLite(ConexaoSQLite.getInstance(this.faseActivity)).update(usuario);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }else {
                    if (fase.getQuestaoAtual() < 5) {
                        fase.setQuestaoAtual(fase.getQuestaoAtual() + 1);
                        this.faseActivity.exibirFase2("Infelizmente você errou!!!", "A tradução de "+fase.getQuestoes().get(fase.getQuestaoAtual()-1).getPalavras()[fase.getQuestoes().get(fase.getQuestaoAtual()-1).getNumeroResposta()].getNome()+" é "+fase.getQuestoes().get(fase.getQuestaoAtual()-1).getPalavras()[fase.getQuestoes().get(fase.getQuestaoAtual()-1).getNumeroResposta()].getTraducao()+"", false, this);
                    }else{
                        fase.setQuestaoAtual(fase.getQuestaoAtual() + 1);
                        this.faseActivity.exibirUltimo("Parabéns, você acertou!!!", "A tradução de "+fase.getQuestoes().get(fase.getQuestaoAtual()-1).getPalavras()[fase.getQuestoes().get(fase.getQuestaoAtual()-1).getNumeroResposta()].getNome()+" é "+fase.getQuestoes().get(fase.getQuestaoAtual()-1).getPalavras()[fase.getQuestoes().get(fase.getQuestaoAtual()-1).getNumeroResposta()].getTraducao()+"", true);
                    }
                }
                break;
        }
    }


    private String retirarCaracteresEspeciais(String palavra) {
        String p0 = palavra.replace(" ", "_");
        String p1 = p0.replace("ç", "c");
        String p2 = p1.replace("ã", "a");
        String p3 = p2.replace("ê", "e");
        String p4 = p3.replace("õ", "o");
        String p5 = p4.replace("á", "a");
        String p6 = p5.replace("é", "e");
        String p7 = p6.replace("í", "i");
        String p8 = p7.replace("ó", "o");
        String p9 = p8.replace("ú", "u");
        String p = p9.toLowerCase();
        return p;
    }

}
