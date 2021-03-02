package integracion.matricula.listarMatriculas;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import integracion.matricula.DAOMatricula;
import integracion.factoria.FactoriaIntegracion;
import negocio.matricula.TMatricula;

public class TestIntegracionListarMatriculas {
	private static DAOMatricula daoMatricula;
	
	@BeforeClass
	public static void before() {
		daoMatricula = FactoriaIntegracion.getInstancia().generaDAOMatricula();
	}

	@Test
	public void testListarMatriculasCorrecto() {
		List<TMatricula> matriculas = daoMatricula.readAll();
		for (TMatricula tMatricula : matriculas) {
			assertNotNull(tMatricula);
		}
	}
}
