package br.com.maisidiomas.model.vo;


public class Questao {

    private String pergunta;
    private int numeroResposta;
    private Palavra[] palavras;

    public Questao(String pergunta, int numeroResposta) {
        this.pergunta = pergunta;
        this.numeroResposta = numeroResposta;
    }

    public Questao(String pergunta, int numeroResposta, Palavra[] palavras) {
        this.pergunta = pergunta;
        this.numeroResposta = numeroResposta;
        this.palavras = palavras;
    }

    public String getPergunta() {
        return pergunta;
    }

    public void setPergunta(String pergunta) {
        this.pergunta = pergunta;
    }

    public int getNumeroResposta() {
        return numeroResposta;
    }

    public void setNumeroResposta(int numeroResposta) {
        this.numeroResposta = numeroResposta;
    }

    public Palavra[] getPalavras() {
        return palavras;
    }

    public void setPalavras(Palavra[] palavras) {
        this.palavras = palavras;
    }
}
