package negocio.clase.asignarClaseAEntrenador;

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

public class TestNegocioAsignarClaseAEntrenador {
	
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
	public void testAsignarClaseAEntrenadorSinRestriccionesCorrecto() {
		DAOPersonal daoPersonal = FactoriaIntegracion.getInstancia().generaDAOPersonal();
		seleccionarCamposUnicos(daoPersonal);
		int idEntrenador = daoPersonal.create(new TEntrenador("Pepe", ejDNI.toString(), ejCB.toString(), ejTFNO.toString(), 2300, 8, true), Evento.ALTA_ENTRENADOR);
		TClase clase = saClase.readById(idNuevaClase);
		clase.setIdEntrenador(idEntrenador);
		boolean ok = saClase.asignarClaseAEntrenador(clase);
		clase = saClase.readById(idNuevaClase);
		assertTrue(ok);
		assertEquals(idEntrenador, clase.getIdEntrenador());
	}
	
	@Test
	public void testAsignarClaseAEntrenadorSuperandoSusClasesPorDiaMaximasIncorrecto() {
		// Creo otra clase con un entrenador que tiene de clases al dia maximas solo 1
		// entonces al asignarle otra clase debe dar error porque no puede dar mas clases que 1
		DAOAula daoAula = FactoriaIntegracion.getInstancia().generaDAOAula();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
		int idAula = daoAula.create(new TAula(128));
		hora = LocalTime.parse("15:00", formatter);
		DAOPersonal daoPersonal = FactoriaIntegracion.getInstancia().generaDAOPersonal();
		seleccionarCamposUnicos(daoPersonal);
		int idEntrenador = daoPersonal.create(new TEntrenador("Pepe", ejDNI.toString(), ejCB.toString(), ejTFNO.toString(), 2300, 1, true), Evento.ALTA_ENTRENADOR); // 1 clase al dia como max
		saClase.create(new TClase(hora, idAula, idEntrenador));
		
		TClase clase = saClase.readById(idNuevaClase);
		clase.setIdEntrenador(idEntrenador);
		boolean ok = saClase.asignarClaseAEntrenador(clase);
		clase = saClase.readById(idNuevaClase);
		assertFalse(ok);
		assertNotEquals(idEntrenador, clase.getIdEntrenador());
	}
	
	@Test
	public void testAsignarClaseAEntrenadorConEntrenadorInexistenteIncorrecto() {
		TClase clase = saClase.readById(idNuevaClase);
		clase.setIdEntrenador(-1);
		boolean ok = saClase.asignarClaseAEntrenador(clase);
		assertFalse(ok);
	}
	
	@Test
	public void testAsignarClaseAEntrenadorIncorrecto() {
		boolean ok = saClase.asignarClaseAEntrenador(null);
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
