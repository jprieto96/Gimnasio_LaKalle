package negocio.aula;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import negocio.aula.altaAula.TestNegocioAltaAula;
import negocio.aula.bajaAula.TestNegocioBajaAula;
import negocio.aula.listarAulas.TestNegocioListarAulas;
import negocio.aula.listarAulasNoDisponibles.TestNegocioListarAulasNoDisponibles;
import negocio.aula.modificarAula.TestNegocioModificarAula;
import negocio.aula.mostrarDatosAula.TestNegocioMostrarDatosAula;

@RunWith(Suite.class)
@SuiteClasses( { TestNegocioAltaAula.class, TestNegocioBajaAula.class, TestNegocioListarAulas.class,
	TestNegocioModificarAula.class, TestNegocioListarAulasNoDisponibles.class, TestNegocioMostrarDatosAula.class })
public class SuiteTestNegocioAula {}
