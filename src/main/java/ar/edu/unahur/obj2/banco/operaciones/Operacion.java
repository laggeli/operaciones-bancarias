package ar.edu.unahur.obj2.banco.operaciones;

import ar.edu.unahur.obj2.banco.observado.Cuenta;

public abstract class Operacion {
    protected Cuenta cuenta;
    protected Double monto;

    public Operacion(Cuenta cuenta, Double monto) {
        this.cuenta = cuenta;
        this.monto = monto;
    }

    public abstract void ejecutar();
    
    public abstract void deshacer();

    public abstract String descripcion();

    public Cuenta getCuenta() { return cuenta; }

    public Double getMonto() { return monto; }
}