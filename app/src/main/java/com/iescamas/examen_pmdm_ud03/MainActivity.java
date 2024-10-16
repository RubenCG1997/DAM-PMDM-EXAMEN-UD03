package com.iescamas.examen_pmdm_ud03;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        (findViewById(R.id.txt_nombre)).requestFocus();
        (findViewById(R.id.swc)).setOnClickListener(this::eligePreguntas);
        ((Spinner)findViewById(R.id.spinner)).setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                cambiaRespuestas();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        ((CheckBox)findViewById(R.id.cb)).setChecked(true);
        mostrarLog(findViewById(R.id.cb));
        (findViewById(R.id.btn_envio)).setOnClickListener(view -> {condicionesCorrectas(); mostrarLog((findViewById(R.id.cb)));});
        (findViewById(R.id.cb)).setOnClickListener(this::mostrarLog);

    }

    private void mostrarLog(View view) {
        CheckBox cb = findViewById(R.id.cb);
        if(cb.isChecked()){
            int respuestaMarcada = ((RadioGroup)findViewById(R.id.rg_respuestas)).getCheckedRadioButtonId();
            String strNombre = ((EditText)findViewById(R.id.txt_nombre)).getText().toString();
            if(strNombre.isEmpty() && respuestaMarcada==-1){
                Log.e("Camacho","Falta el nombre");
                Log.e("Camacho","Falta la respuesta");

            } else if (strNombre.isEmpty() && respuestaMarcada!=-1) {
                Log.e("Camacho","Falta el nombre");

            } else if(!strNombre.isEmpty() && respuestaMarcada==-1){
                Log.e("Camacho","Falta la respuesta");

            }
            else {
                int idGenero = ((RadioGroup)findViewById(R.id.rgElecciones)).getCheckedRadioButtonId();
                String strGenero = idGenero!=-1 ? ((RadioButton)findViewById(idGenero)).getText().toString() : "No se indicó el género";
                String strEdad = ((SwitchCompat)findViewById(R.id.swc)).isChecked() ? "Mayor de edad" : "Menor de edad";
                String strPregunta =(String) ((Spinner)findViewById(R.id.spinner)).getSelectedItem();
                String strRespuesta = ((RadioButton)findViewById(respuestaMarcada)).getText().toString();
                Log.i("Camacho",strNombre+"/"+strGenero+"/"+strEdad+"/"+strPregunta+"/"+strRespuesta);
            }
        }
    }
    private void eligePreguntas(View view){

        int idPreguntas;
        ArrayAdapter<CharSequence> adapter;

        SwitchCompat swc = (SwitchCompat) view;

        idPreguntas = swc.isChecked() ? R.array.preguntasMayor : R.array.preguntasMenor;
        adapter = ArrayAdapter.createFromResource(this,idPreguntas, android.R.layout.simple_spinner_dropdown_item);
        ((Spinner)findViewById(R.id.spinner)).setAdapter(adapter);
    }
    private void cambiaRespuestas(){

        ((RadioButton)findViewById(R.id.rb_respuesta1)).setChecked(false);
        ((RadioButton)findViewById(R.id.rb_respuesta2)).setChecked(false);
        ((RadioButton)findViewById(R.id.rb_respuesta3)).setChecked(false);

        if(((Spinner)findViewById(R.id.spinner)).getSelectedItem().equals("¿De que color es el cielo?")){
            ((RadioButton)findViewById(R.id.rb_respuesta1)).setText(getResources().getText(R.string.respuestaMa1_1));
            ((RadioButton)findViewById(R.id.rb_respuesta2)).setText(getResources().getText(R.string.respuestaMa1_2));
            ((RadioButton)findViewById(R.id.rb_respuesta3)).setText(getResources().getText(R.string.respuestaMa1_3));
        }
        if(((Spinner)findViewById(R.id.spinner)).getSelectedItem().equals("¿De que color es el tronco de un arbol?")){
            ((RadioButton)findViewById(R.id.rb_respuesta1)).setText(getResources().getText(R.string.respuestaMa2_1));
            ((RadioButton)findViewById(R.id.rb_respuesta2)).setText(getResources().getText(R.string.respuestaMa2_2));
            ((RadioButton)findViewById(R.id.rb_respuesta3)).setText(getResources().getText(R.string.respuestaMa2_3));
        }
        if(((Spinner)findViewById(R.id.spinner)).getSelectedItem().equals("¿Cuanto es 1+1?")){
            ((RadioButton)findViewById(R.id.rb_respuesta1)).setText(getResources().getText(R.string.respuestaMe1_1));
            ((RadioButton)findViewById(R.id.rb_respuesta2)).setText(getResources().getText(R.string.respuestaMe1_2));
            ((RadioButton)findViewById(R.id.rb_respuesta3)).setText(getResources().getText(R.string.respuestaMe1_3));
        }
        if(((Spinner)findViewById(R.id.spinner)).getSelectedItem().equals("¿Cuanto es 2+2?")){
            ((RadioButton)findViewById(R.id.rb_respuesta1)).setText(getResources().getText(R.string.respuestaMe2_1));
            ((RadioButton)findViewById(R.id.rb_respuesta2)).setText(getResources().getText(R.string.respuestaMe2_2));
            ((RadioButton)findViewById(R.id.rb_respuesta3)).setText(getResources().getText(R.string.respuestaMe2_3));
        }

    }
    private boolean condicionesCorrectas() {

        int respuestaMarcada = ((RadioGroup)findViewById(R.id.rg_respuestas)).getCheckedRadioButtonId();
         String strNombre = ((EditText)findViewById(R.id.txt_nombre)).getText().toString();
        if(strNombre.isEmpty() && respuestaMarcada==-1){
            Toast.makeText(this, getResources().getText(R.string.envioIncorrecto1)+"/"+getResources().getText(R.string.envioIncorrecto2), Toast.LENGTH_SHORT).show();
            return false;
        } else if (strNombre.isEmpty() && respuestaMarcada!=-1) {
            Toast.makeText(this, getResources().getText(R.string.envioIncorrecto1), Toast.LENGTH_SHORT).show();
            return false;
        } else if(!strNombre.isEmpty() && respuestaMarcada==-1){
            Toast.makeText(this, getResources().getText(R.string.envioIncorrecto2), Toast.LENGTH_SHORT).show();
            return false;
        }
        else {
            Toast.makeText(this, getResources().getText(R.string.envioCorrecto), Toast.LENGTH_LONG).show();
            return true;
        }
    }

}