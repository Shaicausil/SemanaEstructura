package ArrayList;

import java.util.ArrayList;

public class Ejercicio1 {
    public static void main(String[] args) {
        ArrayList<String> colores = new ArrayList<>();
        colores.add("rojo");
        colores.add("azul");
        colores.add("verde");
        colores.add("amarillo");
        colores.add("negro");

        System.out.println("Cantidad de palabras en la lista: " + colores.size());
    }
}

