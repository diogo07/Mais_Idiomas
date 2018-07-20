package br.com.maisidiomas.controller;

import android.view.View;

import br.com.maisidiomas.R;
import br.com.maisidiomas.model.dao.Fachada;
import br.com.maisidiomas.model.vo.Usuario;
import br.com.maisidiomas.utils.FirebaseConecty;
import br.com.maisidiomas.utils.UtilsParametros;
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
                salvar();
                break;
        }
    }

    public void salvar(){
        int alternativa = configuracoesActivity.getAlternativaSelecionada();

        if(alternativa == 0){
            if(!configuracoesActivity.getEdtEscolha().getText().toString().equals("")){
                Usuario usuario = Fachada.findByLogin(configuracoesActivity, configuracoesActivity.getLogin());
                usuario.setNome(configuracoesActivity.getEdtEscolha().getText().toString());
                try {
                    Fachada.atualizarUsuario(configuracoesActivity, usuario);
                    UtilsParametros.carregarUsuario(usuario);
                    configuracoesActivity.exibirMensagem("", "Nome atualizado com sucesso!");
                    configuracoesActivity.getEdtEscolha().setText("");
                } catch (Exception e) {
                    configuracoesActivity.exibirMensagem("Erro", "Não foi possível alterar o seu nome!");
                }
            }else{
                configuracoesActivity.exibirMensagem("Erro", "Campo nome não preenchido!");
            }
        }else if(alternativa == 1){
            if(!configuracoesActivity.getEdtEscolha().getText().toString().equals("")){
                if(FirebaseConecty.isConected(configuracoesActivity)){
                    Usuario usuario = Fachada.findByLogin(configuracoesActivity, configuracoesActivity.getLogin());
                    usuario.setLogin(configuracoesActivity.getEdtEscolha().getText().toString());
                    try {
                        Fachada.atualizarUsuario(configuracoesActivity, usuario);
                        UtilsParametros.carregarUsuario(usuario);
                        configuracoesActivity.exibirMensagem("", "Login alterado com sucesso!");
                        configuracoesActivity.getEdtEscolha().setText("");
                    } catch (Exception e) {
                        configuracoesActivity.exibirMensagem("Erro", "O sistema não conseguiu atualizar seu login!");
                    }
                }else{
                    configuracoesActivity.exibirMensagem("Erro", "Vocé precisa ter conexão com a internet para alterar seu login!");
                }
            }else{
                configuracoesActivity.exibirMensagem("Erro", "Campo login não preenchido!");
            }
        }else if(alternativa == 2){
            if(!configuracoesActivity.getEdtEscolha().getText().toString().equals("") || !configuracoesActivity.getEdtSenhaAtual().getText().toString().equals("")){
                Usuario usuario = Fachada.findByLogin(configuracoesActivity, configuracoesActivity.getLogin());
                usuario.setSenha(configuracoesActivity.getEdtEscolha().getText().toString());
                try {
                    Fachada.atualizarUsuario(configuracoesActivity, usuario);
                    UtilsParametros.carregarUsuario(usuario);
                    configuracoesActivity.exibirMensagem("", "Senha atualizada com sucesso!");
                    configuracoesActivity.getEdtEscolha().setText("");
                    configuracoesActivity.getEdtSenhaAtual().setText("");
                } catch (Exception e) {
                    configuracoesActivity.exibirMensagem("Erro", "Não foi possível alterar sua senha!");
                }
            }else{
                configuracoesActivity.exibirMensagem("Erro", "Campo senha atual e/ou nova senha não preenchido!");
            }
        }else{
            if(!configuracoesActivity.getAvtUser().equals(configuracoesActivity.getAvatar())){
                Usuario usuario = Fachada.findByLogin(configuracoesActivity, configuracoesActivity.getLogin());
                usuario.setFoto(configuracoesActivity.getAvatar());
                try {
                    Fachada.atualizarUsuario(configuracoesActivity, usuario);
                    UtilsParametros.carregarUsuario(usuario);
                    configuracoesActivity.exibirMensagem("", "Avatar atualizado com sucesso!");
                } catch (Exception e) {
                    configuracoesActivity.exibirMensagem("Erro", "Não foi possível alterar o seu avatar!");
                }
            }else {
                configuracoesActivity.exibirMensagem("Erro", "Novo avatar deve ser diferente do atual!");
            }

        }
    }
}
