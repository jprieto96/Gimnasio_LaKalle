package negocio.cliente.listarClientesNoDisponibles;

import static org.junit.Assert.*;

import java.util.List;
import org.junit.BeforeClass;
import org.junit.Test;
import negocio.cliente.SA_Cliente;
import negocio.cliente.TCliente;
import negocio.factoria.FactoriaNegocio;

public class TestNegocioListarClientesNoDisponibles {
	
	private static SA_Cliente saCliente;
	
	@BeforeClass
	public static void before() {
		saCliente = FactoriaNegocio.getInstancia().generaSACliente();
	}

	@Test
	public void testListarAulasNoDisponiblesCorrecto() {
		List<TCliente> clientes = saCliente.readAllUnavailable();
		for (TCliente c : clientes) {
			assertNotNull(c);
			assertFalse(c.getEstado());
		}
	}
	
}
