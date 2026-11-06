package com.example.tema4textview;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);


    }

    protected void onStart(){
        super.onStart();
        TextView miTexto = (TextView) findViewById(R.id.texto);
        miTexto.setText("Nuevo texte a mostrar");
//Opcion 1 para cambiar el color.
        miTexto.setTextColor(Color.parseColor( "#0000FF"));
// Opcion 2 para cambiar el color.
        miTexto.setTextColor(Color.RED);
// Cambin del Texto a Negrita
        miTexto.setTypeface( #: nll, Typeface.ITALIC);
// Cambin el tamano del texto
        miTexto.setTextsize(24);
// Cambiar tipe de letra
        miTexto.setTypeface(Typeface.SANS_SERIF);
    }
}