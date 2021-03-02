package negocio.cliente.modificarCliente;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import negocio.cliente.SA_Cliente;
import negocio.cliente.TCliente;
import negocio.cliente.TDatosCompletosCliente;
import negocio.factoria.FactoriaNegocio;

public class TestNegocioModificarCliente {
	
	private static SA_Cliente saCliente;
	private Integer ejDNI = 1, ejCB = 1, ejTFNO = 1, ejDom = 1, ejMail = 1; // campos UNIQUE en BBDD
	private int idNuevoCliente;
	private TCliente cliente;
	private List<TCliente> clientes;
	
	@Before
	public void before() {
		saCliente = FactoriaNegocio.getInstancia().generaSACliente();
		seleccionarCamposUnicos();
		cliente = new TCliente(-1, "Paco", ejDNI.toString(), ejCB.toString(), ejTFNO.toString(), ejDom.toString(), ejMail.toString());
		idNuevoCliente = saCliente.create(cliente);
	}

	@Test
	public void testModificarClienteCorrecto() {
		seleccionarCamposUnicos();
		TCliente c = new TCliente(idNuevoCliente, "PacoModificado", ejDNI.toString(), ejCB.toString(), ejTFNO.toString(), ejDom.toString(), ejMail.toString());
		boolean ok = saCliente.update(c);
		assertTrue(ok);
		TDatosCompletosCliente d = saCliente.readById(idNuevoCliente);
		c.setEstado(d.getCliente().getEstado());
		assertEquals(c.toString(), d.getCliente().toString());
	}
	
	
	@Test
	public void testModificarClienteIncorrecto() {
		boolean ok = saCliente.update(null);
		assertFalse(ok);
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
