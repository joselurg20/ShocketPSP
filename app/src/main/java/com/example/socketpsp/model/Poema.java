package com.example.socketpsp.model;

import java.io.Serializable;

public class Poema implements Serializable {
    private int id;
    private String titulo;
    private String contenido;
    private int puntos;

    public Poema(int id, String titulo, String contenido, int puntos) {
        this.id = id;
        this.titulo = titulo;
        this.contenido = contenido;
        this.puntos = puntos;
    }

    public Poema(String titulo, String contenido, int puntos) {
        this.titulo = titulo;
        this.contenido = contenido;
        this.puntos = puntos;
    }

    public Poema(){
        this.id = -1;
        this.titulo = "";
        this.contenido = "";
        this.puntos = -1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    @Override
    public String toString() {
        return "Poema{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", contenido='" + contenido + '\'' +
                ", puntos='" + puntos + '\'' +
                '}';
    }
}
