package br.com.maisidiomas.controller;

import android.view.View;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;

import java.util.ArrayList;

import br.com.maisidiomas.model.dao.ConexaoSQLite;
import br.com.maisidiomas.model.dao.Fachada;
import br.com.maisidiomas.model.dao.PalavraDAOSQLite;
import br.com.maisidiomas.model.dao.UsuarioDAOSQLite;
import br.com.maisidiomas.model.vo.Fase;
import br.com.maisidiomas.model.vo.Palavra;
import br.com.maisidiomas.model.vo.Usuario;
import br.com.maisidiomas.utils.FirebaseConecty;
import br.com.maisidiomas.view.FaseActivity;
import br.com.maisidiomas.R;

public class ControllerFase1 implements View.OnClickListener{

    private FaseActivity faseActivity;
    private Usuario usuario;
    private Fase fase;

    public ControllerFase1(FaseActivity faseActivity) {
        this.faseActivity = faseActivity;
        try {
            this.usuario = Fachada.findByLogin(faseActivity, faseActivity.getLogin());
        } catch (Exception e) {
           faseActivity.exibirMensagem("O sistema encontrou problemas ao iniciar a fase");
        }
        try {
            this.fase = new Fase(usuario.getLogin(),0, new PalavraDAOSQLite(ConexaoSQLite.getInstance(this.faseActivity)).listByLevel(1));
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.fase.gerarQuestoesNivel1();
        iniciarQuestao();

        this.faseActivity.getImgOpc1().setOnClickListener(this);
        this.faseActivity.getImgOpc2().setOnClickListener(this);
        this.faseActivity.getImgOpc3().setOnClickListener(this);
        this.faseActivity.getImgOpc4().setOnClickListener(this);
        this.faseActivity.getBtProximo().setOnClickListener(this);
    }

    public void iniciarQuestao() {
        if((fase.getQuestaoAtual()%3) == 0 && fase.getQuestaoAtual() != 0 && fase.getQuestaoAtual() < 8){
            String ajuda = "A tradução de "+fase.getQuestoes().get(fase.getQuestaoAtual()+2).getPalavras()[fase.getQuestoes().get(fase.getQuestaoAtual()+2).getNumeroResposta()].getNome()+" é "+fase.getQuestoes().get(fase.getQuestaoAtual()+2).getPalavras()[fase.getQuestoes().get(fase.getQuestaoAtual()+2).getNumeroResposta()].getTraducao()+"";
            this.faseActivity.exibirMensagemCompra(this, ajuda);
        }
        faseActivity.getImgOpc1().setImageResource(faseActivity.idImagemNivel1(fase.getQuestoes().get(fase.getQuestaoAtual()).getPalavras()[0].getTraducaoSemAcento()));
        faseActivity.getImgOpc2().setImageResource(faseActivity.idImagemNivel1(fase.getQuestoes().get(fase.getQuestaoAtual()).getPalavras()[1].getTraducaoSemAcento()));
        faseActivity.getImgOpc3().setImageResource(faseActivity.idImagemNivel1(fase.getQuestoes().get(fase.getQuestaoAtual()).getPalavras()[2].getTraducaoSemAcento()));
        faseActivity.getImgOpc4().setImageResource(faseActivity.idImagemNivel1(fase.getQuestoes().get(fase.getQuestaoAtual()).getPalavras()[3].getTraducaoSemAcento()));

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
                    if(fase.getQuestaoAtual() < 9){
                        fase.setQuestaoAtual(fase.getQuestaoAtual()+1);
                        this.faseActivity.exibirMensagemFase1("Parabéns, você acertou!!!", "A tradução de "+fase.getQuestoes().get(fase.getQuestaoAtual()-1).getPalavras()[fase.getQuestoes().get(fase.getQuestaoAtual()-1).getNumeroResposta()].getNome()+" é "+fase.getQuestoes().get(fase.getQuestaoAtual()-1).getPalavras()[fase.getQuestoes().get(fase.getQuestaoAtual()-1).getNumeroResposta()].getTraducao()+"", true, this);
                        usuario.setPontuacao(usuario.getPontuacao()+10);
                        try {
                            Fachada.atualizarUsuario(faseActivity, usuario);
                            FirebaseConecty.salvar(usuario);
                        } catch (Exception e) {
                            faseActivity.exibirMensagem("Problemas na atualização da pontuação");
                        }
                    }else{
                        fase.setQuestaoAtual(fase.getQuestaoAtual()+1);
                        usuario.setPontuacao(usuario.getPontuacao()+10);
                        try {
                            Fachada.atualizarUsuario(faseActivity, usuario);
                            FirebaseConecty.salvar(usuario);
                        } catch (Exception e) {
                            faseActivity.exibirMensagem("Problemas na atualização da pontuação");
                        }
                        this.faseActivity.exibirMensagemUltimaQuestao("Parabéns, você acertou!!!", "A tradução de "+fase.getQuestoes().get(fase.getQuestaoAtual()-1).getPalavras()[fase.getQuestoes().get(fase.getQuestaoAtual()-1).getNumeroResposta()].getNome()+" é "+fase.getQuestoes().get(fase.getQuestaoAtual()-1).getPalavras()[fase.getQuestoes().get(fase.getQuestaoAtual()-1).getNumeroResposta()].getTraducao()+"", true);
                    }
                }else {
                    if (fase.getQuestaoAtual() < 9) {
                        fase.setQuestaoAtual(fase.getQuestaoAtual() + 1);
                        this.faseActivity.exibirMensagemFase1("Infelizmente você errou!!!", "A tradução de "+fase.getQuestoes().get(fase.getQuestaoAtual()-1).getPalavras()[fase.getQuestoes().get(fase.getQuestaoAtual()-1).getNumeroResposta()].getNome()+" é "+fase.getQuestoes().get(fase.getQuestaoAtual()-1).getPalavras()[fase.getQuestoes().get(fase.getQuestaoAtual()-1).getNumeroResposta()].getTraducao()+"", false, this);
                    }else{
                        fase.setQuestaoAtual(fase.getQuestaoAtual() + 1);
                        this.faseActivity.exibirMensagemUltimaQuestao("Infelizmente você errou!!!", "A tradução de "+fase.getQuestoes().get(fase.getQuestaoAtual()-1).getPalavras()[fase.getQuestoes().get(fase.getQuestaoAtual()-1).getNumeroResposta()].getNome()+" é "+fase.getQuestoes().get(fase.getQuestaoAtual()-1).getPalavras()[fase.getQuestoes().get(fase.getQuestaoAtual()-1).getNumeroResposta()].getTraducao()+"", true);
                    }
                }
                break;
        }
    }


    public FaseActivity getFaseActivity() {
        return faseActivity;
    }

    public void setFaseActivity(FaseActivity faseActivity) {
        this.faseActivity = faseActivity;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Fase getFase() {
        return fase;
    }

    public void setFase(Fase fase) {
        this.fase = fase;
    }
}
