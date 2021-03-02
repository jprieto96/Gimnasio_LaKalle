package integracion.aula.bajaAula;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import integracion.aula.DAOAula;
import integracion.factoria.FactoriaIntegracion;
import negocio.aula.TAula;

public class TestIntegracionBajaAula {
	
	private static DAOAula daoAula;
	private static int idAula;
	
	@BeforeClass
	public static void before() {
		daoAula = FactoriaIntegracion.getInstancia().generaDAOAula();
		idAula = daoAula.create(new TAula(360));
	}

	@Test
	public void testBajaAulaCorrecto() {
		boolean ok = daoAula.delete(idAula);
		TAula aula = daoAula.readById(idAula);
		assertTrue(ok);
		assertFalse(aula.getEstado());
	}
	
}
