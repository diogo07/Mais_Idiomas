package br.com.maisidiomas.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.maisidiomas.R;

public class RankingDisconectAdapter extends ArrayAdapter<String> {
    private  final Context context;

    public RankingDisconectAdapter(@NonNull Context context, ArrayList<String> lista) {
        super(context, R.layout.modelo_falta_conexao, lista);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.modelo_falta_conexao, parent, false);

        Typeface font = Typeface.createFromAsset(context.getAssets(), "BowlbyOneSC-Regular.ttf");

        TextView tvMsg = rowView.findViewById(R.id.tvMsgFalha);
        tvMsg.setTypeface(font);
        return rowView;
    }
}
