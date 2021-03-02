package integracion.personal;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import integracion.personal.altaPersonal.TestIntegracionAltaPersonal;
import integracion.personal.bajaPersonal.TestIntegracionBajaPersonal;
import integracion.personal.entrenadoresImpartiendoClases.TestIntegracionListarEntrenadoresImpartiendoClases;
import integracion.personal.listarPersonal.TestIntegracionListarPersonal;
import integracion.personal.listarPersonalNoDisponible.TestIntegracionListarPersonalNoDisponible;
import integracion.personal.modificarPersonal.TestIntegracionModificarPersonal;
import integracion.personal.mostrarDatosEntrenador.TestIntegracionMostrarDatosEntrenador;
import integracion.personal.mostrarDatosPersonal.TestIntegracionMostrarDatosPersonal;
import integracion.personal.reactivarPersonal.TestIntegracionReactivarPersonal;

@RunWith(Suite.class)
@SuiteClasses( { TestIntegracionAltaPersonal.class, TestIntegracionBajaPersonal.class,
	TestIntegracionModificarPersonal.class, TestIntegracionListarEntrenadoresImpartiendoClases.class, 
	TestIntegracionListarPersonal.class, TestIntegracionListarPersonalNoDisponible.class,
	TestIntegracionMostrarDatosPersonal.class, TestIntegracionMostrarDatosEntrenador.class, 
	TestIntegracionReactivarPersonal.class})
public class SuiteTestIntegracionPersonal {}
