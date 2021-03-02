package integracion.clase.reactivarClase;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import integracion.clase.DAOClase;
import integracion.factoria.FactoriaIntegracion;
import negocio.clase.TClase;

public class TestIntegracionReactivarClase {
	
	private static DAOClase daoClase;
	private static int idClase;
	
	@BeforeClass
	public static void before() {
		daoClase = FactoriaIntegracion.getInstancia().generaDAOClase();
		while(daoClase.readById(idClase) == null) ++idClase;
		daoClase.delete(idClase);
	}

	@Test
	public void testReactivarClaseCorrecto() {
		boolean ok = daoClase.reactivate(idClase);
		TClase clase = daoClase.readById(idClase);
		assertTrue(ok);
		assertTrue(clase.getEstado());
	}
	
}
