package br.com.maisidiomas.controller;

import android.view.View;
import android.widget.Toast;

import br.com.maisidiomas.R;
import br.com.maisidiomas.view.ConfiguracoesActivity;

public class ControllerConfiguracoes implements View.OnClickListener{

    private ConfiguracoesActivity configuracoesActivity;


    public ControllerConfiguracoes(ConfiguracoesActivity configuracoesActivity) {
        this.configuracoesActivity = configuracoesActivity;

        this.configuracoesActivity.getImgEditNome().setOnClickListener(this);
        this.configuracoesActivity.getImgEditLogin().setOnClickListener(this);
        this.configuracoesActivity.getImgEditSenha().setOnClickListener(this);
        this.configuracoesActivity.getImgEdiAvatar().setOnClickListener(this);

        this.configuracoesActivity.getBtSalvar().setOnClickListener(this);
        this.configuracoesActivity.getBtModAvt().setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.imgEditNome:
                configuracoesActivity.exibirConteudoEscolha("nome");
                configuracoesActivity.inserirFocoImagem(1);
                break;
            case R.id.imgEditLogin:
                configuracoesActivity.exibirConteudoEscolha("login");
                configuracoesActivity.inserirFocoImagem(2);
                break;
            case R.id.imgEditSenha:
                configuracoesActivity.exibirConteudoEscolha("senha");
                configuracoesActivity.inserirFocoImagem(3);
                break;
            case R.id.imgEditAvatar:
                configuracoesActivity.exibirConteudoEscolha("avatar");
                configuracoesActivity.inserirFocoImagem(4);
                break;
            case R.id.btModificarAvatar:
                configuracoesActivity.escolherAvatar();
                break;
            case R.id.btSalvar:
                Toast.makeText(configuracoesActivity, "bt salvar", Toast.LENGTH_LONG).show();
                break;
        }
    }
}
