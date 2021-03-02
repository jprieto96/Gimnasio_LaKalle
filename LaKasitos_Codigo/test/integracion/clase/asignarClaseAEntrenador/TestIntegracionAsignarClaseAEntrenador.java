package integracion.clase.asignarClaseAEntrenador;

import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;
import integracion.clase.DAOClase;
import integracion.factoria.FactoriaIntegracion;
import integracion.personal.DAOPersonal;
import negocio.clase.TClase;

public class TestIntegracionAsignarClaseAEntrenador {
	
	private static DAOClase daoClase;
	private static int idClase;
	
	@BeforeClass
	public static void before() {
		daoClase = FactoriaIntegracion.getInstancia().generaDAOClase();
		while(daoClase.readById(idClase) == null) ++idClase;
	}

	@Test
	public void testAsignarClaseAEntrenadorCorrecto() {
		DAOPersonal daoPersonal = FactoriaIntegracion.getInstancia().generaDAOPersonal();
		TClase clase = daoClase.readById(idClase);
		int nuevoIdEntrenador = 1;
		while(daoPersonal.readEntrenadorById(nuevoIdEntrenador) == null || clase.getIdEntrenador() == nuevoIdEntrenador)
			++nuevoIdEntrenador;
		clase.setIdEntrenador(nuevoIdEntrenador);
		boolean ok = daoClase.asignarClaseAEntrenador(clase);
		clase = daoClase.readById(idClase);
		assertEquals(clase.getIdEntrenador(), nuevoIdEntrenador);
		assertTrue(ok);
	}
	
	@Test(expected = NullPointerException.class)
	public void testAsignarClaseaAulaEsperandoExcepcion() throws Exception {
		assertNull(daoClase.asignarClaseAEntrenador(null));
	}

}
