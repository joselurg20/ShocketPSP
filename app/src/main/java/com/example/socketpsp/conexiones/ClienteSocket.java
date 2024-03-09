package com.example.socketpsp.conexiones;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClienteSocket {
    private static final String SERVER_ADDRESS = "192.168.1.100"; // Dirección IP del servidor
    private static final int SERVER_PORT = 8080;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private Socket socket;
    private String correo;
    private String password;

    public ClienteSocket(String correo, String password) {
        this.correo = correo;
        this.password = password;
    }

    public void enviarCredenciales() {
        // Ejecutar la operación de conexión en un hilo separado
        new Thread(() -> {
            try {
                socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
                out = new ObjectOutputStream(socket.getOutputStream());
                in = new ObjectInputStream(socket.getInputStream());

                // Enviar datos de inicio de sesión al servidor
                out.writeObject(correo);
                out.writeObject(password);
                out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    // Método para enviar otros datos al servidor, si es necesario
    // public void enviarDatos(Object datos) {
    //     try {
    //         out.writeObject(datos);
    //         out.flush();
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }
    // }

    // Resto del código, como métodos para recibir datos del servidor, cerrar conexiones, etc.
}
