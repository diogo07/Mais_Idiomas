package br.com.maisidiomas.model.dao;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import br.com.maisidiomas.model.vo.Usuario;

public class UsuarioDAOFirebase extends UsuarioDAO {

    private DatabaseReference databaseReference;

    public UsuarioDAOFirebase(DatabaseReference databaseReference) {
        this.databaseReference = databaseReference;
    }

    @Override
    public void insert(Usuario usuario) throws Exception {
        this.databaseReference.child("usuarios").child(String.valueOf(usuario.getLogin())).setValue(usuario);
    }

    @Override
    public void update(Usuario usuario) throws Exception {

    }

    @Override
    public Usuario findById(int id) throws Exception {
        return null;
    }

    @Override
    public void remove(int id) throws Exception {

    }

    @Override
    public ArrayList<Usuario> listar() {
        final ArrayList<Usuario> usuarios = new ArrayList<>();
        this.databaseReference.child("usuarios");
        this.databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String key = dataSnapshot.getKey();
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                   usuarios.add(postSnapshot.getValue(Usuario.class));
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                Log.w("Erro", "Failed to read value.", error.toException());
            }
        });

        return usuarios;
    }

    @Override
    public ArrayList<Usuario> listarPorPontuacao() {
        ArrayList<Usuario> usuarios = listar();
        Usuario aux;

        for(int i = 0; i < usuarios.size(); i++){
            for(int j = 0; j<i; j++){
                if(usuarios.get(j).getPontuacao() > usuarios.get(j+1).getPontuacao()){
                    aux = usuarios.get(j);
                    usuarios.get(j).setNome(usuarios.get(j+1).getNome());
                    usuarios.get(j).setId(usuarios.get(j+1).getId());
                    usuarios.get(j).setLogin(usuarios.get(j+1).getLogin());
                    usuarios.get(j).setSenha(usuarios.get(j+1).getSenha());
                    usuarios.get(j).setPontuacao(usuarios.get(j+1).getPontuacao());
                    usuarios.get(j).setFoto(usuarios.get(j+1).getFoto());

                    usuarios.get(j+1).setNome(aux.getNome());
                    usuarios.get(j+1).setId(aux.getId());
                    usuarios.get(j+1).setLogin(aux.getLogin());
                    usuarios.get(j+1).setPontuacao(aux.getPontuacao());
                    usuarios.get(j+1).setFoto(aux.getFoto());
                }
            }
        }
        return usuarios;
    }

    @Override
    public Usuario findByLogin(String login) throws Exception {
        return null;
    }
}
