package br.com.maisidiomas.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toolbar;

import br.com.maisidiomas.R;
import br.com.maisidiomas.controller.ControllerLogin;
import br.com.maisidiomas.utils.UtilsParametros;

public class LoginActivity extends ModeloActivity{

    private EditText etLogin, etSenha;
    private Button btEntrar;
    private TextView tvCadastro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etLogin = findViewById(R.id.etLogin);
        etSenha = findViewById(R.id.etSenha);
        btEntrar = findViewById(R.id.btEntrar);
        tvCadastro = findViewById(R.id.tvCadastro);

        etLogin.setTypeface(getFont());
        etSenha.setTypeface(getFont());

        tvCadastro.setTypeface(getFont());
        btEntrar.setTypeface(getFont());


        UtilsParametros.carregarListaUsuarios(null);

        new ControllerLogin(this);
    }

    public void alertarCamposVazios(){
        if(etLogin.getText().toString().length() == 0){
            etLogin.setError("Preencha este campo");
        }
        if(etSenha.getText().toString().length() == 0){
            etSenha.setError("Preencha este campo");
        }
    }

    public void alertarDadosInvalidos(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);
        alertDialogBuilder.setTitle("Erro");
        alertDialogBuilder
                .setMessage("Login e/ou senha incorreto(os)!")
                .setCancelable(false)
                .setPositiveButton("Ok",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }

    public void limparCampos(){
        etLogin.setText("");
        etSenha.setText("");
    }

    public void exibirMensagem(String mensagem){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);
        alertDialogBuilder.setTitle("Mensagem");
        alertDialogBuilder
                .setMessage(mensagem)
                .setCancelable(false)
                .setPositiveButton("Ok",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }



    public EditText getEtLogin() {
        return etLogin;
    }
    public void setEtLogin(EditText etLogin) {
        this.etLogin = etLogin;
    }
    public EditText getEtSenha() {
        return etSenha;
    }
    public void setEtSenha(EditText etSenha) {
        this.etSenha = etSenha;
    }
    public Button getBtEntrar() {
        return btEntrar;
    }
    public void setBtEntrar(Button btEntrar) {
        this.btEntrar = btEntrar;
    }
    public TextView getTvCadastro() {
        return tvCadastro;
    }
    public void setTvCadastro(TextView tvCadastro) {
        this.tvCadastro = tvCadastro;
    }
}

