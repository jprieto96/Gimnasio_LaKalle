package negocio.clase.altaClase;

import static org.junit.Assert.*;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.junit.BeforeClass;
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

public class TestNegocioAltaClase {
	
	private static SA_Clase saClase;
	private static int idNuevaClase;
	private Integer ejDNI = 1, ejCB = 1, ejTFNO = 1; // campos UNIQUE en BBDD
	private static LocalTime hora;
	
	@BeforeClass
	public static void before() {
		saClase = FactoriaNegocio.getInstancia().generaSAClase();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
		hora = LocalTime.parse("15:00", formatter);
	}

	@Test
	public void testAltaClaseSinRestriccionesCorrecto() {
		DAOAula daoAula = FactoriaIntegracion.getInstancia().generaDAOAula();
		DAOPersonal daoPersonal = FactoriaIntegracion.getInstancia().generaDAOPersonal();
		int idAula = daoAula.create(new TAula(230));
		seleccionarCamposUnicos(daoPersonal);
		int idEntrenador = daoPersonal.create(new TEntrenador("Pepe", ejDNI.toString(), ejCB.toString(), ejTFNO.toString(), 2300, 4, true), Evento.ALTA_ENTRENADOR);
		idNuevaClase = saClase.create(new TClase(hora, idAula, idEntrenador));
		assertNotEquals(idNuevaClase, -1);
	}
	
	@Test
	public void testAltaClaseMedianteReactivacionSinRestriccionesCorrecto() {
		DAOAula daoAula = FactoriaIntegracion.getInstancia().generaDAOAula();
		DAOPersonal daoPersonal = FactoriaIntegracion.getInstancia().generaDAOPersonal();
		int idAula = daoAula.create(new TAula(230));
		seleccionarCamposUnicos(daoPersonal);
		int idEntrenador = daoPersonal.create(new TEntrenador("Pepe", ejDNI.toString(), ejCB.toString(), ejTFNO.toString(), 2300, 4, true), Evento.ALTA_ENTRENADOR);
		idNuevaClase = saClase.create(new TClase(hora, idAula, idEntrenador));
		saClase.delete(idNuevaClase);
		TClase c = saClase.readById(idNuevaClase);
		saClase.create(c);
		c = saClase.readById(idNuevaClase);
		assertTrue(c.getEstado());
	}
	
	@Test
	public void testAltaClaseEnAulaAMismaHoraIncorrecto() {
		DAOAula daoAula = FactoriaIntegracion.getInstancia().generaDAOAula();
		DAOPersonal daoPersonal = FactoriaIntegracion.getInstancia().generaDAOPersonal();
		int idAula = daoAula.create(new TAula(230));
		seleccionarCamposUnicos(daoPersonal);
		int idEntrenador = daoPersonal.create(new TEntrenador("Pepe", ejDNI.toString(), ejCB.toString(), ejTFNO.toString(), 2300, 4, true), Evento.ALTA_ENTRENADOR);
		idNuevaClase = saClase.create(new TClase(hora, idAula, idEntrenador));
		seleccionarCamposUnicos(daoPersonal);
		int idEntrenador2 = daoPersonal.create(new TEntrenador("Pepe", ejDNI.toString(), ejCB.toString(), ejTFNO.toString(), 2300, 4, true), Evento.ALTA_ENTRENADOR);
		// Creo otra clase en el mismo aula a la misma hora por lo que da error
		idNuevaClase = saClase.create(new TClase(hora, idAula, idEntrenador2));
		assertEquals(idNuevaClase, -1);
	}
	
	@Test
	public void testAltaClaseConEntrenadorSuperandoSusClasesDiaIncorrecto() {
		DAOAula daoAula = FactoriaIntegracion.getInstancia().generaDAOAula();
		DAOPersonal daoPersonal = FactoriaIntegracion.getInstancia().generaDAOPersonal();
		int idAula = daoAula.create(new TAula(230));
		seleccionarCamposUnicos(daoPersonal);
		//Entrenador que solo puede dar 2 clases al dia como maximo
		int clasesPorDiaMaximas = 2;
		int idEntrenador = daoPersonal.create(new TEntrenador("Pepe", ejDNI.toString(), ejCB.toString(), ejTFNO.toString(), 2300, clasesPorDiaMaximas, true), Evento.ALTA_ENTRENADOR);
		idNuevaClase = saClase.create(new TClase(hora, idAula, idEntrenador));
		int idAula2 = daoAula.create(new TAula(230));
		idNuevaClase = saClase.create(new TClase(hora, idAula2, idEntrenador));
		int idAula3 = daoAula.create(new TAula(230));
		//A la tercera vez que lo creamos el entrenador ya esta dando clases dos veces entonces a la tercera da error
		idNuevaClase = saClase.create(new TClase(hora, idAula3, idEntrenador));
		assertEquals(idNuevaClase, -1);
	}
	
	@Test
	public void testAltaClaseIncorrectoPorAulaNoDisponibleYEntrenadorNoDisponible() {
		DAOAula daoAula = FactoriaIntegracion.getInstancia().generaDAOAula();
		DAOPersonal daoPersonal = FactoriaIntegracion.getInstancia().generaDAOPersonal();
		int idAula = daoAula.create(new TAula(230));
		daoAula.delete(idAula);
		seleccionarCamposUnicos(daoPersonal);
		int idPersonal = daoPersonal.create(new TEntrenador("Pepe", ejDNI.toString(), ejCB.toString(), ejTFNO.toString(), 2300, 4, true), Evento.ALTA_ENTRENADOR);
		TPersonal e = daoPersonal.readEntrenadorById(idPersonal);
		daoPersonal.delete(e.getIdPersonal());
		saClase.create(new TClase(hora, idAula, idPersonal));
		assertEquals(idNuevaClase, -1);
	}
	
	@Test
	public void testAltaClaseIncorrectoPorAulaYEntrenadorInexistentes() {
		idNuevaClase = saClase.create(new TClase(hora, -1, -1));
		assertEquals(idNuevaClase, -1);
	}
	
	@Test
	public void testAltaClaseIncorrecto() {
		idNuevaClase = saClase.create(null);
		assertEquals(idNuevaClase, -1);
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
