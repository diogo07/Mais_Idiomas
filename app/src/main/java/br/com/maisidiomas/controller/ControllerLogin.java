package br.com.maisidiomas.controller;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;

import br.com.maisidiomas.model.dao.ConexaoSQLite;
import br.com.maisidiomas.model.dao.UsuarioDAOMySQL;
import br.com.maisidiomas.model.dao.UsuarioDAOSQLite;
import br.com.maisidiomas.model.vo.Usuario;
import br.com.maisidiomas.view.CadastroActivity;
//import br.com.maisidiomas.view.HomeActivity;
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
                abrirTelaHome(usuario);
            }else{
                loginActivity.alertarDadosInvalidos();
            }

/*
            Usuario usuario = new UsuarioDAOMySQL(this.loginActivity).findByLoginEsenha(loginActivity.getEtLogin().getText().toString(), loginActivity.getEtSenha().getText().toString());

            if(usuario != null){
                this.loginActivity.limparCampos();
                abrirTelaHome(usuario);
            }else{
                loginActivity.alertarDadosInvalidos();
            }
*/
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
        Intent i = new Intent(loginActivity.getBaseContext(), DashBoardActivity.class);
        i.putExtra("nome", usuario.getNome());
        i.putExtra("login", usuario.getLogin());
        i.putExtra("id", usuario.getId()+"");
        loginActivity.startActivity(i);
    }

    private void abrirTelaCadastro() {
        Intent i = new Intent(loginActivity.getBaseContext(), CadastroActivity.class);
        loginActivity.startActivity(i);
    }
}
