package br.com.maisidiomas.model.dao.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import br.com.maisidiomas.model.dao.fabrica.PalavraDAO;
import br.com.maisidiomas.model.vo.Palavra;

public class PalavraDAOSQLite extends PalavraDAO {

    private SQLiteDatabase sqLiteDatabase;

    public PalavraDAOSQLite(SQLiteDatabase sqLiteDatabase) {
        this.sqLiteDatabase = sqLiteDatabase;
    }

    @Override
    public void insert(Palavra palavra) throws Exception {
        try {
            ContentValues values = new ContentValues();
            values.put("nome", palavra.getNome());
            values.put("traducao", palavra.getTraducao());
            values.put("nivel", palavra.getNivel());
            if (this.sqLiteDatabase.insert("palavra", null, values) > 0) {
                return;
            } else {
                throw new Exception("Erro ao inserir palavra");
            }
        }catch (Exception e){

        }
    }

    @Override
    public void update(Palavra palavra) throws Exception {

    }

    @Override
    public void delete(Palavra palavra) throws Exception {

    }

    @Override
    public Palavra findById(int id) throws Exception {
        return null;
    }

    @Override
    public ArrayList<Palavra> listByLevel(int level) throws Exception {
        ArrayList<Palavra> palavraList = new ArrayList<>();

        StringBuilder sql_listar = new StringBuilder();
        sql_listar.append("select * from palavra where nivel = "+level+" order by nome;");
        Cursor cursor = sqLiteDatabase.rawQuery(sql_listar.toString(), null);

        if(cursor.moveToFirst()){
            do{
                try{
                    Palavra palavra = new Palavra(Integer.parseInt(cursor.getString(1)), cursor.getString(2), cursor.getString(3));
                    palavra.setId(Integer.parseInt(cursor.getString(0)));
                    palavraList.add(palavra);
                }catch (Exception e){
                    throw new Exception("Erro ao listar palavras por nível");
                }
            }while (cursor.moveToNext());

            return palavraList;
        }else{
            return null;
        }
    }

    @Override
    public ArrayList<Palavra> listAll() throws Exception {
        return null;
    }

    @Override
    public boolean palavraIsCadastra(String palavra) throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append("select * from palavra where nome = '"+palavra+"';");
        Cursor cursor = this.sqLiteDatabase.rawQuery(sql.toString(), null);

        if(cursor.moveToFirst()){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public ArrayList<Palavra> listByPalavraChave(String palavraChave) throws Exception {
        ArrayList<Palavra> palavraList = new ArrayList<>();

        StringBuilder sql_listar = new StringBuilder();
        sql_listar.append("select * from palavra where nome like '%"+palavraChave+"%' or traducao like '%"+palavraChave+"%' order by nome;");
        Cursor cursor = sqLiteDatabase.rawQuery(sql_listar.toString(), null);
        try{
            if(cursor.moveToFirst()){
                do{

                        Palavra palavra = new Palavra(Integer.parseInt(cursor.getString(1)), cursor.getString(2), cursor.getString(3));
                        palavra.setId(Integer.parseInt(cursor.getString(0)));
                        palavraList.add(palavra);

                }while (cursor.moveToNext());
                return palavraList;

            }else{
                return null;
            }
        }catch (Exception e){
        throw new Exception("Erro ao listar por palavra chave");
    }
    }

    @Override
    public String[] listNomesByLevel(int nivel) {
        String [] nomes = new String[70];
        int cont = 0;
        StringBuilder sql_listar = new StringBuilder();
        sql_listar.append("select nome from palavra where nivel = "+nivel+" order by nome;");
        Cursor cursor = sqLiteDatabase.rawQuery(sql_listar.toString(), null);

        if(cursor.moveToFirst()){
            do{
                nomes[cont] = cursor.getString(cursor.getColumnIndex("nome"));
                cont++;

            }while (cursor.moveToNext());

            return nomes;
        }else{
            return null;
        }
    }

    public Palavra [] listaPalavras(String [] nomes){
        Palavra [] palavras = new Palavra[4];

        for(int i = 0; i < nomes.length; i++){
            StringBuilder sql = new StringBuilder();
            sql.append("select * from palavra where nome = '"+nomes[i]+"';");
            Cursor cursor = this.sqLiteDatabase.rawQuery(sql.toString(), null);

            if(cursor.moveToFirst()){
                Palavra p = new Palavra(Integer.parseInt(cursor.getString(cursor.getColumnIndex("nivel"))), cursor.getString(cursor.getColumnIndex("nome")), cursor.getString(cursor.getColumnIndex("traducao")));
                palavras[i] = p;
            }
        }

        return palavras;
    }
}
