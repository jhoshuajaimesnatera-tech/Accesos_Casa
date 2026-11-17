package Tema2_Ficheros_Propiedesdes;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class CargarPropiedadesBasico {
    public static void main(String[] args) {
        Path rutaConfig = Path.of("config.properties");

        Properties config = new Properties();

        // Usamos BufferedReader con UTF-8 para evitar la "trampa de la codificación" clásica
        try (BufferedReader lector = Files.newBufferedReader(rutaConfig, StandardCharsets.UTF_8)) {
            config.load(lector);

            // Leemos propiedades pasándole la clave exacta
            String url = config.getProperty("bd.url");
            String usuario = config.getProperty("bd.usuario");

            System.out.println("URL de BD leída: " + url);
            System.out.println("Usuario leído: " + usuario);

        } catch (Exception e) {
            System.err.println("No se pudo leer la configuración: " + e.getMessage());
        }
    }
}