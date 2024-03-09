package com.example.socketpsp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

   private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogin = findViewById(R.id.btn_register);

        // Configura un OnClickListener para el botón de inicio de sesión
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Método llamado cuando se hace clic en el botón
                navigateToMainActivity(); // Método para iniciar la actividad MainActivity
            }
        });
    }


    private void navigateToMainActivity() {
        // Crea un Intent para iniciar la actividad MainActivity
        Intent intent = new Intent(this, Registrer.class);
        startActivity(intent); // Inicia la actividad
    }
}