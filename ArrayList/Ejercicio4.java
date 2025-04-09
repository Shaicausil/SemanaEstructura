package ArrayList;

import java.util.ArrayList;

public class Ejercicio4 {
    public static void main(String[] args) {
        ArrayList<String> animales = new ArrayList<>();
        animales.add("perro");
        animales.add("gato");
        animales.add("pájaro");
        animales.add("pez");
        animales.add("ratón");

        int indice = animales.indexOf("pájaro");
        if (indice != -1) {
            animales.set(indice, "hamster");
        }

        System.out.println("Lista actualizada de animales: " + animales);
    }
}

