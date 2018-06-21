package br.com.maisidiomas.model.vo;

public class Opcao {
    private String pergunta;
    private String opcoes[];
    private String traducoes[];
    private int numeroRespota;

    public Opcao(String pergunta, String[] opcoes, String[] traducoes, int numeroRespota) {
        this.pergunta = pergunta;
        this.opcoes = opcoes;
        this.traducoes = traducoes;
        this.numeroRespota = numeroRespota;
    }

    public String getPergunta() {
        return pergunta;
    }

    public void setPergunta(String pergunta) {
        this.pergunta = pergunta;
    }

    public String[] getOpcoes() {
        return opcoes;
    }

    public void setOpcoes(String[] opcoes) {
        this.opcoes = opcoes;
    }

    public int getNumeroRespota() {
        return numeroRespota;
    }

    public void setNumeroRespota(int numeroRespota) {
        this.numeroRespota = numeroRespota;
    }

    public String[] getTraducoes() {
        return traducoes;
    }

    public void setTraducoes(String[] traducoes) {
        this.traducoes = traducoes;
    }
}
