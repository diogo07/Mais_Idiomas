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

    private String retirarCaracteresEspeciais(String palavra) {
        String p1 = palavra.replace("ç", "c");
        String p2 = p1.replace("ã", "a");
        String p3 = p2.replace("ê", "e");
        String p4 = p3.replace("õ", "o");
        String p5 = p4.replace("á", "a");
        String p6 = p5.replace("é", "e");
        String p7 = p6.replace("í", "i");
        String p8 = p7.replace("ó", "o");
        String p9 = p8.replace("ú", "u");
        String p10 = p9.replace(" ", "_");
        String p = p10.toLowerCase();
        return "ic_"+p;
    }

    public String getTraducaoSemAcento(){
        return retirarCaracteresEspeciais(this.traducao);
    }

    public String getNomeSemAcento(){
        return retirarCaracteresEspeciais(this.nome);
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
