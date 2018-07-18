package br.com.maisidiomas.controller;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;

import br.com.maisidiomas.R;
import br.com.maisidiomas.model.dao.ConexaoSQLite;
import br.com.maisidiomas.model.dao.PalavraDAOSQLite;
import br.com.maisidiomas.utils.DicionarioAdapter;
import br.com.maisidiomas.model.vo.Palavra;
import br.com.maisidiomas.view.DicionarioActivity;

public class ControllerDicionario implements SearchView.OnQueryTextListener, View.OnClickListener {

    private DicionarioActivity dicionarioActivity;

    public ControllerDicionario(DicionarioActivity dicionarioActivity) {
        this.dicionarioActivity = dicionarioActivity;
        this.dicionarioActivity.getSvBuscaPalavra().setOnQueryTextListener(this);
        this.dicionarioActivity.getSvBuscaPalavra().setOnClickListener(this);
        inserirDicionario();
    }

    private void inserirDicionario() {
        ArrayAdapter arrayAdapter = null;
        try {
            arrayAdapter = new DicionarioAdapter(this.dicionarioActivity, new PalavraDAOSQLite(ConexaoSQLite.getInstance(dicionarioActivity)).listByPalavraChave(""));
            ListView lvOpcoes = (ListView) dicionarioActivity.findViewById(R.id.lv_dicionario);
            lvOpcoes.setAdapter(arrayAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        ArrayList<Palavra> palavras = null;
        try {
            palavras = new PalavraDAOSQLite(ConexaoSQLite.getInstance(dicionarioActivity)).listByPalavraChave(newText.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(palavras != null){
            this.dicionarioActivity.getTvInformacao().setText("");
            this.dicionarioActivity.getTvPalavra().setText("NOME");
            this.dicionarioActivity.getTvTraducao().setText("TRADUÇÃO");
            this.dicionarioActivity.getLtCabecalho().setBackgroundColor(Color.GRAY);
            ArrayAdapter arrayAdapter = new DicionarioAdapter(this.dicionarioActivity, palavras);
            ListView lvOpcoes = (ListView) dicionarioActivity.findViewById(R.id.lv_dicionario);
            lvOpcoes.setAdapter(arrayAdapter);
        }else{
            this.dicionarioActivity.getTvPalavra().setText("");
            this.dicionarioActivity.getTvTraducao().setText("");
            this.dicionarioActivity.getTvInformacao().setText("NENHUM RESULTADO ENCONTRADO");
            this.dicionarioActivity.getLtCabecalho().setBackgroundColor(Color.WHITE);
            ListView lvOpcoes = (ListView) dicionarioActivity.findViewById(R.id.lv_dicionario);
            lvOpcoes.setAdapter(null);
        }

        return false;
    }

    @Override
    public void onClick(View v) {
        ((InputMethodManager) this.dicionarioActivity.getSystemService(Context.INPUT_METHOD_SERVICE))
                .showSoftInput(this.dicionarioActivity.getSvBuscaPalavra(), 0);
    }
}
