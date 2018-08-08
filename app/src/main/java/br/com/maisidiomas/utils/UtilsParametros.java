package br.com.maisidiomas.utils;

import android.content.Context;

import java.util.ArrayList;

import br.com.maisidiomas.controller.ControllerCadastro;
import br.com.maisidiomas.controller.ControllerDashBoard;
import br.com.maisidiomas.model.vo.Usuario;

public class UtilsParametros {

    private static Usuario usuarioLogado;
    private static Usuario usuarioCadastrado;
    private static Usuario usuarioAtualizado;
    private static ArrayList<Usuario> usuariosLista;
    private static ControllerDashBoard controllerDashBoard;
    private static Context context;
    private static int idCadastro;


    public static void carregarUsuario(Usuario usuario){
        usuarioLogado = usuario;
    }

    public static Usuario getUsuarioLogado(){
        return usuarioLogado;
    }

    public static void carregarListaUsuarios(ArrayList<Usuario> usuarios){
        usuariosLista = usuarios;
    }

    public static ArrayList<Usuario> getListaUsuarios(){
        return usuariosLista;
    }

    public static void adicionarControleDashBoard(ControllerDashBoard controller){
        controllerDashBoard = controller;
    }

    public static ControllerDashBoard getControllerDashBoard(){
        return controllerDashBoard;
    }

    public static void carregarUsuarioCadastrado(Usuario usuario){
        usuarioCadastrado = usuario;
    }

    public static Usuario getUsuarioCadastrado(){
        return usuarioCadastrado;
    }

    public static void carregarContexto(Context c){
        context = c;
    }

    public static Context getContext(){
        return context;
    }

    public static void carregarIdCadastro(int idCad){
        idCadastro =idCad;
    }

    public static int getIdCadastro(){
        return idCadastro;
    }

    public static void carregarUsuarioAtualizado(Usuario usuario){
        usuarioAtualizado = usuario;
    }

    public static Usuario getUsuarioAtualizado(){
        return  usuarioAtualizado;
    }
}
