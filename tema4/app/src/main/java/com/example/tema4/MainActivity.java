package com.example.tema4;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();

        TextView miTexto = findViewById(R.id.texto);

        miTexto.setText("Nuevo texto a mostrar");

        // Opcion 1 para cambiar el color
        miTexto.setTextColor(Color.parseColor("#0000FF"));

        // Opcion 2 para cambiar el color
        miTexto.setTextColor(Color.RED);

        // Cambio del texto a Negrita
        miTexto.setTypeface(null, Typeface.ITALIC);

        // Cambio el tamaño del texto
        miTexto.setTextSize(24);

        // Cambiar tipo de letra
        miTexto.setTypeface(Typeface.SANS_SERIF);

        Animation miAnimation = AnimationUtils.loadAnimation(this, R.anim.elrotate);
        miAnimation.setRepeatMode(Animation.RESTART);
        miAnimation.setRepeatCount(20);
        miTexto.startAnimation(miAnimation);

    }
}