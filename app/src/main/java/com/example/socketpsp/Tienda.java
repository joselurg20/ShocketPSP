package com.example.socketpsp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import com.example.socketpsp.Menu;

public class Tienda extends AppCompatActivity {

    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tienda);

        btnBack = findViewById(R.id.btn_back);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Devolver los datos de la ardilla a la actividad 'Menu'
                Intent intent = new Intent(Tienda.this, Menu.class);
                intent.putExtra("nombreArdilla", getIntent().getStringExtra("nombreArdilla"));
                intent.putExtra("numeroPuntos", getIntent().getIntExtra("numeroPuntos", 0));
                startActivity(intent);
            }
        });
    }
}
