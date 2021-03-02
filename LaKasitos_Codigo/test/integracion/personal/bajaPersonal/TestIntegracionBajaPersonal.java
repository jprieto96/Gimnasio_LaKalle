package integracion.personal.bajaPersonal;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import integracion.factoria.FactoriaIntegracion;
import integracion.personal.DAOPersonal;
import negocio.personal.TEntrenador;
import negocio.personal.TPersonal;
import presentacion.controlador.Evento;

public class TestIntegracionBajaPersonal {
	
	private static DAOPersonal daoPersonal;
	private static int idNuevoPersonal;
	private Integer ejDNI = 1, ejCB = 1, ejTFNO = 1; // campos UNIQUE en BBDD
	private List<TPersonal> listaPersonal;
	
	@Before
	public void before() {
		daoPersonal = FactoriaIntegracion.getInstancia().generaDAOPersonal();
		seleccionarCamposUnicos();
	}

	@Test
	public void testBajaPersonalCorrecto() {
		TPersonal entrenador = new TEntrenador("Pepe", ejDNI.toString(), ejCB.toString(), ejTFNO.toString(), 2300, 4, true);
		idNuevoPersonal = daoPersonal.create(entrenador, Evento.ALTA_ENTRENADOR);
		TPersonal p = daoPersonal.readEntrenadorById(idNuevoPersonal);
		boolean ok = daoPersonal.delete(p.getIdPersonal());
		p = daoPersonal.readEntrenadorById(idNuevoPersonal);
		assertTrue(ok);
		assertFalse(p.getEstado());
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
