package br.com.maisidiomas.utils;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import br.com.maisidiomas.model.vo.Usuario;

public class FirebaseConecty {

    private static FirebaseDatabase database = FirebaseDatabase.getInstance();
    private static DatabaseReference myRef;


    public static void salvar(Usuario usuario){
        usuario.setNome("iiiiiiiiiiiiii");
        System.out.println("FOI.....");
        myRef = database.getReference();
        myRef.child("usuarios").child(String.valueOf(usuario.getLogin())).setValue(usuario);

    }


    public static List<Usuario> getListUsuarios() {
        final List<Usuario> usuarios = new ArrayList<>();
        myRef = database.getReference().child("usuarios");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String key = dataSnapshot.getKey();
                System.out.println("key");
                System.out.println(key);
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {

                    String key2 = postSnapshot.getKey();
                    System.out.println("key2");
                    System.out.println(key2);
                    usuarios.add(postSnapshot.getValue(Usuario.class));

                }


                System.out.println(usuarios);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("eeeeeee", "Failed to read value.", error.toException());
            }
        });

        return usuarios;
    }
}
