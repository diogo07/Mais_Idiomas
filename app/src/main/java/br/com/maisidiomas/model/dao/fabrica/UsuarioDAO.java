package br.com.maisidiomas.model.dao.fabrica;

import java.util.ArrayList;

import br.com.maisidiomas.model.vo.Usuario;

public abstract class UsuarioDAO {

    public abstract void insert(Usuario usuario) throws Exception;
    public abstract void update(Usuario usuario) throws Exception;
    public abstract Usuario findById(int id) throws Exception;
    public abstract void remove(int id) throws Exception;
    public abstract ArrayList<Usuario> listar();
    public abstract ArrayList<Usuario> listarPorPontuacao();
    public abstract Usuario findByLogin(String login) throws Exception;
    public abstract Usuario findByLoginEsenha(String login, String senha);

    public boolean loginDisponivel(String login){
        try {
            if(findByLogin(login) == null){
                return true;
            }else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }
}
