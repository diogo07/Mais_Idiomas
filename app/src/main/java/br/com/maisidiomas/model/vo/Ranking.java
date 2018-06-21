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


    public static ArrayList<Ranking> getListRanking(){
        Usuario usuario1 = new Usuario("diogo", "diogo", "Diogo");
        usuario1.setFoto("ic_avatar8");
        Ranking ranking1 = new Ranking(usuario1, 300, 8);

        Usuario usuario2 = new Usuario("sousa", "andre", "Sousa");
        usuario2.setFoto("ic_avatar3");
        Ranking ranking2 = new Ranking(usuario2, 400, 7);

        Usuario usuario4 = new Usuario("andre", "andre", "André");
        usuario4.setFoto("ic_avatar4");
        Ranking ranking4 = new Ranking(usuario4, 500, 6);

        Usuario usuario5 = new Usuario("jose", "jose", "José");
        usuario5.setFoto("ic_avatar5");
        Ranking ranking5 = new Ranking(usuario5, 600, 5);

        Usuario usuario6 = new Usuario("luiz", "luiz", "Luiz");
        usuario6.setFoto("ic_avatar6");
        Ranking ranking6 = new Ranking(usuario6, 700, 4);

        Usuario usuario7 = new Usuario("sebastiao", "sebastiao", "Sebastião");
        usuario7.setFoto("ic_avatar8");
        Ranking ranking7 = new Ranking(usuario7, 800, 3);

        Usuario usuario8 = new Usuario("isabel", "isabel", "Isabel");
        usuario8.setFoto("ic_avatar2");
        Ranking ranking8 = new Ranking(usuario8, 900, 2);

        Usuario usuario9 = new Usuario("maria", "maria", "Maria");
        usuario9.setFoto("ic_avatar7");
        Ranking ranking9 = new Ranking(usuario9, 1000, 1);

        ArrayList<Ranking> lista = new ArrayList<>();
        lista.add(ranking9);
        lista.add(ranking8);
        lista.add(ranking7);
        lista.add(ranking6);
        lista.add(ranking5);
        lista.add(ranking4);
        lista.add(ranking2);
        lista.add(ranking1);

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
