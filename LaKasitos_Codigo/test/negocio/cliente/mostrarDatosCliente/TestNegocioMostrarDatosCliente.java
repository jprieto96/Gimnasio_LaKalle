package negocio.cliente.mostrarDatosCliente;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import negocio.cliente.SA_Cliente;
import negocio.cliente.TCliente;
import negocio.cliente.TDatosCompletosCliente;
import negocio.factoria.FactoriaNegocio;

public class TestNegocioMostrarDatosCliente {
	
	private static SA_Cliente saCliente;
	private TCliente cliente;
	private Integer ejDNI = 1, ejCB = 1, ejTFNO = 1, ejDom = 1, ejMail = 1; // campos UNIQUE en BBDD
	private int idCliente;
	private List<TCliente> clientes;
	
	@Before
	public void before() {
		saCliente = FactoriaNegocio.getInstancia().generaSACliente();
		seleccionarCamposUnicos();
		cliente = new TCliente(-1, "Paco", ejDNI.toString(), ejCB.toString(), ejTFNO.toString(), ejDom.toString(), ejMail.toString());
		idCliente = saCliente.create(cliente);
		cliente.setId(idCliente);
	}

	@Test
	public void testMostrarDatosClienteCorrecto() {
		TDatosCompletosCliente d = saCliente.readById(idCliente);
		assertEquals(d.getCliente().toString(), cliente.toString());
	}
	
	@Test
	public void testMostrarDatosClienteIncorrecto() {
		TDatosCompletosCliente c = saCliente.readById(-1);
		assertNull(c);
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
