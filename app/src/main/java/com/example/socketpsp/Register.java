package com.example.socketpsp;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.socketpsp.DAO.ArdillaDAO;
import com.example.socketpsp.model.Ardilla;

public class Register extends AppCompatActivity {

    private EditText txtNombre, txtDni, txtCorreo, txtPassword;
    private Button btnRegister;
    private ArdillaDAO ardillaDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrer);

        // Inicializar ArdillaDAO
        ardillaDAO = new ArdillaDAO(this);
        ardillaDAO.open();

        // Inicializar los EditText y el Button
        txtNombre = findViewById(R.id.txt_nombre);
        txtDni = findViewById(R.id.txt_dni);
        txtCorreo = findViewById(R.id.txt_correo);
        txtPassword = findViewById(R.id.txt_password);
        btnRegister = findViewById(R.id.btn_register);

        // Configurar el evento click del botón de registro
        btnRegister.setOnClickListener(v -> registrarArdilla());

        ImageView imgVolver = findViewById(R.id.img_volver);
        imgVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crear intent para ir a la actividad Register
                Intent intent = new Intent(Register.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ardillaDAO.close(); // Cerrar la conexión a la base de datos al destruir la actividad
    }

    private void registrarArdilla() {
        // Obtener los datos de los EditText
        String nombre = txtNombre.getText().toString();
        String dni = txtDni.getText().toString();
        String correo = txtCorreo.getText().toString();
        String password = txtPassword.getText().toString();
        int puntos = 0;

        // Crear un objeto Ardilla
        Ardilla ardilla = new Ardilla(0, dni, correo, password, nombre, puntos);

        // Insertar la ardilla en la base de datos
        long resultado = ardillaDAO.insertArdilla(ardilla);

        if (resultado != -1) {
            // La ardilla se insertó correctamente
            Toast.makeText(this, "Ardilla registrada correctamente", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Register.this, MainActivity.class);
            startActivity(intent);
        } else {
            // Ocurrió un error al insertar la ardilla
            Toast.makeText(this, "Error al registrar la ardilla", Toast.LENGTH_SHORT).show();
        }
    }
}
