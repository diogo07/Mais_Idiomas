package br.com.maisidiomas.model.dao;

import android.content.Context;

public abstract class FabricaDeDAOS{
    public abstract UsuarioDAO criarUsuarioDAO(Context context);
    public abstract FaseDAO criarFaseDAO(Context context);
    public abstract PalavraDAO criarPalavraDAO(Context context);
}