package br.com.maisidiomas.model.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.maisidiomas.model.vo.Palavra;
import br.com.maisidiomas.model.vo.Usuario;

public class PalavraDAOSQLite {

    private SQLiteDatabase sqLiteDatabase;

    public PalavraDAOSQLite(SQLiteDatabase sqLiteDatabase) {
        this.sqLiteDatabase = sqLiteDatabase;
    }


    public boolean insert(Palavra palavra) {
        ContentValues values = new ContentValues();
        values.put("nome", palavra.getNome());
        values.put("traducao", palavra.getTraducao());
        values.put("nivel", palavra.getNivel());
        if (this.sqLiteDatabase.insert("palavra", null, values) > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean palavraIsCadastrada(String nome){
        StringBuilder sql = new StringBuilder();
        sql.append("select * from palavra where nome = '"+nome+"';");
        Cursor cursor = this.sqLiteDatabase.rawQuery(sql.toString(), null);

        if(cursor.moveToFirst()){
            return true;
        }else{
            return false;
        }
    }

    public ArrayList<Palavra> listar(int nivel){
        ArrayList<Palavra> palavraList = new ArrayList<>();

        StringBuilder sql_listar = new StringBuilder();
        sql_listar.append("select * from palavra where nivel = "+nivel+";");
        Cursor cursor = sqLiteDatabase.rawQuery(sql_listar.toString(), null);

        if(cursor.moveToFirst()){
            do{
                try{
                    Palavra palavra = new Palavra(Integer.parseInt(cursor.getString(1)), cursor.getString(2), cursor.getString(3));
                    palavra.setId(Integer.parseInt(cursor.getString(0)));
                    palavraList.add(palavra);
                }catch (Exception e){
                    //this.sqLiteDatabase.close();
                    return null;
                }
            }while (cursor.moveToNext());

            //this.sqLiteDatabase.close();
            return palavraList;

        }else{
            //this.sqLiteDatabase.close();
            return null;
        }
    }

}
