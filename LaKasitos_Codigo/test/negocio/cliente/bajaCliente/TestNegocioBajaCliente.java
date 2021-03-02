package negocio.cliente.bajaCliente;

import static org.junit.Assert.*;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import integracion.aula.DAOAula;
import integracion.clase.DAOClase;
import integracion.factoria.FactoriaIntegracion;
import integracion.matricula.DAOMatricula;
import integracion.personal.DAOPersonal;
import negocio.aula.TAula;
import negocio.clase.TClase;
import negocio.cliente.SA_Cliente;
import negocio.cliente.TCliente;
import negocio.cliente.TDatosCompletosCliente;
import negocio.factoria.FactoriaNegocio;
import negocio.matricula.TMatricula;
import negocio.personal.TEntrenador;
import negocio.personal.TPersonal;
import presentacion.controlador.Evento;

public class TestNegocioBajaCliente {
	
	private static SA_Cliente saCliente;
	private Integer ejDNI = 1, ejCB = 1, ejTFNO = 1, ejDom = 1, ejMail = 1; // campos UNIQUE en BBDD
	private int idNuevoCliente;
	private List<TCliente> clientes;
	
	@Before
	public void before() {
		saCliente = FactoriaNegocio.getInstancia().generaSACliente();
		seleccionarCamposUnicos();
		idNuevoCliente = saCliente.create(new TCliente(-1, "Paco", ejDNI.toString(), ejCB.toString(), ejTFNO.toString(), ejDom.toString(), ejMail.toString()));
	}

	@Test
	public void testBajaClienteSinMatriculasCorrecto() {
		boolean ok = saCliente.delete(idNuevoCliente);
		TDatosCompletosCliente cliente = saCliente.readById(idNuevoCliente);
		assertFalse(cliente.getCliente().getEstado());
		assertTrue(ok);
	}
	
	@Test
	public void testBajaClienteConMatriculasIncorrecto() {
		DAOClase daoClase = FactoriaIntegracion.getInstancia().generaDAOClase();
		DAOPersonal daoPersonal = FactoriaIntegracion.getInstancia().generaDAOPersonal();
		DAOMatricula daoMatricula = FactoriaIntegracion.getInstancia().generaDAOMatricula();
		DAOAula daoAula = FactoriaIntegracion.getInstancia().generaDAOAula();
		
		int idNuevaAula = daoAula.create(new TAula(235));
		seleccionarCamposUnicosPersonal(daoPersonal);
		int idEntrenador = daoPersonal.create(new TEntrenador("Pepe", ejDNI.toString(), ejCB.toString(), ejTFNO.toString(), 2300, 4, true), Evento.ALTA_ENTRENADOR);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
		LocalTime hora = LocalTime.parse("15:00", formatter);
		int idClase = daoClase.create(new TClase(hora, idNuevaAula, idEntrenador));
		daoMatricula.create(new TMatricula(idNuevoCliente, idClase, "Mañana"));
		
		boolean ok = saCliente.delete(idNuevoCliente);
		TDatosCompletosCliente cliente = saCliente.readById(idNuevoCliente);
		assertFalse(ok);
		assertTrue(cliente.getCliente().getEstado());
	}

	@Test
	public void testBajaClienteIncorrecto() {
		boolean ok = saCliente.delete(-1);
		assertFalse(ok);
	}
	
	private void seleccionarCamposUnicos() {
		clientes = saCliente.readAll();
		for (int i = 0; i < clientes.size(); i++) {
			for (int j = 0; j < clientes.size(); j++) {
				if(i != j) {
					if(clientes.get(j).getCuentaBancaria().equals(ejCB.toString()))
						++ejCB;
					if(clientes.get(j).getDni().equals(ejDNI.toString()))
						++ejDNI;
					if(clientes.get(j).getTelefono().equals(ejTFNO.toString()))
						++ejTFNO;
					if(clientes.get(j).getDomicilio().equals(ejDom.toString()))
						++ejDom;
					if(clientes.get(j).getCorreo().equals(ejMail.toString()))
						++ejMail;
				}
			}
		}
	}
	
	private void seleccionarCamposUnicosPersonal(DAOPersonal daoPersonal) {
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
