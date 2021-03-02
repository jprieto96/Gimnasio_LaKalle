package integracion.aula.reactivarAula;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import integracion.aula.DAOAula;
import integracion.factoria.FactoriaIntegracion;
import negocio.aula.TAula;

public class TestIntegracionReactivarAula {
	
	private static DAOAula daoAula;
	private static int idAula;
	
	@BeforeClass
	public static void before() {
		daoAula = FactoriaIntegracion.getInstancia().generaDAOAula();
		idAula = daoAula.create(new TAula(300));
		daoAula.delete(idAula);
	}

	@Test
	public void testReactivarAulaCorrecto() {
		boolean ok = daoAula.reactivate(idAula);
		TAula aula = daoAula.readById(idAula);
		assertTrue(ok);
		assertTrue(aula.getEstado());
	}
	
}
