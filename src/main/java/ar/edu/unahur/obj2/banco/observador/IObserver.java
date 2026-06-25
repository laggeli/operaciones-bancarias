package ar.edu.unahur.obj2.banco.observador;

import ar.edu.unahur.obj2.banco.operaciones.Operacion;

public interface IObserver {
    void recibirNotificacion(Operacion operacion);
}