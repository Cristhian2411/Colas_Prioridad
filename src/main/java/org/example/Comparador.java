package org.example;// ComparadorUsuario.java
import java.util.Comparator;

public class Comparador implements Comparator<Usuario> {
    @Override
    public int compare(Usuario u1, Usuario u2) {
        return Integer.compare(u2.prioridad, u1.prioridad); // Mayor prioridad primero
    }
}
