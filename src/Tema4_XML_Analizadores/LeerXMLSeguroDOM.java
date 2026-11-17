package Tema4_XML_Analizadores;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import java.nio.file.Path;

public class LeerXMLSeguroDOM {
    public static void main(String[] args) {
        try {
            Path ruta = Path.of("centro.xml");

            // 1. Crear la fábrica y activar espacios de nombres[cite: 122]
            DocumentBuilderFactory fabrica = DocumentBuilderFactory.newInstance();
            fabrica.setNamespaceAware(true);

            // 💡 MEDIDA DE SEGURIDAD OBLIGATORIA: Prohibir declaraciones DOCTYPE externas[cite: 127]
            fabrica.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);

            DocumentBuilder constructor = fabrica.newDocumentBuilder();
            Document documento = constructor.parse(ruta.toFile());

            // 2. Extraer elementos por su etiqueta
            NodeList listaAlumnos = documento.getElementsByTagName("alumno");
            System.out.println("Alumnos encontrados en el XML: " + listaAlumnos.getLength());

        } catch (Exception e) {
            System.err.println("Error analizando el XML de forma segura: " + e.getMessage());
        }
    }
}
