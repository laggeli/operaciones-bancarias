package ar.edu.unahur.obj2.banco.operaciones;

import ar.edu.unahur.obj2.banco.observado.Cuenta;

public class Retiro extends Operacion {
    public Retiro(Cuenta cuenta, Double monto) { super(cuenta, monto); }

    @Override
    public void ejecutar() { cuenta.retirar(monto); }

    @Override
    public void deshacer() { cuenta.depositar(monto); }

    @Override
    public String descripcion() { return "Retirar $" + monto + " de la cuenta " + cuenta.getNumero(); }
}