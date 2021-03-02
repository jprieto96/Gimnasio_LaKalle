package integracion.clase.listarClasesNoDisponibles;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import integracion.clase.DAOClase;
import integracion.factoria.FactoriaIntegracion;
import negocio.clase.TClase;

public class TestIntegracionListarClasesNoDisponibles {
	
	private static DAOClase daoClase;
	
	@BeforeClass
	public static void before() {
		daoClase = FactoriaIntegracion.getInstancia().generaDAOClase();
	}

	@Test
	public void testListarClasesNoDisponiblesCorrecto() {
		List<TClase> clases = daoClase.readAllUnavalaible();
		for (TClase tClase : clases) {
			assertNotNull(tClase);
			assertFalse(tClase.getEstado());
		}
	}

}
