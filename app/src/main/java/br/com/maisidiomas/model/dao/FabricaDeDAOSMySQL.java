package br.com.maisidiomas.model.dao;

public class FabricaDeDAOSMySQL extends FabricaDeDAOS {


    @Override
    public UsuarioDAO criarUsuarioDAO() {
        return null;
        //return new UsuarioDAOMySQL();
    }

    @Override
    public FaseDAO criarFaseDAO() {
        return null;
    }
}
