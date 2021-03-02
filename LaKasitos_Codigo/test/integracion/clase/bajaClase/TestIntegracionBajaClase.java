package integracion.clase.bajaClase;

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

public class TestIntegracionBajaClase {
	
	private static DAOClase daoClase;
	private static int idNuevaClase;
	private static LocalTime hora;
	
	@BeforeClass
	public static void before() {
		DAOAula daoAula = FactoriaIntegracion.getInstancia().generaDAOAula();
		DAOPersonal daoPersonal = FactoriaIntegracion.getInstancia().generaDAOPersonal();
		daoClase = FactoriaIntegracion.getInstancia().generaDAOClase();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
		hora = LocalTime.parse("15:00", formatter);
		int nAula = 1, nEntrenador = 1;
		while(daoAula.readById(nAula) == null) ++nAula;
		while(daoPersonal.readEntrenadorById(nEntrenador) == null) ++nEntrenador;
		idNuevaClase = daoClase.create(new TClase(hora, nAula, nEntrenador));
	}

	@Test
	public void testBajaClaseCorrecto() {
		boolean ok = daoClase.delete(idNuevaClase);
		TClase clase = daoClase.readById(idNuevaClase);
		assertTrue(ok);
		assertFalse(clase.getEstado());
	}

}
