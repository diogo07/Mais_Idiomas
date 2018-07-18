package br.com.maisidiomas.model.vo;

import java.util.ArrayList;

import br.com.maisidiomas.model.dao.UsuarioDAOSQLite;

public class Ranking {
    private Usuario usuario;
    private int posicao;
    private int id_usuario;


    public Ranking() {

    }

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
        if(usuario == null){
            //this.usuario = new UsuarioDAOSQLite().findById(id_usuario);
        }
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public int getPosicao() {
        return posicao;
    }

    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }
}
