package br.com.maisidiomas.model.dao;

import java.util.ArrayList;

import br.com.maisidiomas.model.vo.Usuario;

public interface UsuarioDAO {

    public abstract boolean insert(Usuario usuario) throws Exception;
    public abstract boolean update(Usuario usuario);
    public abstract Usuario findById(int id) throws Exception;
    public abstract boolean remove(int id);
    public abstract ArrayList<Usuario> listar();
    public abstract ArrayList<Usuario> listarPorPontuacao();
}
