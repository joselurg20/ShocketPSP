package com.example.socketpsp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Menu extends AppCompatActivity {

    // Declaración de variables para los elementos de la vista
    private TextView textView;
    private Button btnIrTienda;
    private Button btnIrPoema;
    private Button btnIrDescansar;
    private TextView txtNombreArdilla;
    private TextView txtNumeroPoemas;
    private TextView txtNumeroPuntos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ardilla_menu);

        // Asociar las variables con los elementos de la vista
        textView = findViewById(R.id.textView);
        btnIrTienda = findViewById(R.id.btn_irtienda);
        btnIrPoema = findViewById(R.id.btn_irpoema);
        btnIrDescansar = findViewById(R.id.btn_irdecansar);
        txtNombreArdilla = findViewById(R.id.txt_nombreardilla);
        txtNumeroPoemas = findViewById(R.id.txt_numeropoemas);
        txtNumeroPuntos = findViewById(R.id.txt_numeropuntos);
    }
}
