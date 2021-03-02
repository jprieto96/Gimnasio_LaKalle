package negocio.clase.modificarClase;

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

public class TestNegocioModificarClase {
	
	private static SA_Clase saClase;
	private int idNuevaClase;
	private Integer ejDNI = 1, ejCB = 1, ejTFNO = 1; // campos UNIQUE en BBDD
	private LocalTime hora;
	private DateTimeFormatter formatter;
	
	@Before
	public void before() {
		saClase = FactoriaNegocio.getInstancia().generaSAClase();
		formatter = DateTimeFormatter.ofPattern("HH:mm");
		hora = LocalTime.parse("15:00", formatter);
		DAOAula daoAula = FactoriaIntegracion.getInstancia().generaDAOAula();
		DAOPersonal daoPersonal = FactoriaIntegracion.getInstancia().generaDAOPersonal();
		int idAula = daoAula.create(new TAula(230));
		seleccionarCamposUnicos(daoPersonal);
		int idEntrenador = daoPersonal.create(new TEntrenador("Pepe", ejDNI.toString(), ejCB.toString(), ejTFNO.toString(), 2300, 4, true), Evento.ALTA_ENTRENADOR);
		idNuevaClase = saClase.create(new TClase(hora, idAula, idEntrenador));
	}

	@Test
	public void testModificarClaseSinRestriccionesCorrecto() {
		TClase c = saClase.readById(idNuevaClase);
		boolean ok = saClase.update(c);
		saClase.readById(idNuevaClase);
		assertTrue(ok);
		assertEquals(c.getHora(), hora);
	}
	
	@Test
	public void testModificarClaseEnAulaAMismaHoraIncorrecto() {
		TClase c = saClase.readById(idNuevaClase);
		DAOPersonal daoPersonal = FactoriaIntegracion.getInstancia().generaDAOPersonal();
		
		//Creamos otra clase a otra hora para luego modificar la nuestra y ponerla a la misma hora que esta, que se imparte en el mismo aula que nuestra clase
		seleccionarCamposUnicos(daoPersonal);
		String horaNueva = "16:38";
		int idEntrenador = daoPersonal.create(new TEntrenador("Pepe", ejDNI.toString(), ejCB.toString(), ejTFNO.toString(), 2300, 4, true), Evento.ALTA_ENTRENADOR);
		saClase.create(new TClase(LocalTime.parse(horaNueva, formatter), c.getIdAula(), idEntrenador));
		
		//Ponemos la hora de una clase ya existente en el mismo aula y debe dar error porque
		//no puede haber dos clases a la misma hora en el mismo aula
		c.setHora(LocalTime.parse(horaNueva, formatter));
		boolean ok = saClase.update(c);
		c = saClase.readById(idNuevaClase);
		assertFalse(ok);
		assertEquals(c.getHora(), hora);
	}
	
	@Test
	public void testModificarClaseIncorrecto() {
		boolean ok = saClase.update(null);
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
