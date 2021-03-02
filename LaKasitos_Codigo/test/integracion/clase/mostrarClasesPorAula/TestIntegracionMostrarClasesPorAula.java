package integracion.clase.mostrarClasesPorAula;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import integracion.aula.DAOAula;
import integracion.clase.DAOClase;
import integracion.factoria.FactoriaIntegracion;
import negocio.clase.TClase;

public class TestIntegracionMostrarClasesPorAula {
	
	private static DAOClase daoClase;
	private static int idAula;
	
	@BeforeClass
	public static void before() {
		daoClase = FactoriaIntegracion.getInstancia().generaDAOClase();
		DAOAula daoAula = FactoriaIntegracion.getInstancia().generaDAOAula();
		while(daoAula.readById(idAula) == null) ++idAula;
	}

	@Test
	public void testMostrarClasesPorAulaCorrecto() {
		List<TClase> clases = daoClase.mostrarClasesPorAula(idAula);
		for (TClase tClase : clases) {
			assertNotNull(tClase);
			assertNotEquals(tClase.getIdAula(), 0);
		}
	}
	
	@Test
	public void testMostrarClasesPorAulaNoExistente() {
		List<TClase> clases = daoClase.mostrarClasesPorAula(-1);
		assertTrue(clases.isEmpty());
	}

}
