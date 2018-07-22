package br.com.maisidiomas.model.dao;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import br.com.maisidiomas.model.vo.Fase;

public class FaseDAOSQLite extends FaseDAO {

    private SQLiteDatabase sqLiteDatabase;

    public FaseDAOSQLite(SQLiteDatabase sqLiteDatabase) {
        this.sqLiteDatabase = sqLiteDatabase;
    }

    @Override
    public void insert(Fase fase) throws Exception {
        try {
            ContentValues values = new ContentValues();
            values.put("login_usuario", fase.getLogin());
            values.put("pontuacao", fase.getPontuacao());
            if (this.sqLiteDatabase.insert("fase", null, values) > 0) {
                return;
            } else {
                throw new Exception("Erro ao inserir fase");
            }
        }catch (Exception e){

        }

    }

    @Override
    public void update(Fase fase) throws Exception {

    }

    @Override
    public void delete(Fase fase) throws Exception {

    }

    @Override
    public Fase findById(int id) throws Exception {
        return null;
    }

    @Override
    public ArrayList<Fase> listByLevel(int level) throws Exception {
        return null;
    }

    @Override
    public ArrayList<Fase> listAll() throws Exception {
        return null;
    }
}
