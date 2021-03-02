package integracion.aula.listarAulas;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import integracion.aula.DAOAula;
import integracion.factoria.FactoriaIntegracion;
import negocio.aula.TAula;

public class TestIntegracionListarAulas {
	
	private static DAOAula daoAula;
	
	@BeforeClass
	public static void before() {
		daoAula = FactoriaIntegracion.getInstancia().generaDAOAula();
	}

	@Test
	public void testListarAulasCorrecto() {
		List<TAula> aulas = daoAula.readAll();
		for (TAula tAula : aulas) {
			assertNotNull(tAula);
		}
	}
	
}
