package negocio.aula.altaAula;

import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;
import negocio.aula.SA_Aula;
import negocio.aula.TAula;
import negocio.factoria.FactoriaNegocio;

public class TestNegocioAltaAula {
	
	private static SA_Aula sa_Aula;
	private static int idNuevaAula;
	
	@BeforeClass
	public static void before() {
		sa_Aula = FactoriaNegocio.getInstancia().generaSAAula();
	}

	@Test
	public void testAltaAulaCorrecto() {
		idNuevaAula = sa_Aula.create(new TAula(100));
		assertNotEquals(idNuevaAula, -1);
	}
	
	@Test
	public void testAltaAulaMedianteReactivacionCorrecto() {
		idNuevaAula = sa_Aula.create(new TAula(100));
		sa_Aula.delete(idNuevaAula);
		TAula aula = sa_Aula.readById(idNuevaAula);
		sa_Aula.create(aula);
		aula = sa_Aula.readById(idNuevaAula);
		assertTrue(aula.getEstado());
	}
	
	@Test
	public void testAltaAulaIncorrecto() {
		idNuevaAula = sa_Aula.create(null);
		assertEquals(idNuevaAula, -1);
	}

}
