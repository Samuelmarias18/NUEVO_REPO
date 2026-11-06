package com.example.calculadoraejercicio;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView pantalla;
    double numero1 = 0;
    String operacion = "";
    boolean nuevo = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pantalla = findViewById(R.id.pantalla);
    }


    public void pulsarNumero(View view) {
        Button boton = (Button) view;
        String texto = boton.getText().toString();

        if (nuevo) {
            if (texto.equals(".")) {
                pantalla.setText("0.");
            } else {
                pantalla.setText(texto);
            }
            nuevo = false;
        } else {
            String actual = pantalla.getText().toString();
            if (texto.equals(".") && actual.contains(".")) {
                return;
            }
            pantalla.setText(actual + texto);
        }
    }


    public void pulsarOperacion(View view) {
        Button boton = (Button) view;
        numero1 = Double.parseDouble(pantalla.getText().toString());
        operacion = boton.getText().toString();
        nuevo = true;
    }

    // Se ejecuta al pulsar =
    public void pulsarIgual(View view) {
        if (operacion.equals("")) {
            return;
        }

        double numero2 = Double.parseDouble(pantalla.getText().toString());
        double resultado = 0;

        if (operacion.equals("+")) {
            resultado = numero1 + numero2;
        } else if (operacion.equals("-")) {
            resultado = numero1 - numero2;
        } else if (operacion.equals("*")) {
            resultado = numero1 * numero2;
        } else if (operacion.equals("/")) {
            if (numero2 == 0) {
                pantalla.setText("Error");
                operacion = "";
                nuevo = true;
                return;
            }
            resultado = numero1 / numero2;
        }


        if (resultado == (long) resultado) {
            pantalla.setText(String.valueOf((long) resultado));
        } else {
            pantalla.setText(String.valueOf(resultado));
        }

        operacion = "";
        nuevo = true;
    }
}