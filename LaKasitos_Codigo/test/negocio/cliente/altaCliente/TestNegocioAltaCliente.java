package negocio.cliente.altaCliente;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import negocio.cliente.SA_Cliente;
import negocio.cliente.TCliente;
import negocio.cliente.TDatosCompletosCliente;
import negocio.factoria.FactoriaNegocio;

public class TestNegocioAltaCliente {
	
	private static SA_Cliente saCliente;
	private Integer ejDNI = 1, ejCB = 1, ejTFNO = 1, ejDom = 1, ejMail = 1; // campos UNIQUE en BBDD
	private int idNuevoCliente;
	private List<TCliente> clientes;
	
	@Before
	public void before() {
		saCliente = FactoriaNegocio.getInstancia().generaSACliente();
		seleccionarCamposUnicos();
	}

	@Test
	public void testAltaClienteCorrecto() {
		idNuevoCliente = saCliente.create(new TCliente(-1, "Paco", ejDNI.toString(), ejCB.toString(), ejTFNO.toString(), ejDom.toString(), ejMail.toString()));
		assertNotEquals(idNuevoCliente, -1);
	}
	
	@Test
	public void testAltaClienteMedianteReactivacionCorrecto() {
		idNuevoCliente = saCliente.create(new TCliente(-1, "Paco", ejDNI.toString(), ejCB.toString(), ejTFNO.toString(), ejDom.toString(), ejMail.toString()));
		saCliente.delete(idNuevoCliente);
		TDatosCompletosCliente cliente = saCliente.readById(idNuevoCliente);
		saCliente.create(cliente.getCliente());
		cliente = saCliente.readById(idNuevoCliente);
		assertTrue(cliente.getCliente().getEstado());
	}
	
	@Test
	public void testAltaClienteIncorrecto() {
		idNuevoCliente = saCliente.create(null);
		assertEquals(idNuevoCliente, -1);
	}
	
	private void seleccionarCamposUnicos() {
		clientes = saCliente.readAll();
		for (int i = 0; i < clientes.size(); i++) {
			for (int j = 0; j < clientes.size(); j++) {
				if(i != j) {
					if(clientes.get(j).getCuentaBancaria().equals(ejCB.toString()))
						++ejCB;
					if(clientes.get(j).getDni().equals(ejDNI.toString()))
						++ejDNI;
					if(clientes.get(j).getTelefono().equals(ejTFNO.toString()))
						++ejTFNO;
					if(clientes.get(j).getDomicilio().equals(ejDom.toString()))
						++ejDom;
					if(clientes.get(j).getCorreo().equals(ejMail.toString()))
						++ejMail;
				}
			}
		}
	}

}
