package integracion.aula.modificarAula;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import integracion.aula.DAOAula;
import integracion.factoria.FactoriaIntegracion;
import negocio.aula.TAula;

public class TestIntegracionModificarAula {
	
	private static DAOAula daoAula;
	private static TAula aula;
	
	@BeforeClass
	public static void before() {
		daoAula = FactoriaIntegracion.getInstancia().generaDAOAula();
		aula = new TAula(300);
		daoAula.create(aula);
	}

	@Test
	public void testModificarAulaCorrecto() {
		aula.setAforo(123);
		boolean ok = daoAula.update(aula);
		assertTrue(ok);
	}
	
	@Test(expected = NullPointerException.class)
	public void testModificarAulaCorrectoEsperandoExcepcion() throws NullPointerException {
		assertEquals(null, daoAula.update(null));
	}
	
}
