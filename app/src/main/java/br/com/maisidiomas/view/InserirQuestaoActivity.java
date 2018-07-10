package br.com.maisidiomas.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

import br.com.maisidiomas.R;

public class InserirQuestaoActivity extends AppCompatActivity {

    private Button btGetImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserir_questao);

        btGetImage = findViewById(R.id.btImg);
        btGetImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                startActivityForResult(Intent.createChooser(intent, "Selecione uma imagem"), 123);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == InserirQuestaoActivity.RESULT_OK){
            if(requestCode == 123){
/*
                Bundle bundle = data.getExtras();

                Bitmap bitmap = (Bitmap) bundle.get(data.getData().toString());
                ImageView myImage = (ImageView) findViewById(R.id.imageView2);
                myImage.setImageBitmap(bitmap);

                if(data != null){
                        Bundle bundle = data.getExtras();
                        Bitmap bitmap = (Bitmap) bundle.get("");
                }else {
                    Toast.makeText(this, bundle.toString(), Toast.LENGTH_LONG).show();
                }*/

            }
        }
    }





}
