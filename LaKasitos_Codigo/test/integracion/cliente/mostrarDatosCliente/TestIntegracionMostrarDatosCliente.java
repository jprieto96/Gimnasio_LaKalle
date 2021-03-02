package integracion.cliente.mostrarDatosCliente;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import integracion.cliente.DAOCliente;
import integracion.factoria.FactoriaIntegracion;
import negocio.cliente.TCliente;

public class TestIntegracionMostrarDatosCliente {
	
	private static DAOCliente daoCliente;
	private static TCliente cliente;
	private Integer ejDNI = 1, ejCB = 1, ejTFNO = 1, ejMail = 1; // campos UNIQUE en BBDD
	private List<TCliente> clientes;
	private static int idCliente;
	
	@Before
	public void before() {
		daoCliente = FactoriaIntegracion.getInstancia().generaDAOCliente();
		seleccionarCamposUnicos();
		cliente = new TCliente("Luis", ejDNI.toString(), ejCB.toString(), ejTFNO.toString(), "C/Palomas 23", ejMail.toString());
		idCliente = daoCliente.create(cliente);
		cliente.setId(idCliente);
	}

	@Test
	public void testMostrarDatosClienteCorrecto() {
		TCliente c = daoCliente.readById(idCliente);
		assertEquals(cliente.toString(), c.toString());
	}
	
	@Test
	public void testMostrarDatosClienteIncorrecto() {
		TCliente p = daoCliente.readById(-1);
		assertNull(p);
	}
	
	private void seleccionarCamposUnicos() {
		clientes = daoCliente.readAll();
		for (int i = 0; i < clientes.size(); i++) {
			for (int j = 0; j < clientes.size(); j++) {
				if(i != j) {
					if(clientes.get(j).getCuentaBancaria().equals(ejCB.toString()))
						++ejCB;
					if(clientes.get(j).getDni().equals(ejDNI.toString()))
						++ejDNI;
					if(clientes.get(j).getTelefono().equals(ejTFNO.toString()))
						++ejTFNO;
					if(clientes.get(j).getCorreo().equals(ejMail.toString()))
						++ejMail;
				}
			}
		}
	}
}
