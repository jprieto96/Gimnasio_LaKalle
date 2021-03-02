package integracion.clase.mostrarClasesPorEntrenador;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import integracion.aula.DAOAula;
import integracion.clase.DAOClase;
import integracion.factoria.FactoriaIntegracion;
import integracion.personal.DAOPersonal;
import negocio.clase.TClase;

public class TestIntegracionMostrarClasesPorEntrenador {
	
	private static DAOClase daoClase;
	private static int idEntrenador;
	
	@BeforeClass
	public static void before() {
		daoClase = FactoriaIntegracion.getInstancia().generaDAOClase();
		DAOPersonal daoPersonal = FactoriaIntegracion.getInstancia().generaDAOPersonal();
		while(daoPersonal.readEntrenadorById(idEntrenador) == null) ++idEntrenador;
	}

	@Test
	public void testMostrarClasesPorEntrenadorCorrecto() {
		List<TClase> clases = daoClase.mostrarClasesPorEntrenador(idEntrenador);
		for (TClase tClase : clases) {
			assertNotNull(tClase);
			assertNotEquals(tClase.getIdAula(), 0);
		}
	}
	
	@Test
	public void testMostrarClasesPorEntrenadorNoExistente() {
		List<TClase> clases = daoClase.mostrarClasesPorEntrenador(-1);
		assertTrue(clases.isEmpty());
	}

}
