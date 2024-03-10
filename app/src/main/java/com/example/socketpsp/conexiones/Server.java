package com.example.socketpsp.conexiones;


import com.example.socketpsp.model.Ardilla;
import com.example.socketpsp.model.Poema;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private static final int PORT = 8081;
    private static final int MAX_CLIENTS = 100;

    // Mapa que mantiene un seguimiento de las conexiones de los clientes (Ardillas) y sus respectivos ObjectOutputStream
    static Map<Ardilla, ObjectOutputStream> clientes = new HashMap<>();

    // Mapa que mantiene un seguimiento de los poemas recogidos por cada Ardilla
    static Map<Ardilla, Poema> poemasRecogidos = new HashMap<>();

    // ExecutorService gestiona un conjunto de hilos para manejar las conexiones de los clientes
    static ExecutorService pool = Executors.newFixedThreadPool(MAX_CLIENTS);

    public static void main(String[] args) {
        System.out.println("Servidor de Ardillas Atómicas está en ejecución...");

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                // Acepta nuevas conexiones de clientes y las delega a un nuevo hilo de ManejadorCliente
                pool.execute(new ManejadorCliente(serverSocket.accept()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para enviar un poema a todos los clientes conectados
    static void broadcast(Poema poema) throws IOException {
        synchronized (clientes) {
            for (ObjectOutputStream cliente : clientes.values()) {
                cliente.writeObject(poema);
                cliente.flush();
            }
        }
    }

    // Clase interna que maneja la conexión con un cliente individual
    static class ManejadorCliente implements Runnable {
        private Socket socket;

        ManejadorCliente(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                // Establece un ObjectOutputStream para enviar datos al cliente
                ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
                // Crea una nueva Ardilla y la agrega al mapa de clientes
                Ardilla ardilla = new Ardilla();
                clientes.put(ardilla, outputStream);

                // Mostrar mensaje cuando una ardilla se conecta
                System.out.println("Ardilla conectada: " + ardilla);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
