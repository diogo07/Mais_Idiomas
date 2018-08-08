package br.com.maisidiomas.model.dao.fabrica;

import android.content.Context;

public abstract class FabricaDeDAOS{

    public abstract UsuarioDAO criarUsuarioDAO(Context context);
    public abstract PalavraDAO criarPalavraDAO(Context context);
    public abstract QuestaoNivel3DAO criarQuestaoNivel3DAO(Context context);
    public abstract FaseDAO criarFaseDAO(Context context);
}