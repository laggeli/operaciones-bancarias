package ar.edu.unahur.obj2.banco.observador;

import ar.edu.unahur.obj2.banco.operaciones.Operacion;

public class NotificacionSaldo implements IObserver {
    @Override
    public void recibirNotificacion(Operacion operacion) { 
        if (operacion.getCuenta().getSaldo() < 0) { System.out.println("ALERTA: la cuenta quedó en saldo negativo."); }
    }
}