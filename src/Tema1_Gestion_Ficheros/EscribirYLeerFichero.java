package Tema1_Gestion_Ficheros;

import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.charset.StandardCharsets;
import java.io.IOException;

public class EscribirYLeerFichero {
    public static void main(String[] args) {
        Path fichero = Path.of("centro", "exportaciones", "alumnos.txt");

        try {
            // Aseguramos que la carpeta padre existe antes de escribir[cite: 47]
            Files.createDirectories(fichero.getParent());

            // 1. Escribir texto indicando explícitamente UTF-8
            String contenido = "17|Lucia Fernández Ortiz|2006-04-12\n";
            Files.writeString(fichero, contenido, StandardCharsets.UTF_8);

            // 2. Leer todo el contenido de vuelta
            String leido = Files.readString(fichero, StandardCharsets.UTF_8);
            System.out.println("Texto recuperado del fichero:\n" + leido);

        } catch (IOException e) {
            System.err.println("Error de E/S: " + e.getMessage());
        }
    }
}
