package com.example.socketpsp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.socketpsp.R;
import com.example.socketpsp.model.Ardilla;
import com.example.socketpsp.service.ApiService;
import com.example.socketpsp.service.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends AppCompatActivity {

    private EditText txtNombre, txtDni, txtCorreo, txtPassword;
    private Button btnRegister;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrer);

        // Inicializar los EditText y el Button
        txtNombre = findViewById(R.id.txt_nombre);
        txtDni = findViewById(R.id.txt_dni);
        txtCorreo = findViewById(R.id.txt_correo);
        txtPassword = findViewById(R.id.txt_password);
        btnRegister = findViewById(R.id.btn_register);

        // Inicializar ApiService utilizando Retrofit
        apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);

        // Configurar el evento click del botón de registro
        btnRegister.setOnClickListener(v -> registrarArdilla());
    }


    // Luego, puedes llamar a esta función en tu método registrarArdilla()
    private void registrarArdilla() {
        // Obtener los datos de los EditText
        String nombre = txtNombre.getText().toString();
        String dni = txtDni.getText().toString();
        String correo = txtCorreo.getText().toString();
        String password = txtPassword.getText().toString();


        int puntos = 0;
        int id = 0;


        Ardilla ardilla = new Ardilla(id, nombre, dni, correo, password, puntos);

        // Llamar al método createOrUpdateArdilla de ApiService para crear la ardilla
        Call<Ardilla> call = apiService.createOrUpdateArdilla(ardilla);
        call.enqueue(new Callback<Ardilla>() {
            @Override
            public void onResponse(Call<Ardilla> call, Response<Ardilla> response) {
                if (response.isSuccessful()) {
                    // La ardilla se creó correctamente
                    Toast.makeText(Register.this, "Ardilla creada correctamente", Toast.LENGTH_SHORT).show();
                } else {
                    // Hubo un error al crear la ardilla
                    Toast.makeText(Register.this, "Error al crear la ardilla", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Ardilla> call, Throwable t) {
                // Manejar el error de la solicitud
                Toast.makeText(Register.this, "Error de conexión", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
