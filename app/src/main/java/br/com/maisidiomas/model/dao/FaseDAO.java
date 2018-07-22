package br.com.maisidiomas.model.dao;

import java.util.ArrayList;

import br.com.maisidiomas.model.vo.Fase;

public abstract class FaseDAO {

    public abstract void insert(Fase fase) throws Exception;
    public abstract void update(Fase fase) throws Exception;
    public abstract void delete(Fase fase) throws Exception;
    public abstract Fase findById(int id) throws Exception;
    public abstract ArrayList<Fase> listByLevel(int level) throws Exception;
    public abstract ArrayList<Fase> listAll() throws Exception;
}
