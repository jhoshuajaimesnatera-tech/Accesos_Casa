package Tema1_Gestion_Ficheros;

import java.nio.file.Path;
import java.nio.file.Files;
import java.io.IOException;

public class CrearRutaYCarpetas {
    public static void main(String[] args) {
        // 1. Creamos una ruta portable uniendo trozos con .of() y .resolve()
        Path carpetaDestino = Path.of("centro", "2026", "exportaciones");
        
        try {
            // 2. Creamos todas las carpetas de golpe (el plural 'Directories' no falla si ya existen)[cite: 47]
            Files.createDirectories(carpetaDestino);
            System.out.println("Carpetas creadas con éxito en: " + carpetaDestino.toAbsolutePath());
        } catch (IOException e) {
            System.err.println("Error al crear las carpetas: " + e.getMessage());
        }
    }
}
