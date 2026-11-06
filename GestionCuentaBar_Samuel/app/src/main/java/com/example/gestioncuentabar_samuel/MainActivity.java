package com.example.gestioncuentabar_samuel;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    AutoCompleteTextView autoCamarero;
    EditText editTotal;
    CheckBox checkPropina;
    TextView txtPropina;
    SeekBar seekPropina;
    RadioGroup radioGroup;
    RatingBar ratingBar;
    Button btnCalcular;
    TextView txtResultado;

    boolean conPropina = false;
    int porcentaje = 10;
    float valoracion = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        autoCamarero = findViewById(R.id.autoCamarero);
        editTotal = findViewById(R.id.editTotal);
        checkPropina = findViewById(R.id.checkPropina);
        txtPropina = findViewById(R.id.txtPropina);
        seekPropina = findViewById(R.id.seekPropina);
        radioGroup = findViewById(R.id.radioGroup);
        ratingBar = findViewById(R.id.ratingBar);
        btnCalcular = findViewById(R.id.btnCalcular);
        txtResultado = findViewById(R.id.txtResultado);


        String[] camareros = {"Carlos", "Carmen", "Cristina", "Daniel", "David", "Elena",
                "Javier", "Jorge", "Laura", "Lucia", "Marta", "Miguel", "Pablo", "Sara", "Sergio"};
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, camareros);
        autoCamarero.setAdapter(adaptador);


        txtPropina.setText(getString(R.string.texto_propina) + " " + porcentaje + "%");


        checkPropina.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                conPropina = isChecked;
            }
        });


        seekPropina.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                porcentaje = progress;
                txtPropina.setText(getString(R.string.texto_propina) + " " + progress + "%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });


        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                valoracion = rating;
            }
        });


        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String texto = editTotal.getText().toString();


                if (texto.isEmpty()) {
                    mostrarError(getString(R.string.error_vacio));
                    return;
                }


                double cuenta;
                try {
                    cuenta = Double.parseDouble(texto.replace(",", "."));
                } catch (NumberFormatException e) {
                    mostrarError(getString(R.string.error_formato));
                    return;
                }


                if (cuenta <= 0) {
                    mostrarError(getString(R.string.error_minimo));
                    return;
                }


                int idSeleccionado = radioGroup.getCheckedRadioButtonId();
                if (idSeleccionado == -1) {
                    mostrarError(getString(R.string.error_pago));
                    return;
                }
                RadioButton radioSeleccionado = findViewById(idSeleccionado);
                String pago = radioSeleccionado.getText().toString();


                int porcentajeFinal = 0;
                if (conPropina) {
                    porcentajeFinal = porcentaje;
                }
                double propina = Math.round(cuenta * porcentajeFinal) / 100.0;
                double total = Math.round((cuenta + propina) * 100) / 100.0;


                String resultado = "";
                String camarero = autoCamarero.getText().toString();
                if (!camarero.isEmpty()) {
                    resultado = getString(R.string.label_camarero) + ": " + camarero + "\n";
                }
                resultado = resultado + getString(R.string.label_cuenta) + ": " + cuenta + " €\n";
                resultado = resultado + getString(R.string.texto_propina) + " (" + porcentajeFinal + "%): " + propina + " €\n";
                resultado = resultado + getString(R.string.label_total) + ": " + total + " €\n";
                resultado = resultado + getString(R.string.label_pago) + ": " + pago + "\n";
                resultado = resultado + getString(R.string.label_valoracion) + ": " + valoracion + "/5";

                txtResultado.setTextColor(Color.BLACK);
                txtResultado.setText(resultado);
            }
        });
    }

    private void mostrarError(String mensaje) {
        txtResultado.setTextColor(Color.RED);
        txtResultado.setText(mensaje);
    }
}