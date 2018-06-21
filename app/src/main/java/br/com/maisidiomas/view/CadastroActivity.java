package br.com.maisidiomas.view;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import br.com.maisidiomas.R;
import br.com.maisidiomas.controller.ControllerCadastro;

public class CadastroActivity extends AppCompatActivity {

    private EditText edtNome, edtLogin, edtSenha, edtConfirmSenha;
    private Button btCadastrar, btLimpar;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        edtNome = findViewById(R.id.edtNome);
        edtLogin = findViewById(R.id.edtLogin);
        edtSenha = findViewById(R.id.edtSenha);
        edtConfirmSenha = findViewById(R.id.edtConfirmSenha);
        btCadastrar = findViewById(R.id.btCadastrar);
        btLimpar = findViewById(R.id.btLimpar);
        new ControllerCadastro(this);

    }

    public void alertarCampoVazio() {
        if (edtNome.getText().toString().length() == 0) {
            edtNome.setError("Campo não preenchido");
        }
        if (edtLogin.getText().toString().length() == 0) {
            edtLogin.setError("Campo não preenchido");
        }
        if (edtSenha.getText().toString().length() == 0) {
            edtSenha.setError("Campo não preenchido");
        }
        if (edtConfirmSenha.getText().toString().length() == 0) {
            edtConfirmSenha.setError("Campo não preenchido");
        }
    }

    public void alertarSenhasIncompativeis(){
        edtConfirmSenha.setError("Senhas não conferem");
    }

    public void alertarLoginIndisponivel(){
        edtLogin.setError("Login indisponível!");
    }

    public void AlertSucessoCadastro(){
        Toast.makeText(CadastroActivity.this, "Cadastrado com sucesso!", Toast.LENGTH_LONG).show();
    }

    public void AlertErroCadastro(){
        Toast.makeText(CadastroActivity.this, "Erro ao cadastrar!", Toast.LENGTH_LONG).show();
    }

    public void limparCampos(){
        edtNome.setText("");
        edtLogin.setText("");
        edtSenha.setText("");
        edtConfirmSenha.setText("");

    }

    public EditText getEdtNome() {
        return edtNome;
    }

    public void setEdtNome(EditText edtNome) {
        this.edtNome = edtNome;
    }

    public EditText getEdtLogin() {
        return edtLogin;
    }

    public void setEdtLogin(EditText edtLogin) {
        this.edtLogin = edtLogin;
    }

    public EditText getEdtSenha() {
        return edtSenha;
    }

    public void setEdtSenha(EditText edtSenha) {
        this.edtSenha = edtSenha;
    }

    public EditText getEdtConfirmSenha() {
        return edtConfirmSenha;
    }

    public void setEdtConfirmSenha(EditText edtConfirmSenha) {
        this.edtConfirmSenha = edtConfirmSenha;
    }

    public Button getBtCadastrar() {
        return btCadastrar;
    }

    public void setBtCadastrar(Button btCadastrar) {
        this.btCadastrar = btCadastrar;
    }

    public Button getBtLimpar() {
        return btLimpar;
    }

    public void setBtLimpar(Button btLimpar) {
        this.btLimpar = btLimpar;
    }
}
