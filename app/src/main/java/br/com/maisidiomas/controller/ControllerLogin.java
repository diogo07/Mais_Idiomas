package br.com.maisidiomas.controller;

import android.app.ProgressDialog;
import android.content.Intent;
import android.view.View;

import java.util.ArrayList;

import br.com.maisidiomas.model.dao.ConexaoSQLite;
import br.com.maisidiomas.model.dao.Fachada;
import br.com.maisidiomas.model.dao.UsuarioDAOSQLite;
import br.com.maisidiomas.model.vo.Usuario;
import br.com.maisidiomas.utils.FirebaseConecty;
import br.com.maisidiomas.utils.UtilsParametros;
import br.com.maisidiomas.view.CadastroActivity;
import br.com.maisidiomas.view.DashBoardActivity;
import br.com.maisidiomas.view.LoginActivity;

import br.com.maisidiomas.R;

public class ControllerLogin implements View.OnClickListener{

    private LoginActivity loginActivity;

    public ControllerLogin(LoginActivity loginActivity) {
        this.loginActivity = loginActivity;
        this.loginActivity.getBtEntrar().setOnClickListener(this);
        this.loginActivity.getTvCadastro().setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btEntrar:
                validarLogin();
                break;
            case R.id.tvCadastro:
                abrirTelaCadastro();
                break;
            default:
                break;
        }
    }

    private void validarLogin() {
        if(camposPreenchidos()){
            UtilsParametros.carregarUsuario(null);
            Usuario usuario = Fachada.findByLoginEsenha(loginActivity, loginActivity.getEtLogin().getText().toString(), loginActivity.getEtSenha().getText().toString());
            if(usuario != null){
                this.loginActivity.limparCampos();
                FirebaseConecty.salvar(usuario);
                UtilsParametros.carregarUsuario(usuario);
                abrirTelaHome(usuario);
            }else{
                final ProgressDialog progressDialog = ProgressDialog.show(loginActivity, "", "Carregando ...", true);
                FirebaseConecty.getUsuarioByLogin(this, loginActivity.getEtLogin().getText().toString(), loginActivity.getEtSenha().getText().toString(), progressDialog);

            }
        }else {
            loginActivity.alertarCamposVazios();
        }

    }

    public void verificarUsuarioLogado(){
        Usuario usuario = UtilsParametros.getUsuarioLogado();
        if(usuario != null){
            abrirTelaHome(usuario);
        }else{
            loginActivity.alertarDadosInvalidos();
        }
    }

    private boolean camposPreenchidos() {
        if(loginActivity.getEtLogin().getText().toString().length() > 0 && loginActivity.getEtSenha().getText().toString().length() > 0){
            return true;
        }else{
            return false;
        }
    }

    private void abrirTelaHome(Usuario usuario) {
        Intent i = new Intent(loginActivity, DashBoardActivity.class);
        i.putExtra("nome", usuario.getNome());
        i.putExtra("login", usuario.getLogin());
        i.putExtra("pontuacao", usuario.getPontuacao()+"");
        i.putExtra("avatar", usuario.getFoto());
        i.putExtra("id", usuario.getId()+"");
        loginActivity.startActivity(i);
        loginActivity.finish();
    }

    private void abrirTelaCadastro() {
        Intent i = new Intent(loginActivity.getBaseContext(), CadastroActivity.class);
        loginActivity.startActivity(i);
    }

    public void imprimirLista() {
        ArrayList<Usuario> usuarios = UtilsParametros.getListaUsuarios();
        for (Usuario u: usuarios) {
            System.out.println("Nome: "+u.getNome());
        }
    }


    public LoginActivity getLoginActivity() {
        return loginActivity;
    }

    public void setLoginActivity(LoginActivity loginActivity) {
        this.loginActivity = loginActivity;
    }
}
