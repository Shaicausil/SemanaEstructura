package ArrayList;

import java.util.ArrayList;

public class Ejercicio2 {
    public static void main(String[] args) {
        ArrayList<Integer> numeros = new ArrayList<>();
        numeros.add(10);
        numeros.add(20);
        numeros.add(30);
        numeros.add(40);
        numeros.add(50);

        if (numeros.contains(30)) {
            System.out.println("Encontrado");
        } else {
            System.out.println("No encontrado");
        }
    }
}

