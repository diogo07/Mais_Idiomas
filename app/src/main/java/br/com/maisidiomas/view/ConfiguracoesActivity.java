package br.com.maisidiomas.view;

import android.app.AlertDialog;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.method.TransformationMethod;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.maisidiomas.R;
import br.com.maisidiomas.controller.ControllerConfiguracoes;

public class ConfiguracoesActivity extends ModeloActivity {

    private TextView tvConf, tvAvtMod;
    private Button btSalvar, btModAvt;
    private EditText edtSenhaAtual, edtEscolha;
    private AlertDialog alertaAvatar;
    private ImageView imgEditNome, imgEditLogin, imgEditSenha, imgEdiAvatar;
    private String avatar, avtUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracoes);

        avtUser = getIntent().getStringExtra("avatar");

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        tvConf = findViewById(R.id.tvConf);
        tvAvtMod = findViewById(R.id.tvAvtMod);

        edtSenhaAtual = findViewById(R.id.edtSenhaAtual);
        edtEscolha = findViewById(R.id.edtEscolha);

        btSalvar = findViewById(R.id.btSalvar);
        btModAvt = findViewById(R.id.btModificarAvatar);

        imgEditNome = findViewById(R.id.imgEditNome);
        imgEditLogin = findViewById(R.id.imgEditLogin);
        imgEditSenha = findViewById(R.id.imgEditSenha);
        imgEdiAvatar = findViewById(R.id.imgEditAvatar);

        esconderConteudo();

        tvConf.setTypeface(getFont());
        tvAvtMod.setTypeface(getFont());
        edtEscolha.setTypeface(getFont());
        edtSenhaAtual.setTypeface(getFont());

        btSalvar.setTypeface(getFont());

        new ControllerConfiguracoes(this);
    }

    public void exibirConteudoEscolha(String escolha){
        esconderConteudo();
        if(escolha.equals("nome")){
            edtEscolha.setText("");
            edtEscolha.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            edtEscolha.setVisibility(View.VISIBLE);
            edtEscolha.setHint("Novo nome");
            btSalvar.setVisibility(View.VISIBLE);
        }else if(escolha.equals("login")){
            edtEscolha.setText("");
            edtEscolha.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            edtEscolha.setVisibility(View.VISIBLE);
            edtEscolha.setHint("Novo login");
            btSalvar.setVisibility(View.VISIBLE);
        }else if(escolha.equals("senha")){
            edtEscolha.setText("");
            edtSenhaAtual.setText("");
            edtEscolha.setTransformationMethod(PasswordTransformationMethod.getInstance());
            edtSenhaAtual.setVisibility(View.VISIBLE);
            edtEscolha.setVisibility(View.VISIBLE);
            edtEscolha.setHint("Nova senha");
            btSalvar.setVisibility(View.VISIBLE);
        }else{
            inserirAvatarAtual();
            tvAvtMod.setVisibility(View.VISIBLE);
            btModAvt.setVisibility(View.VISIBLE);
            btSalvar.setVisibility(View.VISIBLE);
        }
    }

    public void esconderConteudo(){
        edtSenhaAtual.setVisibility(View.GONE);
        edtEscolha.setVisibility(View.GONE);
        tvAvtMod.setVisibility(View.GONE);
        btModAvt.setVisibility(View.GONE);
        btSalvar.setVisibility(View.GONE);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return true;
    }

    public void inserirFocoImagem(int opc){
        switch (opc){
            case 1:
                imgEditNome.setFocusable(true);
                imgEditNome.setBackgroundResource(R.drawable.estilo_opcao_questao);
                imgEditLogin.setBackgroundResource(R.drawable.estilo_borda);
                imgEditLogin.setFocusable(false);
                imgEditSenha.setBackgroundResource(R.drawable.estilo_borda);
                imgEditSenha.setFocusable(false);
                imgEdiAvatar.setBackgroundResource(R.drawable.estilo_borda);
                imgEdiAvatar.setFocusable(false);
                break;
            case 2:
                imgEditNome.setFocusable(false);
                imgEditNome.setBackgroundResource(R.drawable.estilo_borda);
                imgEditLogin.setBackgroundResource(R.drawable.estilo_opcao_questao);
                imgEditLogin.setFocusable(true);
                imgEditSenha.setBackgroundResource(R.drawable.estilo_borda);
                imgEditSenha.setFocusable(false);
                imgEdiAvatar.setBackgroundResource(R.drawable.estilo_borda);
                imgEdiAvatar.setFocusable(false);
                break;
            case 3:
                imgEditNome.setFocusable(false);
                imgEditNome.setBackgroundResource(R.drawable.estilo_borda);
                imgEditLogin.setBackgroundResource(R.drawable.estilo_borda);
                imgEditLogin.setFocusable(false);
                imgEditSenha.setBackgroundResource(R.drawable.estilo_opcao_questao);
                imgEditSenha.setFocusable(true);
                imgEdiAvatar.setBackgroundResource(R.drawable.estilo_borda);
                imgEdiAvatar.setFocusable(false);
                break;
            case 4:
                imgEditNome.setFocusable(false);
                imgEditNome.setBackgroundResource(R.drawable.estilo_borda);
                imgEditLogin.setBackgroundResource(R.drawable.estilo_borda);
                imgEditLogin.setFocusable(false);
                imgEditSenha.setBackgroundResource(R.drawable.estilo_borda);
                imgEditSenha.setFocusable(false);
                imgEdiAvatar.setBackgroundResource(R.drawable.estilo_opcao_questao);
                imgEdiAvatar.setFocusable(true);
                break;
        }
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
                    btModAvt.setBackgroundResource(R.mipmap.ic_avatar1);
                    avatar = "avatar1";
                }
                if(i == 1){
                    btModAvt.setBackgroundResource(R.mipmap.ic_avatar2);
                    avatar = "avatar2";
                }
                if(i == 2){
                    btModAvt.setBackgroundResource(R.mipmap.ic_avatar3);
                    avatar = "avatar3";
                }
                if(i == 3){
                    btModAvt.setBackgroundResource(R.mipmap.ic_avatar4);
                    avatar = "avatar4";
                }
                if(i == 4){
                    btModAvt.setBackgroundResource(R.mipmap.ic_avatar5);
                    avatar = "avatar5";
                }
                if(i == 5){
                    btModAvt.setBackgroundResource(R.mipmap.ic_avatar6);
                    avatar = "avatar6";
                }
                if(i == 6){
                    btModAvt.setBackgroundResource(R.mipmap.ic_avatar7);
                    avatar = "avatar7";
                }
                if(i == 7){
                    btModAvt.setBackgroundResource(R.mipmap.ic_avatar8);
                    avatar = "avatar8";
                }
            }else{
                avt.get(i).setFocusable(false);
                avt.get(i).setBackgroundResource(R.drawable.estilo_opcao_no_select);
            }
        }

        alertaAvatar.dismiss();
    }


    private void inserirAvatarAtual() {
        if(avtUser != null){
            switch (avtUser){
                case "avatar1":
                    btModAvt.setBackgroundResource(R.mipmap.ic_avatar1);
                    break;
                case "avatar2":
                    btModAvt.setBackgroundResource(R.mipmap.ic_avatar2);
                    break;
                case "avatar3":
                    btModAvt.setBackgroundResource(R.mipmap.ic_avatar3);
                    break;
                case "avatar4":
                    btModAvt.setBackgroundResource(R.mipmap.ic_avatar4);
                    break;
                case "avatar5":
                    btModAvt.setBackgroundResource(R.mipmap.ic_avatar5);
                    break;
                case "avatar6":
                    btModAvt.setBackgroundResource(R.mipmap.ic_avatar6);
                    break;
                case "avatar7":
                    btModAvt.setBackgroundResource(R.mipmap.ic_avatar7);
                    break;
                case "avatar8":
                    btModAvt.setBackgroundResource(R.mipmap.ic_avatar8);
                    break;
            }
        }
    }


    public TextView getTvConf() {
        return tvConf;
    }

    public void setTvConf(TextView tvConf) {
        this.tvConf = tvConf;
    }

    public TextView getTvAvtMod() {
        return tvAvtMod;
    }

    public void setTvAvtMod(TextView tvAvtMod) {
        this.tvAvtMod = tvAvtMod;
    }

    public Button getBtSalvar() {
        return btSalvar;
    }

    public void setBtSalvar(Button btSalvar) {
        this.btSalvar = btSalvar;
    }

    public Button getBtModAvt() {
        return btModAvt;
    }

    public void setBtModAvt(Button btModAvt) {
        this.btModAvt = btModAvt;
    }

    public EditText getEdtSenhaAtual() {
        return edtSenhaAtual;
    }

    public void setEdtSenhaAtual(EditText edtSenhaAtual) {
        this.edtSenhaAtual = edtSenhaAtual;
    }

    public EditText getEdtEscolha() {
        return edtEscolha;
    }

    public void setEdtEscolha(EditText edtEscolha) {
        this.edtEscolha = edtEscolha;
    }

    public ImageView getImgEditNome() {
        return imgEditNome;
    }

    public void setImgEditNome(ImageView imgEditNome) {
        this.imgEditNome = imgEditNome;
    }

    public ImageView getImgEditLogin() {
        return imgEditLogin;
    }

    public void setImgEditLogin(ImageView imgEditLogin) {
        this.imgEditLogin = imgEditLogin;
    }

    public ImageView getImgEditSenha() {
        return imgEditSenha;
    }

    public void setImgEditSenha(ImageView imgEditSenha) {
        this.imgEditSenha = imgEditSenha;
    }

    public ImageView getImgEdiAvatar() {
        return imgEdiAvatar;
    }

    public void setImgEdiAvatar(ImageView imgEdiAvatar) {
        this.imgEdiAvatar = imgEdiAvatar;
    }
}
