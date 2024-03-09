package com.example.socketpsp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.socketpsp.DAO.ArdillaDAO;
import com.example.socketpsp.DAO.PoemaDAO;
import com.example.socketpsp.Tienda;
import com.example.socketpsp.model.Poema;
import java.util.List;
import java.util.Random;

public class Menu extends AppCompatActivity {

    private TextView textView;
    private Button btnIrTienda;
    private Button btnIrPoema;
    private Button btnIrDescansar;
    private TextView txtNombreArdilla;
    private TextView txtNumeroPoemas;
    private TextView txtNumeroPuntos;

    private List<Poema> listaPoemas;
    private Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ardilla_menu);

        // Inicializar el generador de números aleatorios
        random = new Random();

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

        // Configurar OnClickListener para el botón Ir a por poema
        btnIrPoema.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener un poema aleatorio de la base de datos
                Poema poema = obtenerPoemaAleatorio();

                // Obtener el texto de los TextViews y verificar si están vacíos
                String textoNumeroPoemas = txtNumeroPoemas.getText().toString();
                String textoNumeroPuntos = txtNumeroPuntos.getText().toString();

                int numeroPoemas = 0;
                int numeroPuntos = 0;

                if (!textoNumeroPoemas.isEmpty()) {
                    numeroPoemas = Integer.parseInt(textoNumeroPoemas) + 1;
                }

                if (!textoNumeroPuntos.isEmpty()) {
                    numeroPuntos = Integer.parseInt(textoNumeroPuntos) + poema.getPuntos();
                }

                // Actualizar los puntos de la ardilla en la base de datos
                ArdillaDAO ardillaDAO = new ArdillaDAO(Menu.this);
                ardillaDAO.open();
                ardillaDAO.actualizarPuntosArdilla(nombreArdilla, numeroPuntos);
                ardillaDAO.close();

                // Mostrar los nuevos valores en los TextView correspondientes
                txtNumeroPoemas.setText(String.valueOf(numeroPoemas));
                txtNumeroPuntos.setText(String.valueOf(numeroPuntos));
            }
        });


        // Configurar OnClickListener para el botón Ir a tienda
        btnIrTienda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Aquí iniciamos la actividad 'activity_tienda'
                Intent intent = new Intent(Menu.this, Tienda.class);
                startActivity(intent);
            }
        });

        // Configurar OnClickListener para el botón Ir a descansar
        btnIrDescansar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Aquí iniciamos la actividad MainActivity
                Intent intent = new Intent(Menu.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    // Método para obtener un poema aleatorio de la base de datos
    private Poema obtenerPoemaAleatorio() {
        // Obtener la lista de poemas de la base de datos
        if (listaPoemas == null) {
            PoemaDAO poemaDAO = new PoemaDAO(this);
            poemaDAO.open();
            listaPoemas = poemaDAO.getAllPoemas();
            poemaDAO.close();
        }

        // Obtener un índice aleatorio dentro del rango de la lista de poemas
        int indiceAleatorio = random.nextInt(listaPoemas.size());

        // Devolver el poema correspondiente al índice aleatorio
        return listaPoemas.get(indiceAleatorio);
    }
}
