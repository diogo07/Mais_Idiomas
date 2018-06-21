package br.com.maisidiomas.controller;

import android.content.Intent;
import android.view.View;

import br.com.maisidiomas.view.FaseActivity;
import br.com.maisidiomas.view.LevelActivity;
import br.com.maisidiomas.R;

public class ControllerLevel implements View.OnClickListener {

    private LevelActivity levelActivity;

    public ControllerLevel(LevelActivity levelActivity) {
        this.levelActivity = levelActivity;
        this.levelActivity.getImgNivel1().setOnClickListener(this);
        this.levelActivity.getImgNivel2().setOnClickListener(this);
        this.levelActivity.getImgNivel3().setOnClickListener(this);
        this.levelActivity.getBtIniciar().setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_iniciar:
                this.levelActivity.verificarNivel();
                break;
            case R.id.img_level1:
                this.levelActivity.inserirFocoImagemNivel(1);
                break;
            case R.id.img_level2:
                this.levelActivity.inserirFocoImagemNivel(2);
                break;
            case R.id.img_level3:
                this.levelActivity.inserirFocoImagemNivel(3);
                break;
        }

    }

}
