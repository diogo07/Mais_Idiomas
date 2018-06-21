package br.com.maisidiomas.model.vo;

public class Palavra {

    private int id;
    private int nivel;
    private String nome;
    private String traducao;

    public Palavra(int nivel, String nome, String traducao) {
        this.nivel = nivel;
        this.nome = nome;
        this.traducao = traducao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTraducao() {
        return traducao;
    }

    public void setTraducao(String traducao) {
        this.traducao = traducao;
    }
}
