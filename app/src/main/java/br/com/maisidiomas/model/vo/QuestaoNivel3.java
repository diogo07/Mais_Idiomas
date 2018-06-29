package br.com.maisidiomas.model.vo;

public class QuestaoNivel3 {
    private int id, indiceResposta;
    private String [] palavras, traducoes;


    public QuestaoNivel3(int indiceResposta, String[] palavras, String[] traducoes) {
        this.indiceResposta = indiceResposta;
        this.palavras = palavras;
        this.traducoes = traducoes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIndiceResposta() {
        return indiceResposta;
    }

    public void setIndiceResposta(int indiceResposta) {
        this.indiceResposta = indiceResposta;
    }

    public String[] getPalavras() {
        return palavras;
    }

    public void setPalavras(String[] palavras) {
        this.palavras = palavras;
    }

    public String[] getTraducoes() {
        return traducoes;
    }

    public void setTraducoes(String[] traducoes) {
        this.traducoes = traducoes;
    }
}
