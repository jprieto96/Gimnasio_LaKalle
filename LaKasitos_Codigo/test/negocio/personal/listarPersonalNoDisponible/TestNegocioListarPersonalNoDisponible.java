package negocio.personal.listarPersonalNoDisponible;

import static org.junit.Assert.*;

import java.util.List;
import org.junit.BeforeClass;
import org.junit.Test;
import negocio.factoria.FactoriaNegocio;
import negocio.personal.SAPersonal;
import negocio.personal.TPersonal;

public class TestNegocioListarPersonalNoDisponible {
	
	private static SAPersonal saPersonal;
	
	@BeforeClass
	public static void before() {
		saPersonal = FactoriaNegocio.getInstancia().generaSAPersonal();
	}

	@Test
	public void testListarAulasNoDisponiblesCorrecto() {
		List<TPersonal> listaPersonal = saPersonal.readAllUnavalaible();
		for (TPersonal p : listaPersonal) {
			assertNotNull(p);
			assertFalse(p.getEstado());
		}
	}
	
}
