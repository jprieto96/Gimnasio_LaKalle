package integracion.aula.mostrarDatosAula;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import integracion.aula.DAOAula;
import integracion.factoria.FactoriaIntegracion;
import negocio.aula.TAula;

public class TestIntegracionMostrarDatosAula {
	
	private static DAOAula daoAula;
	private static TAula aula;
	private static int idAula;
	
	@BeforeClass
	public static void before() {
		daoAula = FactoriaIntegracion.getInstancia().generaDAOAula();
		aula = new TAula(490);
		idAula = daoAula.create(aula);
	}

	@Test
	public void testMostrarDatosAulaCorrecto() {
		TAula a = daoAula.readById(idAula);
		assertEquals(idAula, a.getId());
	}
	
	@Test
	public void testMostrarDatosAulaIncorrecto() {
		TAula a = daoAula.readById(-1);
		assertNull(a);
	}
	
}
