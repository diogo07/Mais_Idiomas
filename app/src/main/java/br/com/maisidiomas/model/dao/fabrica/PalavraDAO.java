package br.com.maisidiomas.model.dao.fabrica;

import java.util.ArrayList;

import br.com.maisidiomas.model.vo.Palavra;

public abstract class PalavraDAO {

    public abstract void insert(Palavra palavra) throws Exception;
    public abstract void update(Palavra palavra) throws Exception;
    public abstract void delete(Palavra palavra) throws Exception;
    public abstract Palavra findById(int id) throws Exception;
    public abstract ArrayList<Palavra> listByLevel(int level) throws Exception;
    public abstract ArrayList<Palavra> listAll() throws Exception;
    public abstract boolean palavraIsCadastra(String palavra) throws Exception;
    public abstract ArrayList<Palavra> listByPalavraChave(String palavraChave) throws Exception;
}
