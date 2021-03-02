package negocio.aula.bajaAula;

import static org.junit.Assert.*;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.junit.Before;
import org.junit.Test;

import integracion.clase.DAOClase;
import integracion.factoria.FactoriaIntegracion;
import integracion.personal.DAOPersonal;
import negocio.aula.SA_Aula;
import negocio.aula.TAula;
import negocio.clase.TClase;
import negocio.factoria.FactoriaNegocio;

public class TestNegocioBajaAula {
	
	private static SA_Aula sa_Aula;
	private int idNuevaAula;
	
	@Before
	public void before() {
		sa_Aula = FactoriaNegocio.getInstancia().generaSAAula();
		idNuevaAula = sa_Aula.create(new TAula(100));
	}

	@Test
	public void testBajaAulaSinImpartirClasesCorrecto() {
		boolean ok = sa_Aula.delete(idNuevaAula);
		TAula aula = sa_Aula.readById(idNuevaAula);
		assertTrue(ok);
		assertFalse(aula.getEstado());
	}
	
	@Test
	public void testBajaAulaImpartiendoClasesIncorrecto() {
		DAOClase daoClase = FactoriaIntegracion.getInstancia().generaDAOClase();
		DAOPersonal daoPersonal = FactoriaIntegracion.getInstancia().generaDAOPersonal();
		
		int nEntrenador = 1;
		while(daoPersonal.readEntrenadorById(nEntrenador) == null) ++nEntrenador;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
		LocalTime hora = LocalTime.parse("15:00", formatter);
		
		daoClase.create(new TClase(hora, idNuevaAula, nEntrenador));
		boolean ok = sa_Aula.delete(idNuevaAula); // Dando de baja un aula que tiene clases
		TAula aula = sa_Aula.readById(idNuevaAula);
		assertFalse(ok);
		assertTrue(aula.getEstado());
		
	}

}
