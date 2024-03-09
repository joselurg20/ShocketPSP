package com.example.socketpsp.model;

import java.io.Serializable;

public class Ardilla implements Serializable {
    private int id;

    private String email;

    private String password;

    private String nombre;
    private int puntos;

    public Ardilla(int id, String email, String password, String nombre, int puntos) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.nombre = nombre;
        this.puntos = puntos;
    }

    public Ardilla() {
        this.id = -1;
        this.email= "";
        this.password = "";
        this.nombre = "";
        this.puntos = -1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Ardilla{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", nombre='" + nombre + '\'' +
                ", puntos=" + puntos +
                '}';
    }
}
