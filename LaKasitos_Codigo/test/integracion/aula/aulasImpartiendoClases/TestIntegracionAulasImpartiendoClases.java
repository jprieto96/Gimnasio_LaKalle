package integracion.aula.aulasImpartiendoClases;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import integracion.aula.DAOAula;
import integracion.factoria.FactoriaIntegracion;
import negocio.aula.TAula;

public class TestIntegracionAulasImpartiendoClases {
	
	private static DAOAula daoAula;
	
	@BeforeClass
	public static void before() {
		daoAula = FactoriaIntegracion.getInstancia().generaDAOAula();
	}

	@Test
	public void testAulasImpartiendoClasesCorrecto() {
		List<TAula> aulas = daoAula.aulasImpartiendoClases();
		for (TAula tAula : aulas) {
			assertNotNull(tAula);
		}
	}
	
}
