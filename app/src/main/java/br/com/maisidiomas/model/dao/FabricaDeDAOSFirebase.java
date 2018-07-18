package br.com.maisidiomas.model.dao;

import android.content.Context;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
}
