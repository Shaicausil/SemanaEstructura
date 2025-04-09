package ArrayList;

import java.util.ArrayList;

public class Ejercicio5 {
    public static void main(String[] args) {
        ArrayList<Integer> listaNumeros = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            listaNumeros.add(i);
        }

        ArrayList<Integer> sublista = new ArrayList<>();
        for (int i = 3; i <= 6; i++) {
            sublista.add(listaNumeros.get(i));
        }

        System.out.println("Sublista del 4 al 7: " + sublista);
    }
}
