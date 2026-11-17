package Tema1_Gestion_Ficheros;

import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.io.IOException;

public class CopiarYBorrarFichero {
    public static void main(String[] args) {
        Path original = Path.of("centro", "exportaciones", "alumnos.txt");
        Path copia = Path.of("centro", "exportaciones", "alumnos_copia.txt");

        try {
            if (Files.exists(original)) {
                // 1. Copiar el fichero permitiendo sobrescribir si ya existía la copia[cite: 48, 49]
                Files.copy(original, copia, StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Fichero copiado correctamente.");
            }

            // 2. Borrar un fichero de forma segura (si no existe, devuelve false en vez de romper el programa)[cite: 49]
            boolean borrado = Files.deleteIfExists(copia);
            if (borrado) {
                System.out.println("Fichero de copia borrado con éxito.");
            }

        } catch (IOException e) {
            System.err.println("Error en la operación: " + e.getMessage());
        }
    }
}
