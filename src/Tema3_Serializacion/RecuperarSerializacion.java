package Tema3_Serializacion;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class RecuperarSerializacion {
    public static void main(String[] args) {
        Path ruta = Path.of("alumno.bin");

        try (ObjectInputStream entrada = new ObjectInputStream(
                new BufferedInputStream(Files.newInputStream(ruta)))) {
            
            // Leemos el objeto y hacemos un "cast" a nuestra clase
            AlumnoSerializable alumnoRecuperado = (AlumnoSerializable) entrada.readObject();
            
            System.out.println("--- OBJETO RECUPERADO EXITOSAMENTE ---");
            System.out.println(alumnoRecuperado);

        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al deserializar: " + e.getMessage());
        }
    }
}
