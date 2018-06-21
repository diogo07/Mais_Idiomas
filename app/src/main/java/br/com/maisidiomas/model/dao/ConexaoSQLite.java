package br.com.maisidiomas.model.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ConexaoSQLite extends SQLiteOpenHelper{

    private static SQLiteDatabase database;

    public ConexaoSQLite(Context context) {
        super(context, "banco_mais_idiomas", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuilder query_tabela = new StringBuilder();
        query_tabela.append("create table usuario( ");
        query_tabela.append("id integer primary key, ");
        query_tabela.append("nome varchar(50), ");
        query_tabela.append("login varchar(30), ");
        query_tabela.append("senha varchar(30), ");
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
/*
        StringBuilder query_tabela_questao_nivel3 = new StringBuilder();
        query_tabela_palavras.append("create table questao_nivel_3( ");
        query_tabela_palavras.append("id integer primary key, ");
        query_tabela_palavras.append("palavra1 varchar(30), ");
        query_tabela_palavras.append("palavra2 varchar(30), ");
        query_tabela_palavras.append("palavra3 varchar(30), ");
        query_tabela_palavras.append("traducao1 varchar(30), ");
        query_tabela_palavras.append("traducao2 varchar(30), ");
        query_tabela_palavras.append("traducao3 varchar(30), ");
        query_tabela_palavras.append("indice_resposta integer);");
        db.execSQL(query_tabela_questao_nivel3.toString());
*/
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public static SQLiteDatabase getInstance(Context context) {
        SQLiteOpenHelper sqLiteOpenHelper;
        if (database == null || !database.isOpen()) {
            ConexaoSQLite conn = new ConexaoSQLite(context);
            database = conn.getWritableDatabase();
        }
        return database;
    }
}
