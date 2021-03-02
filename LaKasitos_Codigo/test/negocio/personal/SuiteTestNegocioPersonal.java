package negocio.personal;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import negocio.personal.altaPersonal.TestNegocioAltaPersonal;
import negocio.personal.bajaPersonal.TestNegocioBajaPersonal;
import negocio.personal.listarPersonal.TestNegocioListarPersonal;
import negocio.personal.listarPersonalNoDisponible.TestNegocioListarPersonalNoDisponible;
import negocio.personal.modificarPersonal.TestNegocioModificarPersonal;
import negocio.personal.mostrarDatosPersonal.TestNegocioMostrarDatosPersonal;

@RunWith(Suite.class)
@SuiteClasses( { TestNegocioAltaPersonal.class, TestNegocioBajaPersonal.class, TestNegocioListarPersonal.class,
	TestNegocioModificarPersonal.class, TestNegocioListarPersonalNoDisponible.class, TestNegocioMostrarDatosPersonal.class })
public class SuiteTestNegocioPersonal {}
