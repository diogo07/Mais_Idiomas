package br.com.maisidiomas.view;

import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import br.com.maisidiomas.R;
import br.com.maisidiomas.controller.ControllerLevel;

public class LevelActivity extends ModeloActivity {

    private TextView tvTitulo, tvNivel1, tvNivel2, tvNivel3;
    private ImageView imgNivel1, imgNivel2, imgNivel3;
    private Button btIniciar;
    private String login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        login = getIntent().getStringExtra("login").toString();
        setContentView(R.layout.activity_level);


        tvTitulo = findViewById(R.id.tv_texto_fase);
        tvNivel1 = findViewById(R.id.tv_level1);
        tvNivel2 = findViewById(R.id.tv_level2);
        tvNivel3 = findViewById(R.id.tv_level3);
        btIniciar = findViewById(R.id.bt_iniciar);
        imgNivel1 = findViewById(R.id.img_level1);
        imgNivel2 = findViewById(R.id.img_level2);
        imgNivel3 = findViewById(R.id.img_level3);



        tvTitulo.setTypeface(getFont());
        tvNivel1.setTypeface(getFont());
        tvNivel2.setTypeface(getFont());
        tvNivel3.setTypeface(getFont());
        btIniciar.setTypeface(getFont());

        new ControllerLevel(this);
    }


    public void inserirFocoImagemNivel(int nivel){
        switch (nivel){
            case 1:
                imgNivel1.setFocusable(true);
                imgNivel1.setBackgroundResource(R.drawable.estilo_opcao);
                imgNivel2.setBackgroundResource(R.drawable.estilo_opcao_no_select);
                imgNivel2.setFocusable(false);
                imgNivel3.setBackgroundResource(R.drawable.estilo_opcao_no_select);
                imgNivel3.setFocusable(false);
                break;
            case 2:
                imgNivel1.setFocusable(false);
                imgNivel1.setBackgroundResource(R.drawable.estilo_opcao_no_select);
                imgNivel2.setBackgroundResource(R.drawable.estilo_opcao);
                imgNivel2.setFocusable(true);
                imgNivel3.setBackgroundResource(R.drawable.estilo_opcao_no_select);
                imgNivel3.setFocusable(false);
                break;
            case 3:
                imgNivel1.setFocusable(false);
                imgNivel1.setBackgroundResource(R.drawable.estilo_opcao_no_select);
                imgNivel2.setBackgroundResource(R.drawable.estilo_opcao_no_select);
                imgNivel2.setFocusable(false);
                imgNivel3.setBackgroundResource(R.drawable.estilo_opcao);
                imgNivel3.setFocusable(true);
                break;
        }
    }

    public void verificarNivel(){
        if(!imgNivel1.isFocusable() && !imgNivel2.isFocusable() && !imgNivel3.isFocusable()){
            Toast.makeText(this, "Nenhum nível selecionado", Toast.LENGTH_LONG).show();
        }else if(imgNivel1.isFocusable()){
            chamarTelaFase(1);
        }else if(imgNivel2.isFocusable()){
            chamarTelaFase(2);
        }else{
            Intent i = new Intent(LevelActivity.this, Fase3Activity.class);
            i.putExtra("login", ""+login);
            startActivity(i);
        }
    }

    public void chamarTelaFase(int nivel){
        Intent i = new Intent(LevelActivity.this, FaseActivity.class);
        i.putExtra("nivel", ""+nivel);
        i.putExtra("login", ""+login);
        startActivity(i);
    }

    public TextView getTvTitulo() {
        return tvTitulo;
    }

    public TextView getTvNivel1() {
        return tvNivel1;
    }

    public TextView getTvNivel2() {
        return tvNivel2;
    }

    public TextView getTvNivel3() {
        return tvNivel3;
    }

    public ImageView getImgNivel1() {
        return imgNivel1;
    }

    public ImageView getImgNivel2() {
        return imgNivel2;
    }

    public ImageView getImgNivel3() {
        return imgNivel3;
    }

    public Button getBtIniciar() {
        return btIniciar;
    }
}
