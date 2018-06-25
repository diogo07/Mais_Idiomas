package br.com.maisidiomas.model.dao;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import br.com.maisidiomas.model.vo.Usuario;
import br.com.maisidiomas.view.DashBoardActivity;
import br.com.maisidiomas.view.LoginActivity;

public class UsuarioDAOMySQL extends AsyncTask<String, Void, String> implements UsuarioDAO{

    private Context context;
    private Usuario usuario;

    public UsuarioDAOMySQL(Context context) {
        this.context = context;
    }

    @Override
    public void insert(Usuario usuario) throws Exception {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.isConnected()){
            String [] dados = new String[]{"http://192.168.1.100/maisidiomas/insert_user.php", "nome="+
                    usuario.getNome()+"&login="+usuario.getLogin()+"&senha="+usuario.getSenha()+
                    "&avatar="+usuario.getFoto()};
            execute(dados);
            return;
        }else{
            throw new Exception("Erro ao inserir usuário");
        }
    }

    @Override
    public void update(Usuario usuario) {
        return;
    }

    @Override
    public Usuario findById(int id) throws Exception {
        return null;
    }

    @Override
    public void remove(int id) {
        return;
    }

    @Override
    public ArrayList<Usuario> listar() {
        return null;
    }

    @Override
    public ArrayList<Usuario> listarPorPontuacao() {
        return null;
    }

    public Usuario findByLoginEsenha(String login, String senha){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.isConnected()){
            String [] dados = new String[]{"http://192.168.0.103/maisidiomas/verificar_login.php", "login="+login+"&senha="+senha};
            execute(dados);

            usuario = new Usuario();
            if(usuario != null){
                usuario.setLogin(login);
                usuario.setSenha(senha);
                return usuario;
            }else{
                return null;
            }
        }else{
            return null;
        }
    }

    @Override
    protected String doInBackground(String... strings) {
        return ConexaoMySQL.postDados(strings[0], strings[1]);

    }
    @Override
    protected void onPostExecute(String resultado){

        if(!resultado.equals("dados_vazios") && !resultado.equals("erro")){
            String resposta = resultado;
            String array[];
            array = resposta.split("-");
            usuario = new Usuario();
            usuario.setNome(array[0]);
            usuario.setFoto(array[1]);
            usuario.setLogin(array[2]);
            String texto = array[3].toString();
            String id = texto.replace(" ", "");

            ((LoginActivity) context).exibirMensagem("res = "+(Integer.parseInt(id)));

            /*usuario.setId(Integer.parseInt(id));

            Intent i = new Intent(context, DashBoardActivity.class);
            i.putExtra("nome", usuario.getNome());
            i.putExtra("login", usuario.getLogin());
            i.putExtra("id", ""+usuario.getId());
            context.startActivity(i);*/

        }
    }
}
