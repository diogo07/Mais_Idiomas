package br.com.maisidiomas.model.vo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import br.com.maisidiomas.model.dao.Fachada;
import br.com.maisidiomas.utils.UtilsParametros;

public class Fase {

    private String login;
    private Usuario usuario;
    private ArrayList<Questao> questoes;
    private ArrayList<Palavra> palavras;
    private int questaoAtual;
    private int pontuacao;
    private String [] nomes;

    public Fase(String login_usuario, int questaoAtual, ArrayList<Palavra> palavras) {
        this.login = login_usuario;
        this.questaoAtual = questaoAtual;
        this.palavras = palavras;
        this.pontuacao = 0;
    }

    public Fase(String login_usuario, int questaoAtual, String[] nomes) {
        this.login = login_usuario;
        this.questaoAtual = questaoAtual;
        this.nomes = nomes;
        this.pontuacao = 0;
    }

    public void gerarQuestoesNivel1(){
        questoes = new ArrayList<>();
        int indicePalavraEscolhida =new Random().nextInt(4);//maximo de opcoes
        ArrayList<String> p = gerarArrayListPalavras(nomes);
        Collections.shuffle(p);
        ArrayList<String> pl = new ArrayList<>();

        for(int i = 0; i <= 41; i++){
            pl.add(p.get(i));
            if(pl.size() == 4){
                questoes.add(new Questao("Selecione a imagem que corresponde a palavra:", indicePalavraEscolhida, new String[]{pl.get(0), pl.get(1), pl.get(2), pl.get(3)}));
                pl = new ArrayList<>();
            }
        }
    }

    private ArrayList<String> gerarArrayListPalavras(String[] nomes) {
        ArrayList<String> pls = new ArrayList<>();
        for(int i = 0; i < nomes.length; i++){
            pls.add(nomes[i]);
        }

        return pls;
    }

    public void gerarQuestoesNivel2(){
        questoes = new ArrayList<>();
        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(0);
        arr.add(1);
        arr.add(2);
        arr.add(3);
        Collections.shuffle(palavras);
        ArrayList<Palavra> pl = new ArrayList<>();

        for(int i = 0; i <= 25; i++){
            pl.add(palavras.get(i));
            if(pl.size() == 4){
                Collections.shuffle(arr);
                questoes.add(new Questao("Selecione a imagem que representa a frase:", arr.get(1), new Palavra[]{pl.get(0), pl.get(1), pl.get(2), pl.get(3)}));
                pl = new ArrayList<>();
            }
        }
    }

    public boolean verificarAlternativa(int alternativaMarcada){
        if(alternativaMarcada == questoes.get(questaoAtual).getNumeroResposta()){
            return true;
        }else{
            return false;
        }
    }

    public Usuario getUsuario() {
        if(usuario == null){
            try {
                usuario = Fachada.findByLogin(UtilsParametros.getContext(), login);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public ArrayList<Questao> getQuestoes() {
        return questoes;
    }

    public int getQuestaoAtual() {
        return questaoAtual;
    }

    public void setQuestaoAtual(int questaoAtual) {
        this.questaoAtual = questaoAtual;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }
}
