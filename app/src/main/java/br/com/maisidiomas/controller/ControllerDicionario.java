package br.com.maisidiomas.controller;

import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import br.com.maisidiomas.R;
import br.com.maisidiomas.model.dao.ConexaoSQLite;
import br.com.maisidiomas.model.dao.PalavraDAOSQLite;
import br.com.maisidiomas.model.vo.DicionarioAdapter;
import br.com.maisidiomas.model.vo.Ranking;
import br.com.maisidiomas.model.vo.RankingAdapter;
import br.com.maisidiomas.view.DicionarioActivity;

public class ControllerDicionario {

    private DicionarioActivity dicionarioActivity;

    public ControllerDicionario(DicionarioActivity dicionarioActivity) {
        this.dicionarioActivity = dicionarioActivity;
        inserirDicionario();
    }

    private void inserirDicionario() {
        ArrayAdapter arrayAdapter = new DicionarioAdapter(this.dicionarioActivity, new PalavraDAOSQLite(ConexaoSQLite.getInstance(dicionarioActivity)).listar(1));
        ListView lvOpcoes = (ListView) dicionarioActivity.findViewById(R.id.lv_dicionario);
        lvOpcoes.setAdapter(arrayAdapter);
    }
}
