package Tema3_Serializacion;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;

// La clase DEBE implementar Serializable
class AlumnoSerializable implements Serializable {
    private static final long serialVersionUID = 1L; // Identificador de versión fijo[cite: 98]
    private int id;
    private String nombre;

    public AlumnoSerializable(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Alumno [id=" + id + ", nombre=" + nombre + "]";
    }
}

