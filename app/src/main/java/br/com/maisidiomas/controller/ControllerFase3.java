package br.com.maisidiomas.controller;

import android.view.View;
import br.com.maisidiomas.R;

import br.com.maisidiomas.view.Fase3Activity;

public class ControllerFase3 implements View.OnClickListener {

    private Fase3Activity fase3Activity;

    public ControllerFase3(Fase3Activity fase3Activity) {
        this.fase3Activity = fase3Activity;
        this.fase3Activity.getBtProximo().setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.bt_proximo_fase3){
            verificarRespota();
        }
    }

    private void verificarRespota() {
    }
}
