package negocio.personal.listarPersonal;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import negocio.factoria.FactoriaNegocio;
import negocio.personal.SAPersonal;
import negocio.personal.TPersonal;

public class TestNegocioListarPersonal {
	
	private static SAPersonal saPersonal;
	
	@BeforeClass
	public static void before() {
		saPersonal = FactoriaNegocio.getInstancia().generaSAPersonal();
	}

	@Test
	public void testListarAulasCorrecto() {
		List<TPersonal> listaPersonal = saPersonal.readAll();
		for (TPersonal p : listaPersonal) {
			assertNotNull(p);
		}
	}
	
}
