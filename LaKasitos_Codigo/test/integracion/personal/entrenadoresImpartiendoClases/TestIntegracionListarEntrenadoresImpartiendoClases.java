package integracion.personal.entrenadoresImpartiendoClases;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import integracion.factoria.FactoriaIntegracion;
import integracion.personal.DAOPersonal;
import negocio.personal.TEntrenador;

public class TestIntegracionListarEntrenadoresImpartiendoClases {
	
	private static DAOPersonal daoPersonal;
	
	@BeforeClass
	public static void before() {
		daoPersonal = FactoriaIntegracion.getInstancia().generaDAOPersonal();
	}

	@Test
	public void testListarEntrenadoresImpartiendoClasesCorrecto() {
		List<TEntrenador> listaEntrenadores = daoPersonal.entrenadoresImpartiendoClases();
		for (TEntrenador e : listaEntrenadores) {
			assertNotNull(e);
			assertNotEquals(e.getClases_dia(), 0);
		}
	}
	
}
