package com.example.socketpsp.conexiones;

import com.example.socketpsp.model.Ardilla;
import com.example.socketpsp.model.Poema;

import java.io.*;
import java.net.Socket;
import java.util.Map;

public class ClienteSocket {
    private static final String SERVER_ADDRESS = "192.168.0.107"; // Dirección IP del servidor
    private static final int SERVER_PORT = 8081;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private Socket socket;
    private Ardilla ardilla;

    public void iniciarSesion(final Ardilla ardilla) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
                    out = new ObjectOutputStream(socket.getOutputStream());
                    in = new ObjectInputStream(socket.getInputStream());

                    // Enviar la ardilla al servidor para iniciar sesión
                    out.writeObject(ardilla);
                    out.flush();

                    // Recibir la confirmación de inicio de sesión del servidor
                    boolean isLogged = (boolean) in.readObject();
                    if (isLogged) {
                        // Inicio de sesión exitoso
                        System.out.println("Inicio de sesión exitoso");
                    } else {
                        // Inicio de sesión fallido
                        System.out.println("Inicio de sesión fallido");
                    }
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (socket != null) {
                            socket.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }


    public void enviarPoema(Poema poema) {
        try {
            // Enviar un poema al servidor
            out.writeObject(poema);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Resto del código, como métodos para recibir datos del servidor, cerrar conexiones, etc.
}
