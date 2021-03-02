package negocio.aula.mostrarDatosAula;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import negocio.aula.SA_Aula;
import negocio.aula.TAula;
import negocio.factoria.FactoriaNegocio;

public class TestNegocioMostrarDatosAula {
	
	private static SA_Aula saAula;
	private static TAula aula;
	private static int idAula;
	
	@BeforeClass
	public static void before() {
		saAula = FactoriaNegocio.getInstancia().generaSAAula();
		aula = new TAula(490);
		idAula = saAula.create(aula);
		aula.setId(idAula);
	}

	@Test
	public void testMostrarDatosAulaCorrecto() {
		TAula a = saAula.readById(idAula);
		assertEquals(aula.toString(), a.toString());
	}
	
	@Test
	public void testMostrarDatosAulaIncorrecto() {
		TAula a = saAula.readById(-1);
		assertNull(a);
	}
	
}
