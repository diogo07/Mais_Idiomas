package br.com.maisidiomas.model.dao;

import android.app.ProgressDialog;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import br.com.maisidiomas.controller.ControllerCadastro;
import br.com.maisidiomas.model.vo.Palavra;
import br.com.maisidiomas.model.vo.QuestaoNivel3;
import br.com.maisidiomas.model.vo.Ranking;
import br.com.maisidiomas.model.vo.Usuario;
import br.com.maisidiomas.utils.FirebaseConecty;
import br.com.maisidiomas.utils.UtilsParametros;

public class Fachada {

    public static final FabricaDeDAOSSQLite fabricaDeDAOSSQLite= new FabricaDeDAOSSQLite();

    public static void inserirUsuario(Usuario usuario, Context context) throws Exception{
        FirebaseConecty.salvar(usuario);
        fabricaDeDAOSSQLite.criarUsuarioDAO(context).insert(usuario);
    }

    public static void inserirPalavra(Palavra palavra, Context context) throws Exception{
        fabricaDeDAOSSQLite.criarPalavraDAO(context).insert(palavra);
    }

    public static void inserirQuestaoNivel3(QuestaoNivel3 questaoNivel3, Context context) throws Exception{
        fabricaDeDAOSSQLite.criarQuestaoNivel3DAO(context).insert(questaoNivel3);
    }

    public static ArrayList<Usuario> listarUsuarios(Context context){
        return fabricaDeDAOSSQLite.criarUsuarioDAO(context).listarPorPontuacao();
    }

    public static ArrayList<Palavra> listarPalavrasPorNivel(int nivel, Context context) throws Exception {
        return fabricaDeDAOSSQLite.criarPalavraDAO(context).listByLevel(nivel);
    }

    public static ArrayList<Ranking> getListRanking(Context context){
        ArrayList<Ranking> lista = new ArrayList<>();
        ArrayList<Usuario> usuarios = listarUsuarios(context);
        int cont = 1;
        for(int i = usuarios.size()-1; i >=0; i--){
            if(cont <= 10){
                lista.add(new Ranking(usuarios.get(i), cont));
                cont++;
            }else{
                break;
            }
        }
        return lista;
    }

    public static ArrayList<QuestaoNivel3> listarQuestoesNivel3(Context context){
        return fabricaDeDAOSSQLite.criarQuestaoNivel3DAO(context).listar();
    }



    public static void atualizarUsuario(Context context, Usuario usuario) throws Exception {
        fabricaDeDAOSSQLite.criarUsuarioDAO(context).update(usuario);
        FirebaseConecty.salvar(usuario);
    }

    public static Usuario findByLogin(Context context, String login) throws Exception {
        return fabricaDeDAOSSQLite.criarUsuarioDAO(context).findByLogin(login);
    }

    public static void loginDisponivel(Context context, String login, ControllerCadastro controllerCadastro){
        final ProgressDialog progressDialog = ProgressDialog.show(UtilsParametros.getContext(), "", "Verificando disponibilidade ...", true);
        FirebaseConecty.loginDisponivel(progressDialog, login, controllerCadastro);
    }

    public static Usuario findByLoginEsenha(Context context, String login, String senha){
        return  ((UsuarioDAOSQLite) fabricaDeDAOSSQLite.criarUsuarioDAO(context)).findByLoginEsenha(login, senha);
    }

}
