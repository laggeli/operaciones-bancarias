package ar.edu.unahur.obj2.banco.operaciones;

import ar.edu.unahur.obj2.banco.observado.Cuenta;

public class Deposito extends Operacion {
    public Deposito(Cuenta cuenta, Double monto) { super(cuenta, monto); }

    @Override
    public void ejecutar() { cuenta.depositar(monto); }

    @Override
    public void deshacer() { cuenta.retirar(monto); }

    @Override
    public String descripcion() { return "Depositar $" + monto + " en la cuenta " + cuenta.getNumero(); }
}