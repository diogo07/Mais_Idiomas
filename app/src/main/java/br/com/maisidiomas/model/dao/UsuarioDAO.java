package br.com.maisidiomas.model.dao;

import java.util.ArrayList;

import br.com.maisidiomas.model.vo.Usuario;

public interface UsuarioDAO {

    public abstract void insert(Usuario usuario) throws Exception;
    public abstract void update(Usuario usuario) throws Exception;
    public abstract Usuario findById(int id) throws Exception;
    public abstract void remove(int id) throws Exception;
    public abstract ArrayList<Usuario> listar();
    public abstract ArrayList<Usuario> listarPorPontuacao();
    public abstract Usuario findByLogin(String login) throws Exception;
}
