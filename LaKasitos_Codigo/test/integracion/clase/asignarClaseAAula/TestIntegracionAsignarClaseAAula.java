package integracion.clase.asignarClaseAAula;

import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

import integracion.aula.DAOAula;
import integracion.clase.DAOClase;
import integracion.factoria.FactoriaIntegracion;
import negocio.clase.TClase;

public class TestIntegracionAsignarClaseAAula {
	
	private static DAOClase daoClase;
	private static int idClase;
	
	@BeforeClass
	public static void before() {
		daoClase = FactoriaIntegracion.getInstancia().generaDAOClase();
		while(daoClase.readById(idClase) == null) ++idClase;
	}

	@Test
	public void testAsignarClaseAAulaCorrecto() {
		DAOAula daoAula = FactoriaIntegracion.getInstancia().generaDAOAula();
		TClase clase = daoClase.readById(idClase);
		int nuevoIdAula = 1;
		while(daoAula.readById(nuevoIdAula) == null || clase.getIdAula() == nuevoIdAula)
			++nuevoIdAula;
		clase.setIdAula(nuevoIdAula);
		boolean ok = daoClase.asignarClaseAAula(clase);
		clase = daoClase.readById(idClase);
		assertEquals(clase.getIdAula(), nuevoIdAula);
		assertTrue(ok);
	}
	
	@Test(expected = NullPointerException.class)
	public void testAsignarClaseaAulaEsperandoExcepcion() throws Exception {
		assertNull(daoClase.asignarClaseAAula(null));
	}

}
