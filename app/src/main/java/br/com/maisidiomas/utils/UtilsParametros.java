package br.com.maisidiomas.utils;

import java.util.ArrayList;

import br.com.maisidiomas.controller.ControllerDashBoard;
import br.com.maisidiomas.model.vo.Usuario;

public class UtilsParametros {

    private static Usuario usuarioLogado;
    private static ArrayList<Usuario> usuariosLista;
    private static ControllerDashBoard controllerDashBoard;



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

}