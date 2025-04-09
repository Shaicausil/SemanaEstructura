package ArrayList;

import java.util.ArrayList;

public class Ejercicio3 {
    public static void main(String[] args) {
        ArrayList<Integer> valores = new ArrayList<>();
        valores.add(5);
        valores.add(10);
        valores.add(15);
        valores.add(20);
        valores.add(25);

        int suma = 0;
        for (int valor : valores) {
            suma += valor;
        }

        double promedio = (double) suma / valores.size();
        System.out.println("Promedio: " + promedio);
    }
}
