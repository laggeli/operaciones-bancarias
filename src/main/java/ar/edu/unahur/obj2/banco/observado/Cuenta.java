package ar.edu.unahur.obj2.banco.observado;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unahur.obj2.banco.observador.IObserver;
import ar.edu.unahur.obj2.banco.operaciones.Deposito;
import ar.edu.unahur.obj2.banco.operaciones.Operacion;
import ar.edu.unahur.obj2.banco.operaciones.Retiro;

public class Cuenta implements IObservable {
    private final Integer numero;
    private Double saldo;
    private List<IObserver> observadores = new ArrayList<>();

    public Cuenta(Integer numero, Double saldo) {
        this.numero = numero;
        this.saldo = saldo;
    }

    public Integer getNumero() { return numero; }
    
    public Double getSaldo() { return saldo; }

    public void depositar(Double monto) { 
        saldo += monto; 
        notificar(new Deposito(this, monto));
    }

    public void retirar(Double monto) { 
        if (saldo - monto > -50000) {
            saldo -= monto; 
            Retiro retiro = new Retiro(this, monto);
            notificar(retiro);
        }
    }

    public void agregarObservador(IObserver observador) { observadores.add(observador); }

    public void eliminarObservador(IObserver observador) { observadores.remove(observador); }

    @Override
    public void notificar(Operacion operacion) { observadores.forEach(o -> o.recibirNotificacion(operacion));} 
}