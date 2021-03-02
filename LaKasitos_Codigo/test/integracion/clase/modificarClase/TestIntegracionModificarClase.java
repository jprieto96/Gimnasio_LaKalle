package integracion.clase.modificarClase;

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

public class TestIntegracionModificarClase {
	
	private static DAOClase daoClase;
	private static int idNuevaClase;
	private static LocalTime hora;
	private static DateTimeFormatter formatter;
	
	@BeforeClass
	public static void before() {
		daoClase = FactoriaIntegracion.getInstancia().generaDAOClase();
		formatter = DateTimeFormatter.ofPattern("HH:mm");
		hora = LocalTime.parse("15:00", formatter);
		DAOAula daoAula = FactoriaIntegracion.getInstancia().generaDAOAula();
		DAOPersonal daoPersonal = FactoriaIntegracion.getInstancia().generaDAOPersonal();
		
		int nAula = 1, nEntrenador = 1;
		while(daoAula.readById(nAula) == null) ++nAula;
		while(daoPersonal.readEntrenadorById(nEntrenador) == null) ++nEntrenador;
		
		idNuevaClase = daoClase.create(new TClase(hora, nAula, nEntrenador));
	}

	@Test
	public void testModificarClaseCorrecto() {
		LocalTime nuevahora = LocalTime.parse("16:00", formatter);
		boolean ok = daoClase.update(new TClase(idNuevaClase, nuevahora));
		assertTrue(ok);
	}
	
	@Test(expected = NullPointerException.class)
	public void testModificarClaseCorrectoEsperandoExcepcion() throws NullPointerException {
		assertEquals(null, daoClase.update(null));
	}


}
