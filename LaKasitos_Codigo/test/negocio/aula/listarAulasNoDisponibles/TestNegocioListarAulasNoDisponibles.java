package negocio.aula.listarAulasNoDisponibles;

import static org.junit.Assert.*;

import java.util.List;
import org.junit.BeforeClass;
import org.junit.Test;
import negocio.aula.SA_Aula;
import negocio.aula.TAula;
import negocio.factoria.FactoriaNegocio;

public class TestNegocioListarAulasNoDisponibles {
	
	private static SA_Aula saAula;
	
	@BeforeClass
	public static void before() {
		saAula = FactoriaNegocio.getInstancia().generaSAAula();
	}

	@Test
	public void testListarAulasNoDisponiblesCorrecto() {
		List<TAula> aulas = saAula.readAllUnavailable();
		for (TAula tAula : aulas) {
			assertNotNull(tAula);
			assertFalse(tAula.getEstado());
		}
	}
	
}
