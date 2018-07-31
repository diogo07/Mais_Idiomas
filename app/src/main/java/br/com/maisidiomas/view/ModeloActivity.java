package br.com.maisidiomas.view;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;

public abstract class ModeloActivity extends AppCompatActivity{

    public Typeface getFont(){
        Typeface font = Typeface.createFromAsset(getAssets(), "BowlbyOneSC-Regular.ttf");
        return font;
    }

}
