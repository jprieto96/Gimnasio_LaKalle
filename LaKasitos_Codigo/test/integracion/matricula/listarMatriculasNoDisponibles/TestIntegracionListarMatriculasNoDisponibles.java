package integracion.matricula.listarMatriculasNoDisponibles;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import integracion.matricula.DAOMatricula;
import integracion.factoria.FactoriaIntegracion;
import negocio.matricula.TMatricula;

public class TestIntegracionListarMatriculasNoDisponibles {

	private static DAOMatricula daoMatricula;
	
	@BeforeClass
	public static void before() {
		daoMatricula = FactoriaIntegracion.getInstancia().generaDAOMatricula();
	}

	@Test
	public void testListarMatriculasNoDisponiblesCorrecto() {
		List<TMatricula> matriculas = daoMatricula.readAllUnavalaible();
		for (TMatricula tMatricula : matriculas) {
			assertNotNull(tMatricula);
			assertFalse(tMatricula.getEstado());
		}
	}
}
