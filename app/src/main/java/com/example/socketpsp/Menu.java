package com.example.socketpsp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
        textView = findViewById(R.id.textView1);
        btnIrTienda = findViewById(R.id.btn_irtienda);
        btnIrPoema = findViewById(R.id.btn_irpoema);
        btnIrDescansar = findViewById(R.id.btn_irdecansar);
        txtNombreArdilla = findViewById(R.id.txt_nombreardilla);
        txtNumeroPoemas = findViewById(R.id.txt_numeropoemas);
        txtNumeroPuntos = findViewById(R.id.txt_numeropuntos);

        Intent intent = getIntent();
        String nombreArdilla = intent.getStringExtra("nombreArdilla");
        int numeroPuntos = intent.getIntExtra("numeroPuntos", 0);

        // Mostrar los datos de la ardilla en los TextViews correspondientes
        txtNombreArdilla.setText(nombreArdilla);
        txtNumeroPuntos.setText(String.valueOf(numeroPuntos));

        // Configurar OnClickListener para el botón Ir a tienda
        btnIrTienda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Aquí iniciamos la actividad 'activity_tienda'
                Intent intent = new Intent(Menu.this, Tienda.class);
                startActivity(intent);
            }
        });

        btnIrDescansar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Aquí iniciamos la actividad MainActivity
                Intent intent = new Intent(Menu.this, MainActivity.class);
                startActivity(intent);
            }
        });


    }


}
