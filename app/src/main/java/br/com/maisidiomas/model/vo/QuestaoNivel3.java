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

    public String[] getPalavras() {
        return palavras;
    }

    public String[] getTraducoes() {
        return traducoes;
    }

}
