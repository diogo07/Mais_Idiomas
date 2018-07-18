package br.com.maisidiomas.controller;

import android.content.Intent;
import android.view.View;

import java.util.ArrayList;

import br.com.maisidiomas.model.dao.ConexaoSQLite;
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
    private UsuarioDAOSQLite usuarioDAO;

    public ControllerLogin(LoginActivity loginActivity) {
        this.loginActivity = loginActivity;
        this.loginActivity.getBtEntrar().setOnClickListener(this);
        this.loginActivity.getTvCadastro().setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        usuarioDAO = new UsuarioDAOSQLite(ConexaoSQLite.getInstance(this.loginActivity));
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
            usuarioDAO = new UsuarioDAOSQLite(ConexaoSQLite.getInstance(loginActivity));
            Usuario usuario = usuarioDAO.findByLoginEsenha(loginActivity.getEtLogin().getText().toString(), loginActivity.getEtSenha().getText().toString());
            if(usuario != null){
                this.loginActivity.limparCampos();
                FirebaseConecty.salvar(usuario);
                abrirTelaHome(usuario);
            }else{
                loginActivity.alertarDadosInvalidos();
            }
        }else {
            loginActivity.alertarCamposVazios();
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
}
