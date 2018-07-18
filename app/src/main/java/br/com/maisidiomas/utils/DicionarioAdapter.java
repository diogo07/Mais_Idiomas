package br.com.maisidiomas.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.maisidiomas.R;
import br.com.maisidiomas.model.vo.Palavra;

public class DicionarioAdapter extends ArrayAdapter<Palavra>{
    private  final Context context;
    private final ArrayList<Palavra> lista;

    public DicionarioAdapter(Context context, ArrayList<Palavra> lista) {
        super(context, R.layout.modelo_tabela, lista);
        this.context = context;
        this.lista = lista;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.modelo_tabela, parent, false);

        TextView tvNomePalavra = rowView.findViewById(R.id.tvNomePalavra);
        TextView tvNomeTraducao = rowView.findViewById(R.id.tvNomeTraducao);

        Typeface font = Typeface.createFromAsset(context.getAssets(), "BowlbyOneSC-Regular.ttf");

        tvNomePalavra.setText(lista.get(position).getNome());
        tvNomeTraducao.setText(lista.get(position).getTraducao());
        tvNomePalavra.setTypeface(font);
        tvNomeTraducao.setTypeface(font);

        return rowView;
    }
}
