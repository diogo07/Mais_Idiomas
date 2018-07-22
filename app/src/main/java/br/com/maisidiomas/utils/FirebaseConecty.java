package br.com.maisidiomas.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import br.com.maisidiomas.controller.ControllerLogin;
import br.com.maisidiomas.model.vo.Usuario;

public class FirebaseConecty {

    private static FirebaseDatabase database = FirebaseDatabase.getInstance();
    private static DatabaseReference myRef;


    public static void salvar(Usuario usuario){
        myRef = database.getReference();
        myRef.child("usuarios").child(String.valueOf(usuario.getLogin())).setValue(usuario);

    }

    public static void getListUsuarios() {

        if(isConected(UtilsParametros.getContext())){
            System.out.println("Tem conexao");
            final ProgressDialog progressDialog = ProgressDialog.show(UtilsParametros.getContext(), "", "Carregando ...", true);
            progressDialog.setCancelable(false);
            final ArrayList<Usuario> usuarios = new ArrayList<>();
            myRef = database.getReference().child("usuarios");
            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    usuarios.clear();
                    for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                        Usuario u = postSnapshot.getValue(Usuario.class);
                        usuarios.add(u);
                    }
                    UtilsParametros.carregarListaUsuarios(usuarios);
                    progressDialog.dismiss();
                    UtilsParametros.getControllerDashBoard().exibirRanking();
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    Log.w("Erro", "Failed to read value.", error.toException());
                }
            });
        }else{
            System.out.println("Não tem conexao");
            UtilsParametros.getControllerDashBoard().alertarFaltaDeConexao();
        }
    }

    public static void  getUsuario(final ControllerLogin controllerLogin, final String login, final String senha, final ProgressDialog progressDialog) {
        progressDialog.setCancelable(false);
        UtilsParametros.carregarContexto(controllerLogin.getLoginActivity());
        myRef = database.getReference().child("usuarios").child(login);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
               if(isConected(UtilsParametros.getContext())){
                   Usuario u = dataSnapshot.getValue(Usuario.class);
                   if(u != null){
                       if(u.getSenha().equalsIgnoreCase(senha)) {
                           System.out.println(u);
                           UtilsParametros.carregarUsuario(u);
                           progressDialog.dismiss();
                           controllerLogin.verificarUsuarioLogado();
                       }else{
                           progressDialog.dismiss();
                           controllerLogin.verificarUsuarioLogado();
                       }

                   }else {
                       progressDialog.dismiss();
                       controllerLogin.verificarUsuarioLogado();
                   }
               }else {
                   progressDialog.dismiss();
                   controllerLogin.verificarUsuarioLogado();
               }


            }


            @Override
            public void onCancelled(DatabaseError error) {
                Log.w("Erro", "Failed to read value.", error.toException());
            }
        });
    }

    public static void  getUsuarioByLogin(final String login) {
        final ProgressDialog progressDialog = ProgressDialog.show(UtilsParametros.getContext(), "", "Carregando ...", true);
        progressDialog.setCancelable(false);
        myRef = database.getReference().child("usuarios").child(login);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Usuario u = dataSnapshot.getValue(Usuario.class);
                if(u != null){
                    UtilsParametros.carregarUsuarioCadastrado(u);
                }
                progressDialog.dismiss();
            }


            @Override
            public void onCancelled(DatabaseError error) {
                Log.w("Erro", "Failed to read value.", error.toException());
            }
        });
    }

    public static boolean isConected(Context cont){
        ConnectivityManager conmag = (ConnectivityManager)cont.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = conmag.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        return isConnected;
    }
}
