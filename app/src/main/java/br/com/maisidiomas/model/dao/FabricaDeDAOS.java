package br.com.maisidiomas.model.dao;

public abstract class FabricaDeDAOS{
    public abstract UsuarioDAO criarUsuarioDAO();
    public abstract FaseDAO criarFaseDAO();
}