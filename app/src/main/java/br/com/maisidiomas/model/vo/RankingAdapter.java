package br.com.maisidiomas.model.vo;

import android.content.Context;
import android.graphics.Typeface;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import br.com.maisidiomas.R;

import java.util.ArrayList;

public class RankingAdapter extends ArrayAdapter<Ranking> {

    private  final Context context;
    private final ArrayList<Ranking> lista;

    public RankingAdapter(Context context, ArrayList<Ranking> lista) {
        super(context, R.layout.modelo_ranking, lista);
        this.context = context;
        this.lista = lista;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.modelo_ranking, parent, false);

        TextView tvPosicao = rowView.findViewById(R.id.tv_posicao);
        TextView tvNome = rowView.findViewById(R.id.tv_nome);
        TextView tvPontuacao = rowView.findViewById(R.id.tv_pontuacao);
        ImageView imgPerfil = rowView.findViewById(R.id.img_perfil);

        Typeface font = Typeface.createFromAsset(context.getAssets(), "BowlbyOneSC-Regular.ttf");

        tvPosicao.setText(lista.get(position).getPosicao()+"");
        tvPontuacao.setText("Pontuação: "+lista.get(position).getPontuacao()+" pontos");
        tvNome.setText(lista.get(position).getUsuario().getNome());
        imgPerfil.setImageResource(idAvatar(lista.get(position).getUsuario().getFoto()));

        tvPosicao.setTypeface(font);
        tvPontuacao.setTypeface(font);
        tvNome.setTypeface(font);
        return rowView;
    }



    public int idAvatar(String nome){
        if(nome.equals("avatar1")){
            return R.mipmap.ic_avatar1;
        }
        if(nome.equals("avatar2")){
            return R.mipmap.ic_avatar2;
        }
        if(nome.equals("avatar3")){
            return R.mipmap.ic_avatar3;
        }
        if(nome.equals("avatar4")){
            return R.mipmap.ic_avatar4;
        }
        if(nome.equals("avatar5")){
            return R.mipmap.ic_avatar5;
        }
        if(nome.equals("avatar6")){
            return R.mipmap.ic_avatar6;
        }
        if(nome.equals("avatar7")){
            return R.mipmap.ic_avatar7;
        }
        if(nome.equals("avatar8")){
            return R.mipmap.ic_avatar8;
        }else{
            return R.drawable.ic_perfil;
        }
    }
}
