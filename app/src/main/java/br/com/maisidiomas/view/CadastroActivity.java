package br.com.maisidiomas.view;

import android.app.AlertDialog;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.ArrayList;

import br.com.maisidiomas.R;
import br.com.maisidiomas.controller.ControllerCadastro;

public class CadastroActivity extends ModeloActivity {

    private EditText edtNome, edtLogin, edtSenha, edtConfirmSenha;
    private TextView tvAvatar;
    private Button btCadastrar, btLimpar, btAvatar;
    private AlertDialog alertaAvatar;
    private String avatar;

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
        btAvatar = findViewById(R.id.btEscolhaAvatar);
        tvAvatar = findViewById((R.id.tvAvt));

        edtNome.setTypeface(getFont());
        edtLogin.setTypeface(getFont());
        edtSenha.setTypeface(getFont());
        edtConfirmSenha.setTypeface(getFont());
        tvAvatar.setTypeface(getFont());
        btCadastrar.setTypeface(getFont());
        btLimpar.setTypeface(getFont());

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

    public void escolherAvatar(){
        LayoutInflater li = getLayoutInflater();

        View view = li.inflate(R.layout.escolha_avatar, null);

        TextView textView = view.findViewById(R.id.tvEscolha);
        textView.setTypeface(getFont());

        ImageView avatar1 = view.findViewById(R.id.imgAvatar1);
        ImageView avatar2 = view.findViewById(R.id.imgAvatar2);
        ImageView avatar3 = view.findViewById(R.id.imgAvatar3);
        ImageView avatar4 = view.findViewById(R.id.imgAvatar4);
        ImageView avatar5 = view.findViewById(R.id.imgAvatar5);
        ImageView avatar6 = view.findViewById(R.id.imgAvatar6);
        ImageView avatar7 = view.findViewById(R.id.imgAvatar7);
        ImageView avatar8 = view.findViewById(R.id.imgAvatar8);

        final ArrayList<ImageView> avatares = new ArrayList<>();
        avatares.add(avatar1);
        avatares.add(avatar2);
        avatares.add(avatar3);
        avatares.add(avatar4);
        avatares.add(avatar5);
        avatares.add(avatar6);
        avatares.add(avatar7);
        avatares.add(avatar8);


        for(int i = 0; i < avatares.size(); i++){
            final int finalI = i;
            avatares.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    inserirFocoImagem(finalI+1, avatares);
                }
            });
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("");
        builder.setView(view);
        alertaAvatar = builder.create();
        alertaAvatar.show();
    }


    public void inserirFocoImagem(int id, ArrayList<ImageView> avt){

        for(int i = 0; i < avt.size(); i++){
            if(id-1 == i){
                avt.get(i).setFocusable(true);
                avt.get(i).setBackgroundResource(R.drawable.estilo_opcao_questao);

                if(i == 0){
                    btAvatar.setBackgroundResource(R.mipmap.ic_avatar1);
                    avatar = "avatar1";
                }
                if(i == 1){
                    btAvatar.setBackgroundResource(R.mipmap.ic_avatar2);
                    avatar = "avatar2";
                }
                if(i == 2){
                    btAvatar.setBackgroundResource(R.mipmap.ic_avatar3);
                    avatar = "avatar3";
                }
                if(i == 3){
                    btAvatar.setBackgroundResource(R.mipmap.ic_avatar4);
                    avatar = "avatar4";
                }
                if(i == 4){
                    btAvatar.setBackgroundResource(R.mipmap.ic_avatar5);
                    avatar = "avatar5";
                }
                if(i == 5){
                    btAvatar.setBackgroundResource(R.mipmap.ic_avatar6);
                    avatar = "avatar6";
                }
                if(i == 6){
                    btAvatar.setBackgroundResource(R.mipmap.ic_avatar7);
                    avatar = "avatar7";
                }
                if(i == 7){
                    btAvatar.setBackgroundResource(R.mipmap.ic_avatar8);
                    avatar = "avatar8";
                }
            }else{
                avt.get(i).setFocusable(false);
                avt.get(i).setBackgroundResource(R.drawable.estilo_opcao_no_select);
            }
        }

        alertaAvatar.dismiss();
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Button getBtAvatar() {
        return btAvatar;
    }

    public void setBtAvatar(Button btAvatar) {
        this.btAvatar = btAvatar;
    }

    public AlertDialog getAlertaAvatar() {
        return alertaAvatar;
    }

    public void setAlertaAvatar(AlertDialog alertaAvatar) {
        this.alertaAvatar = alertaAvatar;
    }
}
