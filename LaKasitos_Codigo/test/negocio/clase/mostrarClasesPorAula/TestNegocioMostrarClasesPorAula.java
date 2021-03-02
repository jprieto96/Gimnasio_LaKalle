package negocio.clase.mostrarClasesPorAula;

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

public class TestNegocioMostrarClasesPorAula {
	
	private static SA_Clase saClase;	
	private int idAula;
	private Integer ejDNI = 1, ejCB = 1, ejTFNO = 1; // campos UNIQUE en BBDD
	private LocalTime hora;
	
	@Before
	public void before() {
		saClase = FactoriaNegocio.getInstancia().generaSAClase();
		DAOAula daoAula = FactoriaIntegracion.getInstancia().generaDAOAula();
		DAOPersonal daoPersonal = FactoriaIntegracion.getInstancia().generaDAOPersonal();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
		idAula = daoAula.create(new TAula(230));
		seleccionarCamposUnicos(daoPersonal);
		int idEntrenador = daoPersonal.create(new TEntrenador("Pepe", ejDNI.toString(), ejCB.toString(), ejTFNO.toString(), 2300, 4, true), Evento.ALTA_ENTRENADOR);
		hora = LocalTime.parse("12:45", formatter);
		saClase.create(new TClase(hora, idAula, idEntrenador));
	}

	@Test
	public void testListarClasesPorAulaCorrecto() {
		List<TClase> clases = saClase.mostrarClasesPorAula(idAula);
		for (TClase c : clases) {
			assertNotNull(c);
			assertEquals(c.getIdAula(), idAula);
		}
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
