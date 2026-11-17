package Tema4_XML_Analizadores;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.nio.file.Path;

public class FiltrarNodosTextoDOM {
    public static void main(String[] args) {
        try {
            DocumentBuilderFactory fabrica = DocumentBuilderFactory.newInstance();
            Document documento = fabrica.newDocumentBuilder().parse(Path.of("centro.xml").toFile());

            Element raiz = documento.getDocumentElement();
            NodeList hijos = raiz.getChildNodes();

            System.out.println("Recorriendo hijos directos de forma segura:");
            for (int i = 0; i < hijos.getLength(); i++) {
                Node hijo = hijos.item(i);

                // FILTRO CLAVE: Si el nodo NO es un elemento XML (es un salto de línea/espacio), lo ignoramos[cite: 126]
                if (hijo.getNodeType() != Node.ELEMENT_NODE) {
                    continue; 
                }

                Element elementoReal = (Element) hijo;
                System.out.println(" -> Etiqueta encontrada: " + elementoReal.getTagName());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
