package br.com.maisidiomas.model.dao.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import br.com.maisidiomas.model.dao.fabrica.QuestaoNivel3DAO;
import br.com.maisidiomas.model.vo.QuestaoNivel3;

public class QuestaoNivel3DAOSQLite extends QuestaoNivel3DAO {

    private SQLiteDatabase sqLiteDatabase;

    public QuestaoNivel3DAOSQLite(SQLiteDatabase sqLiteDatabase) {
        this.sqLiteDatabase = sqLiteDatabase;
    }

    public void insert(QuestaoNivel3 questaoNivel3) throws Exception{
        ContentValues values = new ContentValues();
        values.put("palavra1", questaoNivel3.getPalavras()[0]);
        values.put("palavra2", questaoNivel3.getPalavras()[1]);
        values.put("palavra3", questaoNivel3.getPalavras()[2]);
        values.put("palavra4", questaoNivel3.getPalavras()[3]);
        values.put("traducao1", questaoNivel3.getTraducoes()[0]);
        values.put("traducao2", questaoNivel3.getTraducoes()[1]);
        values.put("traducao3", questaoNivel3.getTraducoes()[2]);
        values.put("traducao4", questaoNivel3.getTraducoes()[3]);
        values.put("indice_resposta", questaoNivel3.getIndiceResposta());

        if(this.sqLiteDatabase.insert("questao_nivel_3", null, values) > 0) {
            return;
        } else {
            throw  new Exception("Erro ao inserir questão nivel 3");
        }
    }

    @Override
    public void update(QuestaoNivel3 questaoNivel3) throws Exception {

    }

    @Override
    public void delete(QuestaoNivel3 questaoNivel3) throws Exception {

    }

    @Override
    public QuestaoNivel3 findById(int id) throws Exception {
        return null;
    }

    public ArrayList<QuestaoNivel3> listar(){
        ArrayList<QuestaoNivel3> questoes = new ArrayList<>();
        StringBuilder sql_listar = new StringBuilder();
        sql_listar.append("select * from questao_nivel_3;");
        Cursor cursor = sqLiteDatabase.rawQuery(sql_listar.toString(), null);

        if(cursor.moveToFirst()){
            do{
                try{
                    QuestaoNivel3 questaoNivel3 = new QuestaoNivel3(Integer.parseInt(cursor.getString(cursor.getColumnIndex("indice_resposta"))), new String[]{cursor.getString(cursor.getColumnIndex("palavra1")), cursor.getString(cursor.getColumnIndex("palavra2")), cursor.getString(cursor.getColumnIndex("palavra3")), cursor.getString(cursor.getColumnIndex("palavra4"))}, new String[]{cursor.getString(cursor.getColumnIndex("traducao1")), cursor.getString(cursor.getColumnIndex("traducao2")), cursor.getString(cursor.getColumnIndex("traducao3")), cursor.getString(cursor.getColumnIndex("traducao4"))});
                    questoes.add(questaoNivel3);
                }catch (Exception e){
                    return null;
                }
            }while (cursor.moveToNext());
            return questoes;

        }else{
            return null;
        }
    }

    public boolean questaoIsCadastrada(QuestaoNivel3 q){
        StringBuilder sql = new StringBuilder();
        sql.append("select * from questao_nivel_3 where palavra1 = '"+q.getPalavras()[0]+"' and palavra2 = '"+q.getPalavras()[1]+"' and palavra3 ='"+q.getPalavras()[2]+"' and palavra4 = '"+q.getPalavras()[3]+"';");
        Cursor cursor = this.sqLiteDatabase.rawQuery(sql.toString(), null);

        if(cursor.moveToFirst()){
            return true;
        }else{
            return false;
        }
    }
}
