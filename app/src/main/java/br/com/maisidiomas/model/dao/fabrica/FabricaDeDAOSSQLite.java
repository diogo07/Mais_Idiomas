package br.com.maisidiomas.model.dao.fabrica;

import android.content.Context;

import br.com.maisidiomas.model.dao.sqlite.ConexaoSQLite;
import br.com.maisidiomas.model.dao.sqlite.FaseDAOSQLite;
import br.com.maisidiomas.model.dao.sqlite.PalavraDAOSQLite;
import br.com.maisidiomas.model.dao.sqlite.QuestaoNivel3DAOSQLite;
import br.com.maisidiomas.model.dao.sqlite.UsuarioDAOSQLite;

public class FabricaDeDAOSSQLite extends FabricaDeDAOS {

    @Override
    public UsuarioDAO criarUsuarioDAO(Context context) {
        return new UsuarioDAOSQLite(ConexaoSQLite.getInstance(context));
    }

    @Override
    public PalavraDAO criarPalavraDAO(Context context) {
        return new PalavraDAOSQLite(ConexaoSQLite.getInstance(context));
    }

    @Override
    public QuestaoNivel3DAO criarQuestaoNivel3DAO(Context context) {
        return new QuestaoNivel3DAOSQLite(ConexaoSQLite.getInstance(context));
    }

    @Override
    public FaseDAO criarFaseDAO(Context context) {
        return new FaseDAOSQLite(ConexaoSQLite.getInstance(context));
    }
}
