package br.com.maisidiomas.model.dao.fabrica;


import java.util.ArrayList;

import br.com.maisidiomas.model.vo.QuestaoNivel3;

public abstract class QuestaoNivel3DAO {

    public abstract void insert(QuestaoNivel3 questaoNivel3) throws Exception;
    public abstract void update(QuestaoNivel3 questaoNivel3) throws Exception;
    public abstract void delete(QuestaoNivel3 questaoNivel3) throws Exception;
    public abstract QuestaoNivel3 findById(int id) throws Exception;
    public abstract ArrayList<QuestaoNivel3> listar();
}
