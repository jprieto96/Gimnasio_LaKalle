package integracion.personal.mostrarDatosEntrenador;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import integracion.factoria.FactoriaIntegracion;
import integracion.personal.DAOPersonal;
import negocio.personal.TEntrenador;
import negocio.personal.TPersonal;
import presentacion.controlador.Evento;

public class TestIntegracionMostrarDatosEntrenador {
	
	private static DAOPersonal daoPersonal;
	private static TPersonal personal;
	private Integer ejDNI = 1, ejCB = 1, ejTFNO = 1; // campos UNIQUE en BBDD
	private List<TPersonal> listaPersonal;
	private static int idPersonal;
	
	@Before
	public void before() {
		daoPersonal = FactoriaIntegracion.getInstancia().generaDAOPersonal();
		seleccionarCamposUnicos();
		personal = new TEntrenador("Pepe", ejDNI.toString(), ejCB.toString(), ejTFNO.toString(), 2300, 4, true);
		idPersonal = daoPersonal.create(personal, Evento.ALTA_ENTRENADOR);
	}

	@Test
	public void testMostrarDatosEntrenadorCorrecto() {
		TEntrenador p = daoPersonal.readEntrenadorById(idPersonal);
		assertEquals(idPersonal, p.getId_entrenador());
	}
	
	@Test
	public void testMostrarDatosEntrenadorIncorrecto() {
		TPersonal p = daoPersonal.readById(-1);
		assertNull(p);
	}
	
	private void seleccionarCamposUnicos() {
		listaPersonal = daoPersonal.readAll();
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
