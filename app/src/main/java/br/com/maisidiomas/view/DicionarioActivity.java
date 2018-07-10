package br.com.maisidiomas.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;

import br.com.maisidiomas.R;
import br.com.maisidiomas.controller.ControllerDicionario;

public class DicionarioActivity extends ModeloActivity {

    private TextView tvPalavra, tvTraducao, tvInformacao;
    private SearchView svBuscaPalavra;
    private LinearLayout ltCabecalho;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dicionario);
        tvPalavra = findViewById(R.id.tvName);
        tvTraducao = findViewById(R.id.tvTrad);
        tvInformacao = findViewById(R.id.tvInformacao);
        svBuscaPalavra = findViewById(R.id.svDicionario);
        ltCabecalho = findViewById(R.id.ltCabecalho);

        tvPalavra.setTypeface(getFont());
        tvTraducao.setTypeface(getFont());
        tvInformacao.setTypeface(getFont());
        new ControllerDicionario(this);


    }

    public TextView getTvPalavra() {
        return tvPalavra;
    }

    public void setTvPalavra(TextView tvPalavra) {
        this.tvPalavra = tvPalavra;
    }

    public TextView getTvTraducao() {
        return tvTraducao;
    }

    public void setTvTraducao(TextView tvTraducao) {
        this.tvTraducao = tvTraducao;
    }

    public SearchView getSvBuscaPalavra() {
        return svBuscaPalavra;
    }

    public void setSvBuscaPalavra(SearchView svBuscaPalavra) {
        this.svBuscaPalavra = svBuscaPalavra;
    }

    public TextView getTvInformacao() {
        return tvInformacao;
    }

    public void setTvInformacao(TextView tvInformacao) {
        this.tvInformacao = tvInformacao;
    }

    public LinearLayout getLtCabecalho() {
        return ltCabecalho;
    }

    public void setLtCabecalho(LinearLayout ltCabecalho) {
        this.ltCabecalho = ltCabecalho;
    }
}
