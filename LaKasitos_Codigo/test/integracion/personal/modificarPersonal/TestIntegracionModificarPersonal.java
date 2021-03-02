package integracion.personal.modificarPersonal;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import integracion.factoria.FactoriaIntegracion;
import integracion.personal.DAOPersonal;
import negocio.personal.TEntrenador;
import negocio.personal.TPersonal;
import presentacion.controlador.Evento;

public class TestIntegracionModificarPersonal {
	
	private static DAOPersonal daoPersonal;
	private Integer ejDNI = 1, ejCB = 1, ejTFNO = 1; // campos UNIQUE en BBDD
	private List<TPersonal> listaPersonal;
	private TPersonal personal;
	
	@Before
	public void before() {
		daoPersonal = FactoriaIntegracion.getInstancia().generaDAOPersonal();
		seleccionarCamposUnicos();
	}

	@Test
	public void testModificarPersonalCorrecto() {
		personal = new TEntrenador("Pepe", ejDNI.toString(), ejCB.toString(), ejTFNO.toString(), 2300, 4, true);
		int idNuevoPersonal = daoPersonal.create(personal, Evento.ALTA_ENTRENADOR);
		TEntrenador p = daoPersonal.readEntrenadorById(idNuevoPersonal);
		seleccionarCamposUnicos();
		p.setNombre("David");
		p.setDni(ejDNI.toString());
		p.setCuenta_bancaria(ejCB.toString());
		p.setTelefono(ejTFNO.toString());
		p.setSalario(5000);
		p.setClases_dia(2);
		boolean ok = daoPersonal.update(p, Evento.MODIFICAR_ENTRENADOR);
		assertTrue(ok);
		assertEquals(p.getNombre(), "David");
		assertEquals(p.getDni(), ejDNI.toString());
		assertEquals(p.getCuenta_bancaria(), ejCB.toString());
		assertEquals(p.getTelefono(), ejTFNO.toString());
		assertEquals(p.getSalario(), 5000);
		assertEquals(p.getClases_dia(), 2);
	}
	
	@Test(expected = NullPointerException.class)
	public void testModificarEntrenadorCorrectoEsperandoExcepcion() throws NullPointerException {
		assertEquals(null, daoPersonal.create(null, Evento.ALTA_ENTRENADOR));
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
