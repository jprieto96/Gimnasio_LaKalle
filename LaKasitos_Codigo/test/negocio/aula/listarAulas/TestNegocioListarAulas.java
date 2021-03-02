package negocio.aula.listarAulas;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import negocio.aula.SA_Aula;
import negocio.aula.TAula;
import negocio.factoria.FactoriaNegocio;

public class TestNegocioListarAulas {
	
	private static SA_Aula saAula;
	
	@BeforeClass
	public static void before() {
		saAula = FactoriaNegocio.getInstancia().generaSAAula();
	}

	@Test
	public void testListarAulasCorrecto() {
		List<TAula> aulas = saAula.readAll();
		for (TAula tAula : aulas) {
			assertNotNull(tAula);
		}
	}
	
}
