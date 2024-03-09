package com.example.socketpsp.conexiones;

import com.example.socketpsp.model.Ardilla;
import com.example.socketpsp.model.Poema;

import java.io.*;
import java.net.Socket;
import java.util.Map;
import java.util.Set;

public class ArdillaHandler implements Runnable {
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private Ardilla ardilla;

    // Debes tener acceso a tus estructuras de datos estáticas definidas en el servidor
    private static Map<Ardilla, ObjectOutputStream> clientes;
    private static Map<Ardilla, Poema> poemasRecogidos;

    public ArdillaHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());

            // Realizar el login de la ardilla
            boolean isLogged = false;
            while (!isLogged) {
                ardilla = (Ardilla) in.readObject();
                synchronized (clientes) {
                    if (!clientes.containsKey(ardilla)) {
                        out.writeObject(true); // Envía confirmación de que el login fue exitoso
                        out.flush();
                        clientes.put(ardilla, out);
                        isLogged = true;
                    } else {
                        out.writeObject(false); // Envía negación de que el login fue exitoso
                        out.flush();
                    }
                }
            }

            // Lógica de recolección de poemas
            while (true) {
                Poema poemaRecibido = (Poema) in.readObject();
                synchronized (poemasRecogidos) {
                    poemasRecogidos.put(ardilla, poemaRecibido);
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            // Si la ardilla se desconecta, la eliminamos del mapa de clientes
            if (ardilla != null) {
                clientes.remove(ardilla);
            }
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
