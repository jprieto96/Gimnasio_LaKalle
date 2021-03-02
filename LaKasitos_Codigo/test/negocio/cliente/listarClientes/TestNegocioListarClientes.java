package negocio.cliente.listarClientes;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import negocio.cliente.SA_Cliente;
import negocio.cliente.TCliente;
import negocio.factoria.FactoriaNegocio;

public class TestNegocioListarClientes {
	
	private static SA_Cliente saCliente;
	
	@BeforeClass
	public static void before() {
		saCliente = FactoriaNegocio.getInstancia().generaSACliente();
	}

	@Test
	public void testListarAulasCorrecto() {
		List<TCliente> clientes = saCliente.readAll();
		for (TCliente c : clientes) {
			assertNotNull(c);
		}
	}
	
}
