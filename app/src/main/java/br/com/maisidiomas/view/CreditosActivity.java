package br.com.maisidiomas.view;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.widget.TextView;

import br.com.maisidiomas.R;

public class CreditosActivity extends ModeloActivity {

    private TextView tvCreditos, tvFlat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creditos);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        tvCreditos = findViewById(R.id.tvCred);
        tvFlat = findViewById(R.id.tvFlat);

        tvCreditos.setTypeface(getFont());
        tvFlat.setTypeface(getFont());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return true;
    }
}
