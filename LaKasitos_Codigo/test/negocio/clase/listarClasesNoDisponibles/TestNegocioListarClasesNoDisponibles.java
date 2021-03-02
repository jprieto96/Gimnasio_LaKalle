package negocio.clase.listarClasesNoDisponibles;

import static org.junit.Assert.*;

import java.util.List;
import org.junit.BeforeClass;
import org.junit.Test;
import negocio.clase.SA_Clase;
import negocio.clase.TClase;
import negocio.factoria.FactoriaNegocio;

public class TestNegocioListarClasesNoDisponibles {
	
	private static SA_Clase saClase;
	
	@BeforeClass
	public static void before() {
		saClase = FactoriaNegocio.getInstancia().generaSAClase();
	}

	@Test
	public void testListarAulasNoDisponiblesCorrecto() {
		List<TClase> clases = saClase.readAllUnavalaible();
		for (TClase c : clases) {
			assertNotNull(c);
			assertFalse(c.getEstado());
		}
	}
	
}
