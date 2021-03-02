package integracion.clase.altaClase;

import static org.junit.Assert.*;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.junit.BeforeClass;
import org.junit.Test;

import integracion.aula.DAOAula;
import integracion.clase.DAOClase;
import integracion.factoria.FactoriaIntegracion;
import integracion.personal.DAOPersonal;
import negocio.clase.TClase;

public class TestIntegracionAltaClase {
	
	private static DAOClase daoClase;
	private static int idNuevaClase;
	private static LocalTime hora;
	
	@BeforeClass
	public static void before() {
		daoClase = FactoriaIntegracion.getInstancia().generaDAOClase();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
		hora = LocalTime.parse("15:00", formatter);
	}

	@Test
	public void testAltaClaseCorrecto() {
		DAOAula daoAula = FactoriaIntegracion.getInstancia().generaDAOAula();
		DAOPersonal daoPersonal = FactoriaIntegracion.getInstancia().generaDAOPersonal();
		
		int nAula = 1, nEntrenador = 1;
		while(daoAula.readById(nAula) == null) ++nAula;
		while(daoPersonal.readEntrenadorById(nEntrenador) == null) ++nEntrenador;
		
		idNuevaClase = daoClase.create(new TClase(hora, nAula, nEntrenador));
		assertNotEquals(idNuevaClase, -1);
	}
	
	@Test
	public void testAltaClaseIncorrectoConAulaYEntrenadorNoExistentes() {
		idNuevaClase = daoClase.create(new TClase(hora, -1, -1));
		assertEquals(idNuevaClase, -1);
	}
	
	@Test(expected = NullPointerException.class)
	public void testAltaClaseCorrectoEsperandoExcepcion() throws NullPointerException {
		assertEquals(null, daoClase.create(null));
	}

}
