package br.com.maisidiomas.model.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import br.com.maisidiomas.model.vo.Palavra;
import br.com.maisidiomas.model.vo.Usuario;

public class UsuarioDAOSQLite implements UsuarioDAO{

    private SQLiteDatabase sqLiteDatabase;


    public UsuarioDAOSQLite(SQLiteDatabase sqLiteDatabase) {
        this.sqLiteDatabase = sqLiteDatabase;
    }


    @Override
    public void insert(Usuario usuario) throws Exception{
        ContentValues values = new ContentValues();
        values.put("nome", usuario.getNome());
        values.put("login", usuario.getLogin());
        values.put("senha", usuario.getSenha());
        values.put("pontuacao", usuario.getPontuacao());
        values.put("foto", usuario.getFoto());
        if (this.sqLiteDatabase.insert("usuario", null, values) > 0) {
            this.sqLiteDatabase.close();
            return;
        } else {
            this.sqLiteDatabase.close();
            throw new Exception("Erro ao inserir usuário");
        }
    }

    @Override
    public void update(Usuario usuario) throws Exception{

        ContentValues values = new ContentValues();
        values.put("nome", usuario.getNome());
        values.put("login", usuario.getLogin());
        values.put("senha", usuario.getSenha());
        values.put("pontuacao", usuario.getPontuacao());
        values.put("foto", usuario.getFoto());
        String where = "id = ?";

        String argumentos[] = { usuario.getId()+"" };

        if (sqLiteDatabase.update("usuario", values, where, argumentos) > 0) {
            sqLiteDatabase.close();
            return;
        } else {
            sqLiteDatabase.close();
            throw new Exception("Erro: não foi possível atualizar");
        }
    }

    @Override
    public Usuario findById(int id) throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append("select * from usuario where id = "+id+";");
        Cursor cursor = this.sqLiteDatabase.rawQuery(sql.toString(), null);

        if(cursor.moveToFirst()){
            Usuario usuario = new Usuario(cursor.getString(cursor.getColumnIndex("login")), cursor.getString(cursor.getColumnIndex("senha")), cursor.getString(cursor.getColumnIndex("nome")));
            usuario.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex("id"))));
            usuario.setFoto(cursor.getString(cursor.getColumnIndex("foto")));
            usuario.setPontuacao(Integer.parseInt(cursor.getString(cursor.getColumnIndex("pontuacao"))));
            return usuario;
        }else{
            return null;
        }
    }

    @Override
    public void remove(int id) throws Exception {
        return;
    }

    @Override
    public ArrayList<Usuario> listar() {
        return null;
    }

    @Override
    public ArrayList<Usuario> listarPorPontuacao() {
        ArrayList<Usuario> usuarios = new ArrayList<>();

        StringBuilder sql_listar = new StringBuilder();
        sql_listar.append("select * from usuario order by pontuacao;");
        Cursor cursor = sqLiteDatabase.rawQuery(sql_listar.toString(), null);

        if(cursor.moveToFirst()){
            do{
                try{
                    Usuario usuario = new Usuario(cursor.getString(cursor.getColumnIndex("login")), cursor.getString(cursor.getColumnIndex("senha")), cursor.getString(cursor.getColumnIndex("nome")));
                    usuario.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex("id"))));
                    usuario.setFoto(cursor.getString(cursor.getColumnIndex("foto")));
                    usuario.setPontuacao(Integer.parseInt(cursor.getString(cursor.getColumnIndex("pontuacao"))));
                    usuarios.add(usuario);
                }catch (Exception e){
                    return null;
                }
            }while (cursor.moveToNext());
            return usuarios;

        }else{
            return null;
        }
    }

    public Usuario findByLogin(String login){
        StringBuilder sql = new StringBuilder();
        sql.append("select * from usuario where login = '"+login+"';");
        Cursor cursor = this.sqLiteDatabase.rawQuery(sql.toString(), null);

        if(cursor.moveToFirst()){
            Usuario usuario = new Usuario(cursor.getString(cursor.getColumnIndex("login")), cursor.getString(cursor.getColumnIndex("senha")), cursor.getString(cursor.getColumnIndex("nome")));
            usuario.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex("id"))));
            usuario.setFoto(cursor.getString(cursor.getColumnIndex("foto")));
            usuario.setPontuacao(Integer.parseInt(cursor.getString(cursor.getColumnIndex("pontuacao"))));
            return usuario;
        }else{
           return null;
        }
    }

    public Usuario findByLoginEsenha(String login, String senha){
        StringBuilder sql = new StringBuilder();
        sql.append("select * from usuario where login = '"+login+"' and senha = '"+senha+"';");
        Cursor cursor = this.sqLiteDatabase.rawQuery(sql.toString(), null);

        if(cursor.moveToFirst()){
            Usuario usuario = new Usuario(cursor.getString(cursor.getColumnIndex("login")), cursor.getString(cursor.getColumnIndex("senha")), cursor.getString(cursor.getColumnIndex("nome")));
            usuario.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex("id"))));
            usuario.setFoto(cursor.getString(cursor.getColumnIndex("foto")));
            usuario.setPontuacao(Integer.parseInt(cursor.getString(cursor.getColumnIndex("pontuacao"))));
            return usuario;
        }else{
            return null;
        }
    }



}
