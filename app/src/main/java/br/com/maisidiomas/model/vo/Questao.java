package br.com.maisidiomas.model.vo;


import br.com.maisidiomas.model.dao.Fachada;
import br.com.maisidiomas.utils.UtilsParametros;

public class Questao {

    private String pergunta;
    private int numeroResposta;
    private Palavra[] palavras;
    private String [] nomes;

    public Questao(String pergunta, int numeroResposta, Palavra[] palavras) {
        this.pergunta = pergunta;
        this.numeroResposta = numeroResposta;
        this.palavras = palavras;
    }

    public Questao(String pergunta, int numeroResposta, String[] nomes) {
        this.pergunta = pergunta;
        this.numeroResposta = numeroResposta;
        this.nomes = nomes;
    }

    public String getPergunta() {
        return pergunta;
    }

    public int getNumeroResposta() {
        return numeroResposta;
    }

    public Palavra[] getPalavras() {
        if(palavras == null){
            palavras = Fachada.listarPalavras(nomes, UtilsParametros.getContext());
        }
        return palavras;
    }

}
