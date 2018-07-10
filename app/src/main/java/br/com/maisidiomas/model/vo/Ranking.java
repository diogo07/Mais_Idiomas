package br.com.maisidiomas.model.vo;

import java.util.ArrayList;

public class Ranking {
    private Usuario usuario;
    private int pontuacao;
    private int posicao;


    public Ranking() {
    }

    public Ranking(Usuario usuario, int pontuacao, int posicao) {
        this.usuario = usuario;
        this.pontuacao = pontuacao;
        this.posicao = posicao;
    }


    public static ArrayList<Ranking> getListRanking(ArrayList<Usuario> usuarios){

        ArrayList<Ranking> lista = new ArrayList<>();
        int cont = 1;
        for(int i = usuarios.size()-1; i >=0; i--){
            if(cont <= 10){
                lista.add(new Ranking(usuarios.get(i), usuarios.get(i).getPontuacao(), cont));
                cont++;
            }else{
                break;
            }
        }
        return lista;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    public int getPosicao() {
        return posicao;
    }

    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }
}
