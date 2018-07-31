package br.com.maisidiomas.model.dao.firebase;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ConexaoFirebase {
    private static FirebaseDatabase database = FirebaseDatabase.getInstance();
    private static DatabaseReference conexao;

    public static DatabaseReference getInstace(){
        if (conexao == null) {
            conexao = database.getReference();
        }
        return conexao;
    }
}
