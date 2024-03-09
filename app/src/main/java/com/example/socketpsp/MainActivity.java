package com.example.socketpsp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.socketpsp.DAO.ArdillaDAO;
import com.example.socketpsp.model.Ardilla;

public class MainActivity extends AppCompatActivity {

    private EditText txtCorreo, txtPassword;
    private Button btnRegister, btnInicio;
    private ArdillaDAO ardillaDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar ArdillaDAO
        ardillaDAO = new ArdillaDAO(this);
        ardillaDAO.open();

        // Inicializar los EditText y los botones
        txtCorreo = findViewById(R.id.txt_correo);
        txtPassword = findViewById(R.id.txt_password);
        btnRegister = findViewById(R.id.btn_register);
        btnInicio = findViewById(R.id.btn_inicio);

        // Configurar OnClickListener para el botón de Registro
        btnRegister.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Register.class);
            startActivity(intent);
        });

        // Configurar OnClickListener para el botón de Inicio de Sesión
        btnInicio.setOnClickListener(v -> iniciarSesion());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ardillaDAO.close(); // Cerrar la conexión a la base de datos al destruir la actividad
    }

    private void iniciarSesion() {
        // Obtener el correo electrónico y la contraseña ingresados por el usuario
        String correo = txtCorreo.getText().toString();
        String password = txtPassword.getText().toString();

        // Consultar la base de datos para verificar las credenciales
        Ardilla ardilla = ardillaDAO.getArdillaByEmailAndPassword(correo, password);

        if (ardilla != null) {
            // Las credenciales son válidas, iniciar sesión y abrir el menú principal
            Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, Menu.class);
            startActivity(intent);
        } else {
            // Las credenciales son inválidas, mostrar un mensaje de error
            Toast.makeText(this, "Correo electrónico o contraseña incorrectos", Toast.LENGTH_SHORT).show();
        }
    }
}
