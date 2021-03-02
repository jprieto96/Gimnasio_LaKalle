package negocio.personal.modificarPersonal;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import integracion.factoria.FactoriaIntegracion;
import integracion.personal.DAOPersonal;
import negocio.factoria.FactoriaNegocio;
import negocio.personal.SAPersonal;
import negocio.personal.TEntrenador;
import negocio.personal.TPersonal;
import presentacion.controlador.Evento;

public class TestNegocioModificarPersonal {
	
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
	public void testModificarPersonalCorrecto() {
		DAOPersonal daoPersonal = FactoriaIntegracion.getInstancia().generaDAOPersonal();
		seleccionarCamposUnicos();
		TPersonal p = daoPersonal.readEntrenadorById(idNuevoPersonal);
		TPersonal e = new TEntrenador(p.getIdPersonal(), "PepeModificado", ejDNI.toString(), ejCB.toString(), ejTFNO.toString(), 2900, 4);
		e.setEstado(p.getEstado());
		((TEntrenador)e).setId_entrenador(((TEntrenador)p).getId_entrenador());
		boolean ok = saPersonal.update(e, Evento.MODIFICAR_ENTRENADOR);
		p = daoPersonal.readEntrenadorById(idNuevoPersonal);
		assertTrue(ok);
		assertEquals(e.toString(), p.toString());
	}
	
	@Test
	public void testAltaEntrenadorIncorrecto() {
		boolean ok = saPersonal.update(null, Evento.ALTA_ENTRENADOR);
		assertFalse(ok);
	}
	
	@Test
	public void testAltaMantenimientoIncorrecto() {
		boolean ok = saPersonal.update(null, Evento.ALTA_MANTENIMIENTO);
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
