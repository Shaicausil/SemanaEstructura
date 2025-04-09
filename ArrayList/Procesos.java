package ArrayList;

import javax.swing.*;
import java.util.ArrayList;

public class Procesos {
    ArrayList<String> listaProfesores;
    ArrayList<ArrayList<String>> listaGeneralEstudiantes;

    public Procesos() {
        listaProfesores = new ArrayList<>();
        listaGeneralEstudiantes = new ArrayList<>();
    }

    public void iniciar() {
        int opcion;
        do {
            String menu = """
                    MENÚ:
                    1. Registrar profesores
                    2. Registrar estudiantes
                    3. Ver todos los profesores y sus estudiantes
                    4. Consultar un profesor y sus estudiantes
                    5. Consultar cantidad de estudiantes de un profesor
                    6. Consultar director de grupo de un estudiante
                    0. Salir
                    Seleccione una opción:""";
            String input = JOptionPane.showInputDialog(menu);
            if (input == null) return;
            opcion = Integer.parseInt(input);

                switch (opcion) {
                    case 1:
                        registrarProfesores();
                        break;
                    case 2:
                        registrarEstudiantes();
                        break;
                    case 3:
                        consultarListaProfesoresYEstudiantes();
                        break;
                    case 4:
                        consultaProfesorPorNombre();
                        break;
                    case 5:
                        consultaCantidadEstudiantesPorProfesor();
                        break;
                    case 6:
                        consultarEstudiante();
                        break;
                    case 0:
                        JOptionPane.showMessageDialog(null, "¡Hasta luego!");
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Opción inválida.");
                        break;
            }

        } while (opcion != 0);
    }

    public void registrarProfesores() {
        int cantidad = Integer.parseInt(JOptionPane.showInputDialog("¿Cuántos profesores desea registrar?"));
        for (int i = 0; i < cantidad; i++) {
            String nombre = JOptionPane.showInputDialog("Nombre del profesor " + (i + 1) + ":");
            if (!listaProfesores.contains(nombre)) {
                listaProfesores.add(nombre);
            } else {
                JOptionPane.showMessageDialog(null, "El nombre ya existe. No se puede registrar duplicado.");
                i--;
            }
        }
    }

    public void registrarEstudiantes() {
        if (listaProfesores.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Primero debe registrar profesores.");
            return;
        }

        listaGeneralEstudiantes.clear();

        for (String profesor : listaProfesores) {
            int cantidad = Integer.parseInt(JOptionPane.showInputDialog("¿Cuántos estudiantes tiene el profesor " + profesor + "?"));
            ArrayList<String> listEstudiantes = new ArrayList<>();

            for (int j = 0; j < cantidad; j++) {
                String estudiante = JOptionPane.showInputDialog("Nombre del estudiante " + (j + 1) + " para el profesor " + profesor + ":");
                if (!listEstudiantes.contains(estudiante)) {
                    listEstudiantes.add(estudiante);
                } else {
                    JOptionPane.showMessageDialog(null, "El estudiante ya existe en esta lista.");
                    j--;
                }
            }
            listaGeneralEstudiantes.add(listEstudiantes);
        }
    }

    public void consultarListaProfesoresYEstudiantes() {
        if (listaProfesores.isEmpty() || listaGeneralEstudiantes.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe registrar profesores y estudiantes primero.");
            return;
        }

        String resultado = "";
        for (int i = 0; i < listaProfesores.size(); i++) {
            resultado += listaProfesores.get(i) + ": ";
            ArrayList<String> estudiantes = listaGeneralEstudiantes.get(i);
            for (int j = 0; j < estudiantes.size(); j++) {
                resultado += estudiantes.get(j);
                if (j < estudiantes.size() - 1) {
                    resultado += ", ";
                }
            }
            resultado += "\n";
        }
        JOptionPane.showMessageDialog(null, resultado);
    }

    public void consultaProfesorPorNombre() {
        if (listaProfesores.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Primero debe registrar profesores.");
            return;
        }

        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del profesor a consultar:");
        if (listaProfesores.contains(nombre)) {
            int pos = listaProfesores.indexOf(nombre);
            String mensaje = "Estudiantes de " + nombre + ": ";
            ArrayList<String> estudiantes = listaGeneralEstudiantes.get(pos);
            for (int i = 0; i < estudiantes.size(); i++) {
                mensaje += estudiantes.get(i);
                if (i < estudiantes.size() - 1) {
                    mensaje += ", ";
                }
            }
            JOptionPane.showMessageDialog(null, mensaje);
        } else {
            JOptionPane.showMessageDialog(null, "Profesor no encontrado.");
        }
    }

    public void consultaCantidadEstudiantesPorProfesor() {
        if (listaProfesores.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Primero debe registrar profesores.");
            return;
        }

        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del profesor:");
        if (listaProfesores.contains(nombre)) {
            int pos = listaProfesores.indexOf(nombre);
            JOptionPane.showMessageDialog(null, "Cantidad de estudiantes: " + listaGeneralEstudiantes.get(pos).size());
        } else {
            JOptionPane.showMessageDialog(null, "Profesor no encontrado.");
        }
    }

    public void consultarEstudiante() {
        if (listaGeneralEstudiantes.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe registrar estudiantes primero.");
            return;
        }

        String estudiante = JOptionPane.showInputDialog("Ingrese el nombre del estudiante:");
        boolean encontrado = false;

        for (int i = 0; i < listaGeneralEstudiantes.size(); i++) {
            if (listaGeneralEstudiantes.get(i).contains(estudiante)) {
                JOptionPane.showMessageDialog(null, "El estudiante pertenece al grupo del profesor " + listaProfesores.get(i));
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            JOptionPane.showMessageDialog(null, "Estudiante no registrado en ningún grupo.");
        }
    }
}
