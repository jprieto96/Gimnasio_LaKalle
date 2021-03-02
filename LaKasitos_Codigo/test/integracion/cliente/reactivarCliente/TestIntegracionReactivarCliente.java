package integracion.cliente.reactivarCliente;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import integracion.cliente.DAOCliente;
import integracion.factoria.FactoriaIntegracion;
import negocio.cliente.TCliente;

public class TestIntegracionReactivarCliente {
	
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
		cliente = daoCliente.readById(idCliente);
		daoCliente.delete(cliente.getId());
	}

	@Test
	public void testReactivarClienteCorrecto() {
		boolean ok = daoCliente.reactivate(cliente.getId());
		TCliente c = daoCliente.readById(cliente.getId());
		assertTrue(ok);
		assertTrue(c.getEstado());
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
