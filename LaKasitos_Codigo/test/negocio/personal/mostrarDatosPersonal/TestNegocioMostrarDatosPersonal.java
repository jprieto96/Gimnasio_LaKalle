package negocio.personal.mostrarDatosPersonal;

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

public class TestNegocioMostrarDatosPersonal {
	
	private static SAPersonal saPersonal;
	private static TPersonal personal;
	private Integer ejDNI = 1, ejCB = 1, ejTFNO = 1; // campos UNIQUE en BBDD
	private List<TPersonal> listaPersonal;
	private static int idPersonal;
	
	@Before
	public void before() {
		DAOPersonal daoPersonal = FactoriaIntegracion.getInstancia().generaDAOPersonal();
		saPersonal = FactoriaNegocio.getInstancia().generaSAPersonal();
		seleccionarCamposUnicos();
		personal = new TEntrenador("Pepe", ejDNI.toString(), ejCB.toString(), ejTFNO.toString(), 2300, 4, true);
		idPersonal = saPersonal.create(personal, Evento.ALTA_ENTRENADOR);
		personal = daoPersonal.readEntrenadorById(idPersonal);
	}

	@Test
	public void testMostrarDatosPersonalCorrecto() {
		TPersonal p = saPersonal.readById(personal.getIdPersonal());
		assertEquals(personal.toString(), p.toString());
	}
	
	@Test
	public void testMostrarDatosPersonalIncorrecto() {
		TPersonal p = saPersonal.readById(-1);
		assertNull(p);
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
