package br.com.maisidiomas.model.dao;

import java.util.ArrayList;

import br.com.maisidiomas.model.vo.Fase;

public abstract class FaseDAO {
    public abstract void insert(Fase fase) throws Exception;
    public abstract void update(Fase fase);
    public abstract Fase findById(int id) throws Exception;
    public abstract void remove(int id);
    public abstract ArrayList<Fase> listar();
    public abstract ArrayList<Fase> listarPorPontuacao();
}
