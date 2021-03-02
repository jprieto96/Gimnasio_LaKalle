package integracion.aula.altaAula;

import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

import integracion.aula.DAOAula;
import integracion.factoria.FactoriaIntegracion;
import negocio.aula.TAula;

public class TestIntegracionAltaAula {
	
	private static DAOAula daoAula;
	private static int idNuevaAula;
	
	@BeforeClass
	public static void before() {
		daoAula = FactoriaIntegracion.getInstancia().generaDAOAula();
	}

	@Test
	public void testAltaAulaCorrecto() {
		idNuevaAula = daoAula.create(new TAula(100));
		assertNotEquals(idNuevaAula, -1);
	}
	
	@Test(expected = NullPointerException.class)
	public void testAltaAulaCorrectoEsperandoExcepcion() throws NullPointerException {
		assertEquals(null, daoAula.create(null));
	}

}
