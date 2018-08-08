package br.com.maisidiomas.model.dao;

import android.app.ProgressDialog;
import android.content.Context;

import java.util.ArrayList;

import br.com.maisidiomas.controller.ControllerCadastro;
import br.com.maisidiomas.controller.ControllerConfiguracoes;
import br.com.maisidiomas.model.dao.fabrica.FabricaDeDAOS;
import br.com.maisidiomas.model.dao.fabrica.FabricaDeDAOSSQLite;
import br.com.maisidiomas.model.vo.Palavra;
import br.com.maisidiomas.model.vo.QuestaoNivel3;
import br.com.maisidiomas.model.vo.Ranking;
import br.com.maisidiomas.model.vo.Usuario;
import br.com.maisidiomas.utils.FirebaseConecty;
import br.com.maisidiomas.utils.UtilsParametros;

public class Fachada {

    public static final FabricaDeDAOS fabricaDeDAO = new FabricaDeDAOSSQLite();

    public static void inserirUsuario(Usuario usuario, Context context) throws Exception{
        FirebaseConecty.salvar(usuario);
        fabricaDeDAO.criarUsuarioDAO(context).insert(usuario);
    }

    public static void inserirQuestaoNivel3(QuestaoNivel3 questaoNivel3, Context context) throws Exception{
        fabricaDeDAO.criarQuestaoNivel3DAO(context).insert(questaoNivel3);
    }

    public static ArrayList<Usuario> listarUsuarios(Context context){
        return fabricaDeDAO.criarUsuarioDAO(context).listarPorPontuacao();
    }

    public static ArrayList<Palavra> listarPalavrasPorNivel(int nivel, Context context) throws Exception {
        return fabricaDeDAO.criarPalavraDAO(context).listByLevel(nivel);
    }

    public static String[] listarNomesPorNivel(int nivel, Context context) throws Exception {
        return fabricaDeDAO.criarPalavraDAO(context).listNomesByLevel(nivel);
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
        return fabricaDeDAO.criarQuestaoNivel3DAO(context).listar();
    }

    public static void atualizarUsuario(Context context, Usuario usuario) throws Exception {
        fabricaDeDAO.criarUsuarioDAO(context).update(usuario);
        UtilsParametros.carregarUsuario(usuario);
    }

    public static Usuario findByLogin(Context context, String login) throws Exception {
        return fabricaDeDAO.criarUsuarioDAO(context).findByLogin(login);
    }

    public static void loginDisponivel(Context context, String login, ControllerCadastro controllerCadastro){
        if(fabricaDeDAO.criarUsuarioDAO(context).loginDisponivel(login)){
            final ProgressDialog progressDialog = ProgressDialog.show(UtilsParametros.getContext(), "", "Verificando disponibilidade ...", true);
            FirebaseConecty.loginDisponivel(progressDialog, login, controllerCadastro);
        }else{
            controllerCadastro.getCadastroActivity().alertarLoginIndisponivel();
        }
    }

    public static void loginPodeAtualizar(Context context, String login, ControllerConfiguracoes controllerConfiguracoes){
        if(fabricaDeDAO.criarUsuarioDAO(context).loginDisponivel(login)){
            final ProgressDialog progressDialog = ProgressDialog.show(UtilsParametros.getContext(), "", "Verificando disponibilidade ...", true);
            FirebaseConecty.loginPodeSerAtualizado(progressDialog, login, controllerConfiguracoes);
        }else{
            controllerConfiguracoes.getConfiguracoesActivity().exibirMensagem("", "Este login não está disponível!");
        }
    }

    public static Usuario findByLoginEsenha(Context context, String login, String senha){
        return  fabricaDeDAO.criarUsuarioDAO(context).findByLoginEsenha(login, senha);
    }

    public static boolean questaoNivel3IsCadastrada(QuestaoNivel3 questaoNivel3, Context context){
        return fabricaDeDAO.criarQuestaoNivel3DAO(context).questaoIsCadastrada(questaoNivel3);
    }

    public static Palavra [] listarPalavras(String [] nomes, Context context){
        return fabricaDeDAO.criarPalavraDAO(context).listaPalavras(nomes);
    }

    public static boolean palavraIsCadastrada(Context context, String palavra) throws Exception {
        return fabricaDeDAO.criarPalavraDAO(context).palavraIsCadastra(palavra);
    }

    public static void inserirPalavra(Context context, Palavra palavra) throws Exception {
        fabricaDeDAO.criarPalavraDAO(context).insert(palavra);
    }

    public static ArrayList<Palavra> listByPalavraChave(String s, Context context) throws Exception {
        return fabricaDeDAO.criarPalavraDAO(context).listByPalavraChave(s);
    }
}
