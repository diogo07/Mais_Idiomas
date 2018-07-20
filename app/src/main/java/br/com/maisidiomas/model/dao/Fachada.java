package br.com.maisidiomas.model.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import br.com.maisidiomas.model.vo.Palavra;
import br.com.maisidiomas.model.vo.QuestaoNivel3;
import br.com.maisidiomas.model.vo.Ranking;
import br.com.maisidiomas.model.vo.Usuario;
import br.com.maisidiomas.utils.FirebaseConecty;
import br.com.maisidiomas.utils.UtilsParametros;

public class Fachada {

    public static final FabricaDeDAOSSQLite fabricaDeDAOSSQLite= new FabricaDeDAOSSQLite();

    public static void inserirUsuario(Usuario usuario, Context context) throws Exception{
        new UsuarioDAOSQLite(ConexaoSQLite.getInstance(context)).insert(usuario);
    }

    public static void inserirPalavra(Palavra palavra, Context context) throws Exception{
        new PalavraDAOSQLite(ConexaoSQLite.getInstance(context)).insert(palavra);
    }

    public static void inserirQuestaoNivel3(QuestaoNivel3 questaoNivel3, Context context) throws Exception{
        new QuestaoNivel3DAOSQLite(ConexaoSQLite.getInstance(context)).insert(questaoNivel3);
    }



    public static ArrayList<Usuario> listarUsuarios(Context context){
        return new UsuarioDAOSQLite(ConexaoSQLite.getInstance(context)).listarPorPontuacao();
    }

    public static ArrayList<Palavra> listarPalavrasPorNivel(int nivel, Context context) throws Exception {
        return new PalavraDAOSQLite(ConexaoSQLite.getInstance(context)).listByLevel(nivel);
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



    public static void atualizarUsuario(Context context, Usuario usuario) throws Exception {
        new UsuarioDAOSQLite(ConexaoSQLite.getInstance(context)).update(usuario);
        FirebaseConecty.salvar(usuario);
    }

    public static Usuario findByLogin(Context context, String login){
        return new UsuarioDAOSQLite(ConexaoSQLite.getInstance(context)).findByLogin(login);
    }

    public static boolean loginDisponivel(Context context, String login){
        if(fabricaDeDAOSSQLite.criarUsuarioDAO(context).loginDisponivel(login)){
            FirebaseConecty.getUsuarioByLogin(login);
            if(UtilsParametros.getUsuarioCadastrado() != null){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }

    public static Usuario findByLoginEsenha(Context context, String login, String senha){
        UsuarioDAOSQLite usuarioDAO = (UsuarioDAOSQLite) fabricaDeDAOSSQLite.criarUsuarioDAO(context);
        return usuarioDAO.findByLoginEsenha(login, senha);
    }

}
