package integracion.clase.listarClases;

import static org.junit.Assert.*;

import java.util.List;
import org.junit.BeforeClass;
import org.junit.Test;

import integracion.clase.DAOClase;
import integracion.factoria.FactoriaIntegracion;
import negocio.clase.TClase;

public class TestIntegracionListarClases {
	
	private static DAOClase daoClase;
	
	@BeforeClass
	public static void before() {
		daoClase = FactoriaIntegracion.getInstancia().generaDAOClase();
	}

	@Test
	public void testListarClasesCorrecto() {
		List<TClase> clases = daoClase.readAll();
		for (TClase tClase : clases) {
			assertNotNull(tClase);
		}
	}

}
