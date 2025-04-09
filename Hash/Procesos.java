package Hash;

import javax.swing.*;
import java.util.ArrayList;

public class Procesos {

    // Instancia del modelo de datos
    ModeloDatos miData;

    // Contadores de estudiantes
    int ganan = 0, pierden = 0, recuperan = 0;

    // Constructor: inicia la aplicación
    public Procesos() {
        miData = new ModeloDatos();
        menu();
    }

    // Menú principal del sistema
    private void menu() {
        int opcion = -1;

        do {
            String input = JOptionPane.showInputDialog("""
                MENÚ DEL SISTEMA
                1. Registrar estudiante
                2. Consultar estudiante por documento
                3. Consultar lista de estudiantes
                4. Cantidad total de estudiantes
                5. Cantidad total de notas ingresadas
                6. Sumatoria total de notas
                7. Promedio de notas finales
                8. Estudiantes que ganan
                9. Estudiantes que pierden
                10. Estudiantes que pueden recuperar
                11. Estudiantes que pierden sin recuperación
                0. Salir
                """);

            if (input == null) return;

            if (esNumeroValido(input)) {
                opcion = Integer.parseInt(input);
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese solo números válidos.");
                continue;
            }

            switch (opcion) {
                case 1:
                    registrarEstudiante();
                    break;
                case 2:
                    String doc = JOptionPane.showInputDialog("Ingrese documento:");
                    miData.consultarEstudiante(doc);
                    break;
                case 3:
                    miData.imprimirMapa();
                    break;
                case 4:
                    JOptionPane.showMessageDialog(null, "Total estudiantes: " + miData.getCantidadEstudiantes());
                    break;
                case 5:
                    JOptionPane.showMessageDialog(null, "Cantidad total de notas: " + (miData.getCantidadEstudiantes() * 3));
                    break;
                case 6:
                    JOptionPane.showMessageDialog(null, "Suma total de notas: " + sumarNotasTotales());
                    break;
                case 7:
                    JOptionPane.showMessageDialog(null, "Promedio de notas finales: " + promedioFinalNotas());
                    break;
                case 8:
                    JOptionPane.showMessageDialog(null, "Estudiantes que ganan: " + ganan);
                    break;
                case 9:
                    JOptionPane.showMessageDialog(null, "Estudiantes que pierden: " + pierden);
                    break;
                case 10:
                    JOptionPane.showMessageDialog(null, "Estudiantes que pueden recuperar: " + recuperan);
                    break;
                case 11:
                    int sinRecuperar = miData.getCantidadEstudiantes() - (ganan + recuperan);
                    JOptionPane.showMessageDialog(null, "Pierden sin recuperación: " + sinRecuperar);
                    break;
                case 0:
                    JOptionPane.showMessageDialog(null, "Saliendo del sistema...");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida.");
                    break;
            }

        } while (true);
    }

    // Registrar un estudiante con sus notas
    private void registrarEstudiante() {
        ArrayList<String> datos = new ArrayList<>();

        String doc = JOptionPane.showInputDialog("Ingrese documento:");
        String nombre = JOptionPane.showInputDialog("Ingrese nombre:");
        String materia = JOptionPane.showInputDialog("Ingrese materia:");

        double n1 = validarNota("Nota 1:");
        double n2 = validarNota("Nota 2:");
        double n3 = validarNota("Nota 3:");

        datos.add(doc);
        datos.add(nombre);
        datos.add(materia);
        datos.add(String.valueOf(n1));
        datos.add(String.valueOf(n2));
        datos.add(String.valueOf(n3));

        miData.guardarDatos(datos);

        double promedio = (n1 + n2 + n3) / 3;

        if (promedio >= 3.5) {
            ganan++;
            JOptionPane.showMessageDialog(null, "Promedio: " + promedio + "\nResultado: GANA");
        } else if (promedio >= 2) {
            recuperan++;
            pierden++;
            JOptionPane.showMessageDialog(null, "Promedio: " + promedio + "\nResultado: PUEDE RECUPERAR");
        } else {
            pierden++;
            JOptionPane.showMessageDialog(null, "Promedio: " + promedio + "\nResultado: PIERDE SIN RECUPERACIÓN");
        }
    }

    // Validar que una nota esté entre 0 y 5
    private double validarNota(String mensaje) {
        double nota = -1;
        while (nota < 0 || nota > 5) {
            String input = JOptionPane.showInputDialog(mensaje);
            if (input == null) System.exit(0);
            if (esNumeroDecimalValido(input)) {
                nota = Double.parseDouble(input);
                if (nota < 0 || nota > 5) {
                    JOptionPane.showMessageDialog(null, "La nota debe estar entre 0 y 5.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese un número válido.");
            }
        }
        return nota;
    }

    // Verifica si una cadena representa un número entero positivo válido
    private boolean esNumeroValido(String texto) {
        if (texto == null || texto.isEmpty()) return false;
        for (int i = 0; i < texto.length(); i++) {
            if (!Character.isDigit(texto.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    // Verifica si una cadena representa un número decimal positivo válido
    private boolean esNumeroDecimalValido(String texto) {
        if (texto == null || texto.isEmpty()) return false;
        int puntos = 0;
        for (int i = 0; i < texto.length(); i++) {
            char c = texto.charAt(i);
            if (c == '.') {
                puntos++;
                if (puntos > 1) return false;
            } else if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    // Sumar todas las notas ingresadas
    private double sumarNotasTotales() {
        double suma = 0;
        for (ArrayList<String> datos : miData.getMapaEstudiantes().values()) {
            suma += Double.parseDouble(datos.get(3));
            suma += Double.parseDouble(datos.get(4));
            suma += Double.parseDouble(datos.get(5));
        }
        return suma;
    }

    // promedio general
    private double promedioFinalNotas() {
        double sumaPromedios = 0;
        int total = miData.getCantidadEstudiantes();

        for (ArrayList<String> datos : miData.getMapaEstudiantes().values()) {
            double n1 = Double.parseDouble(datos.get(3));
            double n2 = Double.parseDouble(datos.get(4));
            double n3 = Double.parseDouble(datos.get(5));
            sumaPromedios += (n1 + n2 + n3) / 3;
        }

        return total > 0 ? sumaPromedios / total : 0;
    }
}
