package Tema3_Serializacion;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class GuardarSerializacion {
    public static void main(String[] args) {
        Path ruta = Path.of("alumno.bin");
        AlumnoSerializable alumno = new AlumnoSerializable(17, "Lucía Fernández");

        // Usamos ObjectOutputStream envuelto en BufferedOutputStream para mejorar rendimiento[cite: 95]
        try (ObjectOutputStream salida = new ObjectOutputStream(
                new BufferedOutputStream(Files.newOutputStream(ruta)))) {
            
            salida.writeObject(alumno);
            System.out.println("¡Objeto serializado y guardado en disco con éxito!");

        } catch (IOException e) {
            System.err.println("Error al serializar: " + e.getMessage());
        }
    }
}

