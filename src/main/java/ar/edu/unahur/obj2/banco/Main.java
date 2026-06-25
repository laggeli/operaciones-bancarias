package ar.edu.unahur.obj2.banco;

import ar.edu.unahur.obj2.banco.administrador.Administrador;
import ar.edu.unahur.obj2.banco.observado.Cuenta;
import ar.edu.unahur.obj2.banco.observador.Auditoria;
import ar.edu.unahur.obj2.banco.observador.NotificacionCliente;
import ar.edu.unahur.obj2.banco.observador.NotificacionSaldo;
import ar.edu.unahur.obj2.banco.operaciones.Deposito;
import ar.edu.unahur.obj2.banco.operaciones.Operacion;
import ar.edu.unahur.obj2.banco.operaciones.Retiro;

public class Main {
    public static void main(String[] args) {
        Cuenta cuenta = new Cuenta(1234, 10000.0);
        Administrador admin = new Administrador();

        System.out.println("-- primera cuenta --");
        System.out.println();

        System.out.println("Saldo inicial: $" + cuenta.getSaldo());
        System.out.println();

        Operacion deposito1 = new Deposito(cuenta, 70000.0);

        System.out.println("Depositar $70000");
        admin.ejecutar(deposito1);
        System.out.println("Saldo: $" + cuenta.getSaldo());
        System.out.println();

        Operacion retiro1 = new Retiro(cuenta, 5000.0);

        System.out.println("Retirar $5000");
        admin.ejecutar(retiro1);
        System.out.println("Saldo: $" + cuenta.getSaldo());
        System.out.println();

        Operacion retiro2 = new Retiro(cuenta, 16000.0);

        System.out.println("Retirar $16000");
        admin.ejecutar(retiro2);
        System.out.println("Saldo: $" + cuenta.getSaldo());
        System.out.println();

        Operacion deposito2 = new Deposito(cuenta, 9000.0);

        System.out.println("Depositar $9000");
        admin.ejecutar(deposito2);
        System.out.println("Saldo: $" + cuenta.getSaldo());
        System.out.println();

        Operacion retiro1Lote = new Retiro(cuenta, 5000.0);
        Operacion deposito1Lote = new Deposito(cuenta, 300000.0);
        Operacion retiro2Lote = new Retiro(cuenta, 50000.0);
        admin.registrar(retiro1Lote);
        admin.registrar(deposito1Lote);
        admin.registrar(retiro2Lote);

        System.out.println("Saldo inicial: $" + cuenta.getSaldo());
        System.out.println();
        admin.ejecutarLote();
        System.out.println("Saldo final: $" + cuenta.getSaldo());
        System.out.println();

        System.out.println("-- segunda cuenta --");
        System.out.println();

        Cuenta cuenta2 = new Cuenta(1234, 10000.0);
        cuenta2.agregarObservador(new Auditoria());
        cuenta2.agregarObservador(new NotificacionCliente());
        cuenta2.agregarObservador(new NotificacionSaldo());
        Administrador admin2 = new Administrador();

        System.out.println("Saldo inicial: $" + cuenta2.getSaldo());
        System.out.println();
        admin2.ejecutar(new Deposito(cuenta2, 3000.0));
        System.out.println();
        admin2.ejecutar(new Retiro(cuenta2, 5000.0));
        System.out.println();
        admin2.ejecutar(new Retiro(cuenta2, 8500.0));
        System.out.println();
        admin2.ejecutar(new Deposito(cuenta2, 200.0));
        System.out.println();
        admin2.ejecutar(new Retiro(cuenta2, 100000.0));
        System.out.println();
        System.out.println("Saldo final: " + cuenta2.getSaldo());
    }
}