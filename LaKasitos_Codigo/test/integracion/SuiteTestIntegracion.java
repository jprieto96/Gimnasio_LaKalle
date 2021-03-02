package integracion;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import integracion.aula.SuiteTestIntegracionAula;
import integracion.clase.SuiteTestIntegracionClase;
import integracion.cliente.SuiteTestIntegracionCliente;
import integracion.matricula.SuiteTestIntegracionMatricula;
import integracion.personal.SuiteTestIntegracionPersonal;

@RunWith(Suite.class)
@SuiteClasses( { SuiteTestIntegracionAula.class, SuiteTestIntegracionClase.class,
	SuiteTestIntegracionCliente.class, SuiteTestIntegracionMatricula.class, SuiteTestIntegracionPersonal.class })
public class SuiteTestIntegracion {}
