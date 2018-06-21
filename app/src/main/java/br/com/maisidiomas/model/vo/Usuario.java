package br.com.maisidiomas.model.vo;

import java.util.ArrayList;

public class Usuario {

    private int id, pontuacao;
    private String login, senha, nome, foto;
    private ArrayList<Fase> fases;


    public Usuario(String login, String senha, String nome) {
        this.login = login;
        this.senha = senha;
        this.nome = nome;
        this.foto = "foto user";
    }

    public Usuario() {
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public ArrayList<Fase> getFases() {
        return fases;
    }
    public void setFases(ArrayList<Fase> fases) {
        this.fases = fases;
    }
    public String getFoto() {
        return foto;
    }
    public void setFoto(String foto) {
        this.foto = foto;
    }
    public int getPontuacao() {
        return pontuacao;
    }
    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }
}
