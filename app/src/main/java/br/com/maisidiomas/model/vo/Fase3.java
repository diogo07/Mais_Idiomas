package br.com.maisidiomas.model.vo;

import java.util.ArrayList;
import java.util.Collections;

public class Fase3 {
    private ArrayList<QuestaoNivel3> questoes;
    private int questaoAtual;

    public Fase3(ArrayList<QuestaoNivel3> questoes) {
        this.questoes = questoes;
        this.questaoAtual = 1;
        embaralharQuestoes();
    }


    public void embaralharQuestoes(){
        Collections.shuffle(questoes);
    }

    public ArrayList<QuestaoNivel3> getQuestoes() {
        return questoes;
    }

    public void setQuestoes(ArrayList<QuestaoNivel3> questoes) {
        this.questoes = questoes;
    }

    public int getQuestaoAtual() {
        return questaoAtual;
    }

    public void setQuestaoAtual(int questaoAtual) {
        this.questaoAtual = questaoAtual;
    }
}
