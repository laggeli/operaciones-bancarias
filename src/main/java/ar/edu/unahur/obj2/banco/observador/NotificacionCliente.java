package ar.edu.unahur.obj2.banco.observador;

import ar.edu.unahur.obj2.banco.operaciones.Operacion;

public class NotificacionCliente implements IObserver {
    @Override
    public void recibirNotificacion(Operacion operacion) { System.out.println(operacion.descripcion()); }
}