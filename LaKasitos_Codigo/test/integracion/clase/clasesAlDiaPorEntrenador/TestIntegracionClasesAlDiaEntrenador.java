package integracion.clase.clasesAlDiaPorEntrenador;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import integracion.clase.DAOClase;
import integracion.factoria.FactoriaIntegracion;
import integracion.personal.DAOPersonal;
import negocio.personal.TEntrenador;

public class TestIntegracionClasesAlDiaEntrenador {
	
	private static DAOClase daoClase;
	private static TEntrenador entrenador;
	
	@BeforeClass
	public static void before() {
		daoClase = FactoriaIntegracion.getInstancia().generaDAOClase();
		DAOPersonal daoPersonal = FactoriaIntegracion.getInstancia().generaDAOPersonal();
		int nEntrenador = 1;
		while(daoPersonal.readEntrenadorById(nEntrenador) == null) ++nEntrenador;
		entrenador = daoPersonal.readEntrenadorById(nEntrenador);
	}

	@Test
	public void testClasesAlDiaEntrenadorCorrecto() {
		int c = daoClase.clasesAlDiaPorEntrenador(entrenador.getId_entrenador());
		assertNotEquals(-1, c);
	}
	
	@Test
	public void testClasesAlDiaEntrenadorNoExistente() {
		int c = daoClase.clasesAlDiaPorEntrenador(-1);
		assertEquals(0, c);
	}

}
