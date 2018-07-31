package br.com.maisidiomas.model.vo;

import java.util.ArrayList;

public class Ranking {
    private Usuario usuario;
    private int posicao;

    public Ranking(Usuario usuario, int posicao) {
        this.usuario = usuario;
        this.posicao = posicao;
    }


    public static ArrayList<Ranking> getListRanking(ArrayList<Usuario> usuarios){

        ArrayList<Ranking> lista = new ArrayList<>();
        int cont = 1;
        for(int i = usuarios.size()-1; i >=0; i--){
            if(cont <= 10){
                lista.add(new Ranking(usuarios.get(i), cont));
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

    public int getPosicao() {
        return posicao;
    }

}
