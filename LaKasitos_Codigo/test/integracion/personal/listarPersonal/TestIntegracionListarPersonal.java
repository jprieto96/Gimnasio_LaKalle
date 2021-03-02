package integracion.personal.listarPersonal;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import integracion.factoria.FactoriaIntegracion;
import integracion.personal.DAOPersonal;
import negocio.personal.TPersonal;

public class TestIntegracionListarPersonal {
	
	private static DAOPersonal daoPersonal;
	
	@BeforeClass
	public static void before() {
		daoPersonal = FactoriaIntegracion.getInstancia().generaDAOPersonal();
	}

	@Test
	public void testListarPersonalCorrecto() {
		List<TPersonal> listaPersonal = daoPersonal.readAll();
		for (TPersonal p : listaPersonal) {
			assertNotNull(p);
		}
	}
	
}
