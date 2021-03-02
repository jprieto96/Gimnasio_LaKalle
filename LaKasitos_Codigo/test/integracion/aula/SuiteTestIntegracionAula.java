package integracion.aula;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import integracion.aula.altaAula.TestIntegracionAltaAula;
import integracion.aula.aulasImpartiendoClases.TestIntegracionAulasImpartiendoClases;
import integracion.aula.bajaAula.TestIntegracionBajaAula;
import integracion.aula.listarAulas.TestIntegracionListarAulas;
import integracion.aula.listarAulasNoDisponibles.TestIntegracionListarAulasNoDisponibles;
import integracion.aula.modificarAula.TestIntegracionModificarAula;
import integracion.aula.mostrarDatosAula.TestIntegracionMostrarDatosAula;
import integracion.aula.reactivarAula.TestIntegracionReactivarAula;

@RunWith(Suite.class)
@SuiteClasses( { TestIntegracionAltaAula.class, TestIntegracionBajaAula.class,
					TestIntegracionModificarAula.class, TestIntegracionListarAulas.class,
						TestIntegracionMostrarDatosAula.class, TestIntegracionReactivarAula.class,
							TestIntegracionAulasImpartiendoClases.class, TestIntegracionListarAulasNoDisponibles.class })
public class SuiteTestIntegracionAula {}
