package integracion.personal.listarPersonalNoDisponible;

import static org.junit.Assert.*;

import java.util.List;
import org.junit.BeforeClass;
import org.junit.Test;
import integracion.factoria.FactoriaIntegracion;
import integracion.personal.DAOPersonal;
import negocio.personal.TPersonal;

public class TestIntegracionListarPersonalNoDisponible {
	
	private static DAOPersonal daoPersonal;
	
	@BeforeClass
	public static void before() {
		daoPersonal = FactoriaIntegracion.getInstancia().generaDAOPersonal();
	}

	@Test
	public void testListarPersonalNoDisponiblesCorrecto() {
		List<TPersonal> listPersonal = daoPersonal.readAllUnavalaible();
		for (TPersonal p : listPersonal) {
			assertNotNull(p);
			assertFalse(p.getEstado());
		}
	}
	
}
