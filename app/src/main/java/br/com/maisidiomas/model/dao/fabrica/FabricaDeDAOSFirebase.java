package br.com.maisidiomas.model.dao.fabrica;

import android.content.Context;

import br.com.maisidiomas.model.dao.firebase.ConexaoFirebase;
import br.com.maisidiomas.model.dao.firebase.UsuarioDAOFirebase;

public class FabricaDeDAOSFirebase extends FabricaDeDAOS{

    @Override
    public UsuarioDAO criarUsuarioDAO(Context context) {
        return new UsuarioDAOFirebase(ConexaoFirebase.getInstace());
    }

    @Override
    public PalavraDAO criarPalavraDAO(Context context) {
        return null;
    }

    @Override
    public QuestaoNivel3DAO criarQuestaoNivel3DAO(Context context) {
        return null;
    }

    @Override
    public FaseDAO criarFaseDAO(Context context) {
        return null;
    }
}
