package integracion.cliente.modificarCliente;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import integracion.cliente.DAOCliente;
import integracion.factoria.FactoriaIntegracion;
import negocio.cliente.TCliente;

public class TestIntegracionModificarCliente {
	
	private static DAOCliente daoCliente;
	private Integer ejDNI = 1, ejCB = 1, ejTFNO = 1, ejMail = 1; // campos UNIQUE en BBDD
	private List<TCliente> clientes;
	private TCliente cliente;
	
	@Before
	public void before() {
		daoCliente = FactoriaIntegracion.getInstancia().generaDAOCliente();
		seleccionarCamposUnicos();
	}

	@Test
	public void testModificarClienteCorrecto() {
		cliente = new TCliente("Luis", ejDNI.toString(), ejCB.toString(), ejTFNO.toString(), "C/Palomas 23", ejMail.toString());
		int idNuevoCliente = daoCliente.create(cliente);
		TCliente c = daoCliente.readById(idNuevoCliente);
		seleccionarCamposUnicos();
		c.setNombre("Luis");
		c.setDni(ejDNI.toString());
		c.setCuentaBancaria(ejCB.toString());
		c.setTelefono(ejTFNO.toString());
		c.setDomicilio("C/Palomas 23");
		c.setCorreo(ejMail.toString());
		boolean ok = daoCliente.update(c);
		assertTrue(ok);
		assertEquals(c.getNombre(), "Luis");
		assertEquals(c.getDni(), ejDNI.toString());
		assertEquals(c.getCuentaBancaria(), ejCB.toString());
		assertEquals(c.getTelefono(), ejTFNO.toString());
		assertEquals(c.getDomicilio(), "C/Palomas 23");
		assertEquals(c.getCorreo(), ejMail.toString());
	}
	
	@Test(expected = NullPointerException.class)
	public void testModificarClienteCorrectoEsperandoExcepcion() throws NullPointerException {
		assertEquals(null, daoCliente.create(null));
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
