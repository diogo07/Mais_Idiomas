package br.com.maisidiomas.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import br.com.maisidiomas.R;

public class ConfiguracoesActivity extends ModeloActivity {

    private TextView tvConf, tvAltNome, tvAltLogin, tvAltSenha, tvAltAvatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracoes);

        tvConf = findViewById(R.id.tvConf);
        tvAltNome = findViewById(R.id.tvAltNome);
        tvAltLogin = findViewById(R.id.tvAltLogin);
        tvAltSenha = findViewById(R.id.tvAltSenha);
        tvAltAvatar = findViewById(R.id.tvAltAvatar);

        tvConf.setTypeface(getFont());
        tvAltNome.setTypeface(getFont());
        tvAltLogin.setTypeface(getFont());
        tvAltSenha.setTypeface(getFont());
        tvAltAvatar.setTypeface(getFont());
    }
}
