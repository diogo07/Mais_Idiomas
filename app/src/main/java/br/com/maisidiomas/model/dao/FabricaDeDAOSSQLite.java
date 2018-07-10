package br.com.maisidiomas.model.dao;

import android.content.Context;

public class FabricaDeDAOSSQLite extends FabricaDeDAOS {

    @Override
    public UsuarioDAO criarUsuarioDAO(Context context) {
        return new UsuarioDAOSQLite(ConexaoSQLite.getInstance(context));
    }

    @Override
    public FaseDAO criarFaseDAO(Context context) {
        return null;
    }

    @Override
    public PalavraDAO criarPalavraDAO(Context context) {
        return new PalavraDAOSQLite(ConexaoSQLite.getInstance(context));
    }
}
