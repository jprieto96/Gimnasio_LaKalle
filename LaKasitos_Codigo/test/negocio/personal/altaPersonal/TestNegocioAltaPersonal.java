package negocio.personal.altaPersonal;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import integracion.factoria.FactoriaIntegracion;
import integracion.personal.DAOPersonal;
import negocio.factoria.FactoriaNegocio;
import negocio.personal.SAPersonal;
import negocio.personal.TEntrenador;
import negocio.personal.TMantenimiento;
import negocio.personal.TPersonal;
import presentacion.controlador.Evento;

public class TestNegocioAltaPersonal {
	
	private static SAPersonal saPersonal;
	private int idNuevoPersonal;
	private Integer ejDNI = 1, ejCB = 1, ejTFNO = 1; // campos UNIQUE en BBDD
	private List<TPersonal> listaPersonal;
	
	@Before
	public void before() {
		saPersonal = FactoriaNegocio.getInstancia().generaSAPersonal();
		seleccionarCamposUnicos();
	}

	@Test
	public void testAltaEntrenadorCorrecto() {
		TPersonal entrenador = new TEntrenador("Pepe", ejDNI.toString(), ejCB.toString(), ejTFNO.toString(), 2300, 4, true);
		idNuevoPersonal = saPersonal.create(entrenador, Evento.ALTA_ENTRENADOR);
		assertNotEquals(idNuevoPersonal, -1);
	}
	
	@Test
	public void testAltaMantenimientoCorrecto() {
		TPersonal mantenimiento = new TMantenimiento("Carolina", ejDNI.toString(), ejCB.toString(), ejTFNO.toString(), 2700, "Tarde", true);
		idNuevoPersonal = saPersonal.create(mantenimiento, Evento.ALTA_MANTENIMIENTO);
		assertNotEquals(idNuevoPersonal, -1);
	}
	
	@Test
	public void testAltaEntrenadorMedianteReactivacionCorrecto() {
		DAOPersonal daoPersonal = FactoriaIntegracion.getInstancia().generaDAOPersonal();
		TPersonal entrenador = new TEntrenador("Pepe", ejDNI.toString(), ejCB.toString(), ejTFNO.toString(), 2300, 4, true);
		idNuevoPersonal = saPersonal.create(entrenador, Evento.ALTA_ENTRENADOR);
		saPersonal.delete(idNuevoPersonal);
		TPersonal p = daoPersonal.readEntrenadorById(idNuevoPersonal);
		saPersonal.create(p, Evento.ALTA_ENTRENADOR);
		p = daoPersonal.readEntrenadorById(idNuevoPersonal);
		assertTrue(p.getEstado());
	}
	
	@Test
	public void testAltaEntrenadorIncorrecto() {
		idNuevoPersonal = saPersonal.create(null, Evento.ALTA_ENTRENADOR);
		assertEquals(idNuevoPersonal, -1);
	}
	
	@Test
	public void testAltaMantenimientoIncorrecto() {
		idNuevoPersonal = saPersonal.create(null, Evento.ALTA_MANTENIMIENTO);
		assertEquals(idNuevoPersonal, -1);
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
