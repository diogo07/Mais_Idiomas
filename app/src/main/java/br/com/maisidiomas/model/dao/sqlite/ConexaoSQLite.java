package br.com.maisidiomas.model.dao.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ConexaoSQLite extends SQLiteOpenHelper{

    private static SQLiteDatabase database;

    private ConexaoSQLite(Context context) {

        super(context, "banco_mais_idiomas", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuilder query_tabela = new StringBuilder();
        query_tabela.append("create table usuario( ");
        query_tabela.append("id integer, ");
        query_tabela.append("nome varchar(50), ");
        query_tabela.append("login varchar(30), ");
        query_tabela.append("senha varchar(30), ");
        query_tabela.append("ajuda varchar(5), ");
        query_tabela.append("pontuacao integer, ");
        query_tabela.append("foto varchar(50));");
        db.execSQL(query_tabela.toString());

        StringBuilder query_tabela_palavras = new StringBuilder();
        query_tabela_palavras.append("create table palavra( ");
        query_tabela_palavras.append("id integer primary key, ");
        query_tabela_palavras.append("nivel integer, ");
        query_tabela_palavras.append("nome varchar(100), ");
        query_tabela_palavras.append("traducao varchar(100));");
        db.execSQL(query_tabela_palavras.toString());

        StringBuilder query_tabela_questao_nivel3 = new StringBuilder();
        query_tabela_questao_nivel3.append("create table questao_nivel_3( ");
        query_tabela_questao_nivel3.append("id integer primary key, ");
        query_tabela_questao_nivel3.append("palavra1 varchar(20), ");
        query_tabela_questao_nivel3.append("palavra2 varchar(20), ");
        query_tabela_questao_nivel3.append("palavra3 varchar(20), ");
        query_tabela_questao_nivel3.append("palavra4 varchar(20), ");
        query_tabela_questao_nivel3.append("traducao1 varchar(20), ");
        query_tabela_questao_nivel3.append("traducao2 varchar(20), ");
        query_tabela_questao_nivel3.append("traducao3 varchar(20), ");
        query_tabela_questao_nivel3.append("traducao4 varchar(20), ");
        query_tabela_questao_nivel3.append("indice_resposta integer);");
        db.execSQL(query_tabela_questao_nivel3.toString());

        StringBuilder query_tabela_fase = new StringBuilder();
        query_tabela_fase.append("create table fase( ");
        query_tabela_fase.append("id integer primary key, ");
        query_tabela_fase.append("login_usuario varchar(50), ");
        query_tabela_fase.append("nivel integer, ");
        query_tabela_fase.append("pontuacao integer);");
        db.execSQL(query_tabela_fase.toString());



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public static synchronized SQLiteDatabase getInstance(Context context) {
        if (database == null || !database.isOpen()) {
            ConexaoSQLite conn = new ConexaoSQLite(context);
            database = conn.getWritableDatabase();
        }
        return database;
    }
}
