package negocio.clase.asignarClaseAAula;

import static org.junit.Assert.*;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import integracion.aula.DAOAula;
import integracion.factoria.FactoriaIntegracion;
import integracion.personal.DAOPersonal;
import negocio.aula.TAula;
import negocio.clase.SA_Clase;
import negocio.clase.TClase;
import negocio.factoria.FactoriaNegocio;
import negocio.personal.TEntrenador;
import negocio.personal.TPersonal;
import presentacion.controlador.Evento;

public class TestNegocioAsignarClaseAAula {
	
	private static SA_Clase saClase;
	private int idNuevaClase;
	private Integer ejDNI = 1, ejCB = 1, ejTFNO = 1; // campos UNIQUE en BBDD
	private LocalTime hora;
	
	@Before
	public void before() {
		saClase = FactoriaNegocio.getInstancia().generaSAClase();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
		hora = LocalTime.parse("15:00", formatter);
		DAOAula daoAula = FactoriaIntegracion.getInstancia().generaDAOAula();
		DAOPersonal daoPersonal = FactoriaIntegracion.getInstancia().generaDAOPersonal();
		int idAula = daoAula.create(new TAula(230));
		seleccionarCamposUnicos(daoPersonal);
		int idEntrenador = daoPersonal.create(new TEntrenador("Pepe", ejDNI.toString(), ejCB.toString(), ejTFNO.toString(), 2300, 4, true), Evento.ALTA_ENTRENADOR);
		idNuevaClase = saClase.create(new TClase(hora, idAula, idEntrenador));
	}

	@Test
	public void testAsignarClaseAAulaSinRestriccionesCorrecto() {
		DAOAula daoAula = FactoriaIntegracion.getInstancia().generaDAOAula();
		int idAula = daoAula.create(new TAula(324));
		TClase clase = saClase.readById(idNuevaClase);
		clase.setIdAula(idAula);
		boolean ok = saClase.asignarClaseAAula(clase);
		clase = saClase.readById(idNuevaClase);
		assertTrue(ok);
		assertEquals(idAula, clase.getIdAula());
	}
	
	@Test
	public void testAsignarClaseAAulaMismaHoraIncorrecto() {
		// Creo otra clase a la misma hora que la creada en un principio y al asignar la clase
		// a otra aula que ya tiene una clase a esa hora debe dar error
		DAOAula daoAula = FactoriaIntegracion.getInstancia().generaDAOAula();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
		int idAula = daoAula.create(new TAula(128));
		hora = LocalTime.parse("15:00", formatter);
		DAOPersonal daoPersonal = FactoriaIntegracion.getInstancia().generaDAOPersonal();
		seleccionarCamposUnicos(daoPersonal);
		int idEntrenador = daoPersonal.create(new TEntrenador("Pepe", ejDNI.toString(), ejCB.toString(), ejTFNO.toString(), 2300, 4, true), Evento.ALTA_ENTRENADOR);
		saClase.create(new TClase(hora, idAula, idEntrenador));
		
		TClase clase = saClase.readById(idNuevaClase);
		clase.setIdAula(idAula);
		boolean ok = saClase.asignarClaseAAula(clase);
		clase = saClase.readById(idNuevaClase);
		assertFalse(ok);
		assertNotEquals(idAula, clase.getIdAula());
	}
	
	@Test
	public void testAsignarClaseAAulaConAulaInexistenteIncorrecto() {
		TClase clase = saClase.readById(idNuevaClase);
		clase.setIdAula(-1);
		boolean ok = saClase.asignarClaseAAula(clase);
		assertFalse(ok);
	}
	
	@Test
	public void testAsignarClaseAAulaIncorrecto() {
		boolean ok = saClase.asignarClaseAAula(null);
		assertFalse(ok);
	}
	
	private void seleccionarCamposUnicos(DAOPersonal daoPersonal) {
		List<TPersonal> listaPersonal = daoPersonal.readAll();
		for (int i = 0; i < listaPersonal.size(); i++) {
			for (int j = 0; j < listaPersonal.size(); j++) {
				if(i != j) {
					if(listaPersonal.get(j).getCuenta_bancaria().equals(ejCB.toString()))
						++ejCB;
					if(listaPersonal.get(j).getDni().equals(ejDNI.toString()))
						++ejDNI;
					if(listaPersonal.get(j).getTelefono().equals(ejTFNO.toString()))
						++ejTFNO;
				}
			}
		}
	}

}
