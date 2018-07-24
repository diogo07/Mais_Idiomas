package br.com.maisidiomas.controller;

import android.view.View;

import br.com.maisidiomas.model.dao.Fachada;
import br.com.maisidiomas.model.vo.Usuario;
import br.com.maisidiomas.utils.FirebaseConecty;
import br.com.maisidiomas.utils.UtilsParametros;
import br.com.maisidiomas.view.CadastroActivity;

import br.com.maisidiomas.R;

public class ControllerCadastro implements View.OnClickListener{

    private CadastroActivity cadastroActivity;

    public ControllerCadastro(CadastroActivity cadastroActivity) {
        this.cadastroActivity = cadastroActivity;
        this.cadastroActivity.getBtCadastrar().setOnClickListener(this);
        this.cadastroActivity.getBtLimpar().setOnClickListener(this);
        this.cadastroActivity.getBtAvatar().setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.btCadastrar){
            cadastrar();
        }
        if(id == R.id.btLimpar){

        }
        if(id == R.id.btEscolhaAvatar){
            this.cadastroActivity.escolherAvatar();
        }
    }

    private void cadastrar() {
        if(FirebaseConecty.isConected(cadastroActivity)){
            if(!camposVazios()){
                if(senhasConferem()){
                    if(!cadastroActivity.getAvatar().equals("vazio")){
                        UtilsParametros.carregarContexto(cadastroActivity);
                        Fachada.loginDisponivel(cadastroActivity,cadastroActivity.getEdtLogin().getText().toString()+"", this);
                    /*if(!Fachada.loginDisponivel(cadastroActivity,cadastroActivity.getEdtLogin().getText().toString()+"", this)){
                        cadastroActivity.alertarLoginIndisponivel();
                    }else{
                        Usuario usuario = new Usuario(cadastroActivity.getEdtLogin().getText().toString(),
                                cadastroActivity.getEdtSenha().getText().toString(),
                                cadastroActivity.getEdtNome().getText().toString());
                        usuario.setPontuacao(0);
                        usuario.setFoto(this.cadastroActivity.getAvatar());

                        try{
                            Fachada.inserirUsuario(usuario, cadastroActivity);
                            cadastroActivity.AlertSucessoCadastro();
                            cadastroActivity.limparCampos();
                            cadastroActivity.finish();
                        }catch (Exception e){
                            cadastroActivity.AlertErroCadastro();
                        }
                    }*/
                    }else{
                        cadastroActivity.exibirMensagem("Selecione seu avatar!");
                    }
                }else{
                    cadastroActivity.alertarSenhasIncompativeis();
                }
            }else{
                cadastroActivity.alertarCampoVazio();
            }
        }else{
            this.cadastroActivity.exibirMensagem("Sem conexão com a internet");
        }
    }

    public void cadastrarUsuario(){

        Usuario usuario = new Usuario(cadastroActivity.getEdtLogin().getText().toString(),cadastroActivity.getEdtSenha().getText().toString(),
                    cadastroActivity.getEdtNome().getText().toString());
        usuario.setPontuacao(0);
        usuario.setFoto(this.cadastroActivity.getAvatar());
        usuario.setId(UtilsParametros.getIdCadastro());

        try{
            Fachada.inserirUsuario(usuario, cadastroActivity);
            cadastroActivity.AlertSucessoCadastro();
            cadastroActivity.limparCampos();
            cadastroActivity.finish();
        }catch (Exception e){
            cadastroActivity.AlertErroCadastro();
        }
    }

    private boolean senhasConferem() {
        if(cadastroActivity.getEdtSenha().getText().toString().equals(cadastroActivity.getEdtConfirmSenha().getText().toString())){
            return true;
        }else{
            return false;
        }
    }

    private boolean camposVazios() {
        if(cadastroActivity.getEdtNome().getText().toString().length() > 0
                && cadastroActivity.getEdtLogin().getText().toString().length() > 0
                && cadastroActivity.getEdtSenha().getText().toString().length() > 0
                && cadastroActivity.getEdtConfirmSenha().getText().toString().length() > 0){
            return false;
        }else{
            return true;
        }
    }


    public CadastroActivity getCadastroActivity() {
        return cadastroActivity;
    }

    public void setCadastroActivity(CadastroActivity cadastroActivity) {
        this.cadastroActivity = cadastroActivity;
    }
}
