package ar.edu.unahur.obj2.banco.observador;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unahur.obj2.banco.operaciones.Operacion;

public class Auditoria implements IObserver {
    private List<Operacion> movimientos = new ArrayList<>();

    @Override
    public void recibirNotificacion(Operacion operacion) { movimientos.add(operacion); }

    public List<Operacion> getMovimientos() { return movimientos; }
}