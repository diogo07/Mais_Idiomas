package br.com.maisidiomas.view;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.widget.TextView;

import br.com.maisidiomas.R;

public class AjudaActivity extends ModeloActivity {

    TextView tvTexto, tvLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajuda);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        tvTexto = findViewById(R.id.tvTexto);
        tvLink = findViewById(R.id.tvAj);

        tvTexto.setTypeface(getFont());
        tvLink.setTypeface(getFont());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return true;
    }
}
