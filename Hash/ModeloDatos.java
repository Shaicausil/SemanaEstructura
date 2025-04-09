package Hash;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import javax.swing.JOptionPane;

public class ModeloDatos {

    private HashMap<String, ArrayList<String>> mapEstudiantes;

    public ModeloDatos() {
        mapEstudiantes = new HashMap<>();
    }

    public void guardarDatos(ArrayList<String> listaEstudiantes) {
        mapEstudiantes.put(listaEstudiantes.get(0), listaEstudiantes);
        JOptionPane.showMessageDialog(null, "Registro exitoso.");
    }

    public void consultarEstudiante(String documento) {
        if (mapEstudiantes.containsKey(documento)) {
            JOptionPane.showMessageDialog(null, "Datos: " + mapEstudiantes.get(documento));
        } else {
            JOptionPane.showMessageDialog(null, "Documento no existe.");
        }
    }

    public void imprimirMapa() {
        Iterator<String> it = mapEstudiantes.keySet().iterator();
        String salida = "";

        while (it.hasNext()) {
            String doc = it.next();
            salida += doc + " - " + mapEstudiantes.get(doc) + "\n";
        }

        JOptionPane.showMessageDialog(null, salida);
    }


    public HashMap<String, ArrayList<String>> getMapaEstudiantes() {
        return mapEstudiantes;
    }

    public int getCantidadEstudiantes() {
        return mapEstudiantes.size();
    }
}
