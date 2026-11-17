package Tema3_Serializacion;

import java.io.Serializable;

class ConexionOculta implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String usuario = "admin";
    
    // Este campo NUNCA se guardará en el fichero binario (quedará como null al recuperar)[cite: 97]
    private transient String passwordSecreta = "123456"; 

    public void mostrarDatos() {
        System.out.println("Usuario: " + usuario);
        System.out.println("Password (si es transient será null): " + passwordSecreta);
    }
}


