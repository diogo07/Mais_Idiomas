package br.com.maisidiomas.model.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import br.com.maisidiomas.model.vo.Usuario;

public class UsuarioDAOSQLite implements UsuarioDAO{

    private SQLiteDatabase sqLiteDatabase;


    public UsuarioDAOSQLite(SQLiteDatabase sqLiteDatabase) {
        this.sqLiteDatabase = sqLiteDatabase;
    }

    public boolean insert(Usuario usuario) {
        ContentValues values = new ContentValues();
        values.put("nome", usuario.getNome());
        values.put("login", usuario.getLogin());
        values.put("senha", usuario.getSenha());
        values.put("pontuacao", usuario.getPontuacao());
        values.put("foto", usuario.getFoto());
        if (this.sqLiteDatabase.insert("usuario", null, values) > 0) {
            //this.sqLiteDatabase.close();
            return true;
        } else {
            //this.sqLiteDatabase.close();
            return false;
        }
    }

    @Override
    public boolean update(Usuario usuario) {

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
            return true;
        } else {
            sqLiteDatabase.close();
            return false;
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
            //this.sqLiteDatabase.close();
            return usuario;
        }else{
            //this.sqLiteDatabase.close();
            return null;
        }
    }

    @Override
    public boolean remove(int id) {
        return false;
    }

    @Override
    public ArrayList<Usuario> listar() {
        return null;
    }

    @Override
    public ArrayList<Usuario> listarPorPontuacao() {
        return null;
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
            //this.sqLiteDatabase.close();
            return usuario;
        }else{
            //this.sqLiteDatabase.close();
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
            //this.sqLiteDatabase.close();
            return usuario;
        }else{
            //this.sqLiteDatabase.close();
            return null;
        }
    }


   /* public UsuarioDAOSQLite(Context context) {

        super(context, "db_mais_idiomas", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuilder query_tabela = new StringBuilder();
        query_tabela.append("create table usuario( ");
        query_tabela.append("id integer primary key, ");
        query_tabela.append("nome varchar(50), ");
        query_tabela.append("login varchar(30), ");
        query_tabela.append("senha varchar(30), ");
        query_tabela.append("foto varchar(50));");
        db.execSQL(query_tabela.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public boolean insert(Usuario usuario) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nome", usuario.getNome());
        values.put("login", usuario.getLogin());
        values.put("senha", usuario.getSenha());
        values.put("foto", usuario.getFoto());
        if (db.insert("usuario", null, values) > 0) {
            db.close();
            return true;
        } else {
            db.close();
            return false;
        }
    }

    @Override
    public boolean update(Usuario usuario) {
        return false;
    }

    @Override
    public Usuario findById(int id) throws Exception {
        return null;
    }

    @Override
    public boolean remove(int id) {
        return false;
    }

    @Override
    public ArrayList<Usuario> listar() {
        return null;
    }

    @Override
    public ArrayList<Usuario> listarPorPontuacao() {
        return null;
    }

    public boolean findByLogin(String login){
        SQLiteDatabase db = this.getWritableDatabase();

        StringBuilder sql = new StringBuilder();
        sql.append("select * from usuario where login = '"+login+"';");
        Cursor cursor = db.rawQuery(sql.toString(), null);

        if(cursor.moveToFirst()){
            return true;
        }else{
            return false;
        }
    }

    public Usuario findByLoginEsenha(String login, String senha){
        SQLiteDatabase db = this.getWritableDatabase();

        StringBuilder sql = new StringBuilder();
        sql.append("select id, nome, login, senha, foto from usuario where login = '"+login+"' and senha = '"+senha+"';");
        Cursor cursor = db.rawQuery(sql.toString(), null);

        if(cursor.moveToFirst()){
            Usuario usuario = new Usuario(cursor.getString(2), cursor.getString(3), cursor.getString(1));
            usuario.setId(Integer.parseInt(cursor.getString(0)));
            usuario.setFoto(cursor.getString(4));
            return usuario;
        }else{
            return null;
        }
    }
*/


}
