package integracion.cliente.listarClientes;

import static org.junit.Assert.*;


import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import integracion.cliente.DAOCliente;
import integracion.factoria.FactoriaIntegracion;
import negocio.cliente.TCliente;

public class TestIntegracionListarClientes {
	
	private static DAOCliente daoCliente;
	
	@BeforeClass
	public static void before() {
		daoCliente = FactoriaIntegracion.getInstancia().generaDAOCliente();
	}

	@Test
	public void testListarClientesCorrecto() {
		List<TCliente> clientes = daoCliente.readAll();
		for (TCliente tCliente : clientes) {
			assertNotNull(tCliente);
		}
	}
}
