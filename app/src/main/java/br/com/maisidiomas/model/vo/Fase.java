package br.com.maisidiomas.model.vo;

import java.util.ArrayList;
import java.util.Collections;

public class Fase {

    private int id_usuario;
    private ArrayList<Questao> questoes;
    private ArrayList<Palavra> palavras;
    private int questaoAtual;
    private ArrayList<Opcao> questoesNivel3;

    public Fase(int id_usuario, int questaoAtual, ArrayList<Palavra> palavras) {
        this.id_usuario = id_usuario;
        this.questaoAtual = questaoAtual;
        this.palavras = palavras;
    }

    public Fase(int questaoAtual, ArrayList<Opcao> questoesNivel3) {
        this.questaoAtual = questaoAtual;
        this.questoesNivel3 = questoesNivel3;
    }

    public void gerarQuestoesNivel1(){
        questoes = new ArrayList<>();
        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(0);
        arr.add(1);
        arr.add(2);
        arr.add(3);
        Collections.shuffle(palavras);
        int indice = 1;
        ArrayList<Palavra> pl = new ArrayList<>();

        for(int i = 0; i <= 41; i++){
            pl.add(palavras.get(i));
            if(pl.size() == 4){
                Collections.shuffle(arr);
                questoes.add(new Questao("Selecione a imagem que corresponde a palavra:", arr.get(2), new Palavra[]{pl.get(0), pl.get(1), pl.get(2), pl.get(3)}));
                pl = new ArrayList<>();
            }
        }
    }

    public void gerarQuestoesNivel2(){
        questoes = new ArrayList<>();
        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(0);
        arr.add(1);
        arr.add(2);
        arr.add(3);
        Collections.shuffle(palavras);
        int indice = 1;
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


    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public ArrayList<Questao> getQuestoes() {
        return questoes;
    }

    public void setQuestoes(ArrayList<Questao> questoes) {
        this.questoes = questoes;
    }

    public int getQuestaoAtual() {
        return questaoAtual;
    }

    public void setQuestaoAtual(int questaoAtual) {
        this.questaoAtual = questaoAtual;
    }
}
