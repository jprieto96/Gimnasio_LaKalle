package integracion.aula.listarAulasNoDisponibles;

import static org.junit.Assert.*;

import java.util.List;
import org.junit.BeforeClass;
import org.junit.Test;

import integracion.aula.DAOAula;
import integracion.factoria.FactoriaIntegracion;
import negocio.aula.TAula;

public class TestIntegracionListarAulasNoDisponibles {
	
	private static DAOAula daoAula;
	
	@BeforeClass
	public static void before() {
		daoAula = FactoriaIntegracion.getInstancia().generaDAOAula();
	}

	@Test
	public void testListarAulasNoDisponiblesCorrecto() {
		List<TAula> aulas = daoAula.readAllUnavaliable();
		for (TAula tAula : aulas) {
			assertNotNull(tAula);
			assertFalse(tAula.getEstado());
		}
	}
	
}
