package ar.edu.unahur.obj2.banco.observado;

import ar.edu.unahur.obj2.banco.operaciones.Operacion;

public interface IObservable {
    void notificar(Operacion operacion);
}