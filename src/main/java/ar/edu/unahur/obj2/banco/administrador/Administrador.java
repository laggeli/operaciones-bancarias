package ar.edu.unahur.obj2.banco.administrador;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unahur.obj2.banco.operaciones.Operacion;

public class Administrador {
    private List<Operacion> pendientes = new ArrayList<>();
    private List<Operacion> historial = new ArrayList<>();

    public void ejecutar(Operacion operacion) { 
        operacion.ejecutar(); 
        historial.add(operacion);
    }

    public void deshacer() {
        if (!historial.isEmpty()) {
            Operacion ultima = historial.get(historial.size() - 1);
            ultima.deshacer();
            historial.remove(ultima);
        }
}

    public void registrar(Operacion operacion) { pendientes.add(operacion); }

    public void ejecutarLote() { 
        pendientes.forEach(o -> o.ejecutar()); 
        pendientes.clear();
    }
}