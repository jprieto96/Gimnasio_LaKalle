package negocio.clase;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import negocio.clase.altaClase.TestNegocioAltaClase;
import negocio.clase.asignarClaseAAula.TestNegocioAsignarClaseAAula;
import negocio.clase.asignarClaseAEntrenador.TestNegocioAsignarClaseAEntrenador;
import negocio.clase.bajaClase.TestNegocioBajaClase;
import negocio.clase.listarClases.TestNegocioListarClases;
import negocio.clase.listarClasesNoDisponibles.TestNegocioListarClasesNoDisponibles;
import negocio.clase.modificarClase.TestNegocioModificarClase;
import negocio.clase.mostrarClasesPorAula.TestNegocioMostrarClasesPorAula;
import negocio.clase.mostrarClasesPorEntrenador.TestNegocioMostrarClasesPorEntrenador;
import negocio.clase.mostrarDatosClase.TestNegocioMostrarDatosClase;

@RunWith(Suite.class)
@SuiteClasses( { TestNegocioAltaClase.class, TestNegocioBajaClase.class, TestNegocioListarClases.class,
	TestNegocioModificarClase.class, TestNegocioListarClasesNoDisponibles.class, TestNegocioMostrarDatosClase.class,
	TestNegocioAsignarClaseAAula.class, TestNegocioAsignarClaseAEntrenador.class, TestNegocioMostrarClasesPorAula.class,
	TestNegocioMostrarClasesPorEntrenador.class })
public class SuiteTestNegocioClase {}
