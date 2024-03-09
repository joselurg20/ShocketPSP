package com.example.socketpsp.model;

public class Ardilla {
    private int id;
    private String dni;
    private String email;
    private String password;
    private String nombre;
    private int puntos;

    // Constructor vacío necesario para Retrofit
    public Ardilla() {
    }

    // Constructor con parámetros para crear una instancia de Ardilla
    public Ardilla(int id, String dni, String email, String password, String nombre, int puntos) {
        this.id = id;
        this.dni = dni;
        this.email = email;
        this.password = password;
        this.nombre = nombre;
        this.puntos = puntos;
    }

    // Getters y setters para todos los campos de Ardilla

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }
}
