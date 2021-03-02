package negocio.clase.bajaClase;

import static org.junit.Assert.*;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import integracion.aula.DAOAula;
import integracion.cliente.DAOCliente;
import integracion.factoria.FactoriaIntegracion;
import integracion.matricula.DAOMatricula;
import integracion.personal.DAOPersonal;
import negocio.aula.TAula;
import negocio.clase.SA_Clase;
import negocio.clase.TClase;
import negocio.cliente.TCliente;
import negocio.factoria.FactoriaNegocio;
import negocio.matricula.TMatricula;
import negocio.personal.TEntrenador;
import negocio.personal.TPersonal;
import presentacion.controlador.Evento;
import presentacion.matricula.Nivel;

public class TestNegocioBajaClase {
	
	private static SA_Clase saClase;
	private int idNuevaClase;
	private Integer ejDNI = 1, ejCB = 1, ejTFNO = 1, ejMail = 1; // campos UNIQUE en BBDD
	private static LocalTime hora;
	
	@Before
	public void before() {
		saClase = FactoriaNegocio.getInstancia().generaSAClase();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
		hora = LocalTime.parse("15:00", formatter);
		DAOAula daoAula = FactoriaIntegracion.getInstancia().generaDAOAula();
		DAOPersonal daoPersonal = FactoriaIntegracion.getInstancia().generaDAOPersonal();
		int idAula = daoAula.create(new TAula(230));
		seleccionarCamposUnicos(daoPersonal);
		int idEntrenador = daoPersonal.create(new TEntrenador("Pepe", ejDNI.toString(), ejCB.toString(), ejTFNO.toString(), 2300, 4, true), Evento.ALTA_ENTRENADOR);
		idNuevaClase = saClase.create(new TClase(hora, idAula, idEntrenador));
	}

	@Test
	public void testBajaClaseCorrecto() {
		boolean ok = saClase.delete(idNuevaClase);
		TClase c = saClase.readById(idNuevaClase);
		assertTrue(ok);
		assertFalse(c.getEstado());
	}
	
	@Test
	public void testBajaClaseEnLaQueHayClinetesMatriculadosIncorrecto() {
		//Matriculamos a un cliente en la clase creada
		DAOCliente daoCliente = FactoriaIntegracion.getInstancia().generaDAOCliente();
		DAOMatricula daoMatricula = FactoriaIntegracion.getInstancia().generaDAOMatricula();
		seleccionarCamposUnicos(daoCliente);
		int idCliente = daoCliente.create(new TCliente("Luis", ejDNI.toString(), ejCB.toString(), ejTFNO.toString(), "C/Palomas 23", ejMail.toString()));
		daoMatricula.create(new TMatricula(idCliente, idNuevaClase, Nivel.MEDIO.toString()));
		
		//Intentamos dar de baja la clase pero no nos va a dejar por que hay matriculas en ella
		boolean ok = saClase.delete(idNuevaClase);
		TClase c = saClase.readById(idNuevaClase);
		assertFalse(ok);
		assertTrue(c.getEstado());
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
	
	private void seleccionarCamposUnicos(DAOCliente daoCliente) {
		List<TCliente> clientes = daoCliente.readAll();
		for (int i = 0; i < clientes.size(); i++) {
			for (int j = 0; j < clientes.size(); j++) {
				if(i != j) {
					if(clientes.get(j).getCuentaBancaria().equals(ejCB.toString()))
						++ejCB;
					if(clientes.get(j).getDni().equals(ejDNI.toString()))
						++ejDNI;
					if(clientes.get(j).getTelefono().equals(ejTFNO.toString()))
						++ejTFNO;
					if(clientes.get(j).getCorreo().equals(ejMail.toString()))
						++ejMail;
				}
			}
		}
	}

}
