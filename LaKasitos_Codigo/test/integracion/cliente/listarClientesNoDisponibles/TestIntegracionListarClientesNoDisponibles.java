package integracion.cliente.listarClientesNoDisponibles;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import integracion.cliente.DAOCliente;
import integracion.factoria.FactoriaIntegracion;
import negocio.cliente.TCliente;

public class TestIntegracionListarClientesNoDisponibles {
	
	private static DAOCliente daoCliente;
	
	@BeforeClass
	public static void before() {
		daoCliente = FactoriaIntegracion.getInstancia().generaDAOCliente();
	}

	@Test
	public void testListarClientesNoDisponiblesCorrecto() {
		List<TCliente> clientes = daoCliente.readAllUnavailable();
		for (TCliente tCliente : clientes) {
			assertNotNull(tCliente);
			assertFalse(tCliente.getEstado());
		}
	}
}
