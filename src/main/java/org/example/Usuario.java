package org.example;

// Usuario.java
public class Usuario {
    String nombre;
    int prioridad;

    public Usuario(String nombre, int prioridad) {
        this.nombre = nombre;
        this.prioridad = prioridad;
    }

    @Override
    public String toString() {
        return nombre + " (Prioridad: " + prioridad + ")";
    }
}
