package ar.edu.unahur.obj2.banco;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unahur.obj2.banco.administrador.Administrador;
import ar.edu.unahur.obj2.banco.observado.Cuenta;
import ar.edu.unahur.obj2.banco.observador.Auditoria;
import ar.edu.unahur.obj2.banco.operaciones.Deposito;
import ar.edu.unahur.obj2.banco.operaciones.Retiro;

public class BancoTest {
    private Cuenta cuenta;
    private Administrador admin;
    private Auditoria auditoria;

    @BeforeEach
    void setUp() {
        cuenta = new Cuenta(1234, 10000.0);
        admin = new Administrador();
        auditoria = new Auditoria();

        cuenta.agregarObservador(auditoria);
    }

    @Test
    void dadaUnaCuenta_cuandoSeLeDepositaDinero_entoncesAumentaElSaldo() {
        admin.ejecutar(new Deposito(cuenta, 3000.0));

        assertEquals(13000.0, cuenta.getSaldo());
    }

    @Test
    void dadaUnaCuenta_cuandoSeLeRetiraDinero_entoncesDisminuyeElSaldo() {
        admin.ejecutar(new Retiro(cuenta, 2000.0));

        assertEquals(8000.0, cuenta.getSaldo());
    }

    @Test
    void dadoUnaCuenta_cuandoSeLeEjecutaUnLoteDeMovimientos_entoncesSeHacenTodasLasOperaciones() {
        admin.registrar(new Deposito(cuenta, 3000.0));
        admin.registrar(new Retiro(cuenta, 1000.0));
        admin.registrar(new Deposito(cuenta, 500.0));
        admin.ejecutarLote();

        assertEquals(12500.0, cuenta.getSaldo());
    }

    @Test
    void dadoUnaCuenta_cuandoDeshaceUnDeposito_entoncesSeRevierteElSaldo() {
        admin.ejecutar(new Deposito(cuenta, 3000.0));
        admin.deshacer();

        assertEquals(10000.0, cuenta.getSaldo());
    }

    @Test
    void dadoUnaCuenta_cuandoDeshaceUnRetiro_entoncesSeRevierteElSaldo() {
        admin.ejecutar(new Retiro(cuenta, 2000.0));
        admin.deshacer();

        assertEquals(10000.0, cuenta.getSaldo());
    }

    @Test
    void dadoUnaCuenta_cuandoQuiereRetirarConSaldoSuficiente_entoncesSeLePermiteRetirar() {
        admin.ejecutar(new Retiro(cuenta, 70000.0));

        assertEquals(10000.0, cuenta.getSaldo());
    }

    @Test
    void dadoUnaCuenta_cuandoQuiereRetirarConSaldoLimite_entoncesQuedaAlDescubierto() {
        admin.ejecutar(new Retiro(cuenta, 30000.0));

        assertEquals(-20000.0, cuenta.getSaldo());
    }

    @Test
    void dadoUnaCuenta_cuandoSeHaceUnDeposito_entoncesEnLaAuditoriaSeRegistraElMovimiento() {
        admin.ejecutar(new Deposito(cuenta, 3000.0));

        assertEquals(1, auditoria.getMovimientos().size());
    }

    @Test
    void dadoUnDeposito_cuandoSeRealiza_entoncesSuDescripcionEsCorrecta() {
        Deposito deposito = new Deposito(cuenta, 3000.0);

        assertEquals("Depositar $3000.0 en la cuenta 1234", deposito.descripcion());
    }

    @Test
    void dadoUnRetiro_cuandoSeRealiza_entoncesSuDescripcionEsCorrecta() {
        Retiro retiro = new Retiro(cuenta, 5000.0);

        assertEquals("Retirar $5000.0 de la cuenta 1234", retiro.descripcion());
    }

    @Test
    void dadoUnaCuenta_cuandoEliminaElObservador_entoncesElObservadorNoRecibeMasEventos() {
        cuenta.eliminarObservador(auditoria);
        admin.ejecutar(new Deposito(cuenta, 3000.0));

        assertEquals(0, auditoria.getMovimientos().size());
    }
}