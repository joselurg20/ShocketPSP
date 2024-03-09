package com.example.socketpsp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.socketpsp.DAO.ArdillaDAO;
import com.example.socketpsp.DAO.PoemaDAO;
import com.example.socketpsp.conexiones.ClienteSocket;
import com.example.socketpsp.model.Ardilla;
import com.example.socketpsp.model.Poema;

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

        crearPoemasIniciales();
    }

    private void crearPoemasIniciales() {
        // Crear 20 poemas iniciales y agregarlos a la base de datos
        PoemaDAO poemaDAO = new PoemaDAO(this);
        poemaDAO.open();

        for (int i = 1; i <= 20; i++) {
            String titulo = "Poema " + i;
            String contenido = "Contenido del poema " + i;
            int puntos = i * 10; // Asignar puntos según el número de poema

            // Crear el poema y agregarlo a la base de datos
            Poema poema = new Poema(titulo, contenido, puntos);
            poemaDAO.insertPoema(poema);
        }

        // Registrar un mensaje de log indicando que los poemas se han creado exitosamente
        Log.d("MainActivity", "Poemas creados exitosamente");

        // Cerrar la conexión a la base de datos
        poemaDAO.close();
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

        // Iniciar la conexión con el servidor y enviar los datos de inicio de sesión
        ClienteSocket clienteSocket = new ClienteSocket(correo, password);
        clienteSocket.enviarCredenciales();


        if (ardilla != null) {
            // Las credenciales son válidas, iniciar sesión y abrir el menú principal
            Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, Menu.class);
            // Pasar datos de la ardilla al intent
            intent.putExtra("nombreArdilla", ardilla.getNombre());
            intent.putExtra("numeroPuntos", ardilla.getPuntos());
            startActivity(intent);
        } else {
            // Las credenciales son inválidas, mostrar un mensaje de error
            Toast.makeText(this, "Correo electrónico o contraseña incorrectos", Toast.LENGTH_SHORT).show();
        }
    }

}
