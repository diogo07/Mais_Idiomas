package br.com.maisidiomas.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import br.com.maisidiomas.controller.ControllerDashBoard;
import br.com.maisidiomas.controller.ControllerLogin;
import br.com.maisidiomas.model.vo.Usuario;
import br.com.maisidiomas.view.DashBoardActivity;
import br.com.maisidiomas.view.LoginActivity;

public class FirebaseConecty {

    private static FirebaseDatabase database = FirebaseDatabase.getInstance();
    private static DatabaseReference myRef;


    public static void salvar(Usuario usuario){
        myRef = database.getReference();
        myRef.child("usuarios").child(String.valueOf(usuario.getLogin())).setValue(usuario);

    }

    public static void getListUsuarios() {

        if(isConected(UtilsParametros.getControllerDashBoard().getDashBoardActivity())){
            System.out.println("Tem conexao");
            final ProgressDialog progressDialog = ProgressDialog.show(UtilsParametros.getControllerDashBoard().getDashBoardActivity(), "", "Carregando ...", true);
            progressDialog.setCancelable(true);
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

    public static void  getUsuario(final LoginActivity loginActivity, final ProgressDialog progressDialog , final String login, final String senha) {

        myRef = database.getReference().child("usuarios").child(login);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Usuario u = dataSnapshot.getValue(Usuario.class);
                    if(u.getSenha().equalsIgnoreCase(senha)){
                        System.out.println("OPA");
                        System.out.println(u);
                        UtilsParametros.carregarUsuario(u);
                        loginActivity.startActivity(new Intent(loginActivity, DashBoardActivity.class));
                        loginActivity.finish();

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
