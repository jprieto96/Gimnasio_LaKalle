package integracion.matricula;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import integracion.aula.altaAula.TestIntegracionAltaAula;
import integracion.aula.bajaAula.TestIntegracionBajaAula;
import integracion.aula.listarAulas.TestIntegracionListarAulas;
import integracion.aula.modificarAula.TestIntegracionModificarAula;
import integracion.aula.mostrarDatosAula.TestIntegracionMostrarDatosAula;

@RunWith(Suite.class)
@SuiteClasses( { TestIntegracionAltaAula.class, TestIntegracionBajaAula.class,
					TestIntegracionModificarAula.class, TestIntegracionListarAulas.class,
						TestIntegracionMostrarDatosAula.class })
public class SuiteTestIntegracionMatricula {}
