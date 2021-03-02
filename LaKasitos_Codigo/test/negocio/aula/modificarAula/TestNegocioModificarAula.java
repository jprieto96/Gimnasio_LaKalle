package negocio.aula.modificarAula;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import negocio.aula.SA_Aula;
import negocio.aula.TAula;
import negocio.factoria.FactoriaNegocio;

public class TestNegocioModificarAula {
	
	private static SA_Aula sa_Aula;
	private int idNuevaAula;
	
	@Before
	public void before() {
		sa_Aula = FactoriaNegocio.getInstancia().generaSAAula();
		idNuevaAula = sa_Aula.create(new TAula(100));
	}
	
	@Test
	public void testModificarAulaCorrecto() {
		TAula aulaAModificar = new TAula(idNuevaAula, 535);
		boolean ok = sa_Aula.update(aulaAModificar);
		TAula aulaYaModificada = sa_Aula.readById(idNuevaAula);
		assertTrue(ok);
		assertEquals(aulaAModificar.getAforo(), aulaYaModificada.getAforo());
	}
	
	@Test
	public void testModificarAulaIncorrecto() {
		boolean ok = sa_Aula.update(null);
		assertFalse(ok);
	}

}
