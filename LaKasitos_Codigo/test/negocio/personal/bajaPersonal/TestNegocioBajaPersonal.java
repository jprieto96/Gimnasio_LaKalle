package negocio.personal.bajaPersonal;

import static org.junit.Assert.*;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import integracion.aula.DAOAula;
import integracion.clase.DAOClase;
import integracion.factoria.FactoriaIntegracion;
import integracion.personal.DAOPersonal;
import negocio.aula.TAula;
import negocio.clase.TClase;
import negocio.factoria.FactoriaNegocio;
import negocio.personal.SAPersonal;
import negocio.personal.TEntrenador;
import negocio.personal.TPersonal;
import presentacion.controlador.Evento;

public class TestNegocioBajaPersonal {
	
	private static SAPersonal saPersonal;
	private int idNuevoPersonal;
	private Integer ejDNI = 1, ejCB = 1, ejTFNO = 1; // campos UNIQUE en BBDD
	private List<TPersonal> listaPersonal;
	
	@Before
	public void before() {
		saPersonal = FactoriaNegocio.getInstancia().generaSAPersonal();
		seleccionarCamposUnicos();
		TPersonal entrenador = new TEntrenador("Pepe", ejDNI.toString(), ejCB.toString(), ejTFNO.toString(), 2300, 4, true);
		idNuevoPersonal = saPersonal.create(entrenador, Evento.ALTA_ENTRENADOR);
	}

	@Test
	public void testBajaPersonalCorrecto() {
		DAOPersonal daoPersonal = FactoriaIntegracion.getInstancia().generaDAOPersonal();
		TPersonal p = daoPersonal.readEntrenadorById(idNuevoPersonal);
		boolean ok = saPersonal.delete(p.getIdPersonal());
		p = daoPersonal.readEntrenadorById(idNuevoPersonal);
		assertFalse(p.getEstado());
		assertTrue(ok);
	}
	
	@Test
	public void testBajaEntrenadorImpartiendoClasesIncorrecto() {
		DAOPersonal daoPersonal = FactoriaIntegracion.getInstancia().generaDAOPersonal();
		DAOAula daoAula = FactoriaIntegracion.getInstancia().generaDAOAula();
		DAOClase daoClase = FactoriaIntegracion.getInstancia().generaDAOClase();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
		LocalTime hora = LocalTime.parse("15:00", formatter);
		int idAula = daoAula.create(new TAula(320));
		TPersonal p = daoPersonal.readEntrenadorById(idNuevoPersonal);
		daoClase.create(new TClase(hora, idAula, idNuevoPersonal));
		boolean ok = saPersonal.delete(p.getIdPersonal());
		p = daoPersonal.readEntrenadorById(idNuevoPersonal);
		assertTrue(p.getEstado());
		assertFalse(ok);
	}
	
	private void seleccionarCamposUnicos() {
		listaPersonal = saPersonal.readAll();
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
