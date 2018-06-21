package br.com.maisidiomas.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import br.com.maisidiomas.R;
import br.com.maisidiomas.controller.ControllerDicionario;

public class DicionarioActivity extends ModeloActivity {

    private TextView tvPalavra, tvTraducao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dicionario);
        tvPalavra = findViewById(R.id.tvName);
        tvTraducao = findViewById(R.id.tvTrad);

        tvPalavra.setTypeface(getFont());
        tvTraducao.setTypeface(getFont());
        new ControllerDicionario(this);


    }
}
