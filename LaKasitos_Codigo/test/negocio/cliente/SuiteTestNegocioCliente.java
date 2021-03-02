package negocio.cliente;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import negocio.cliente.altaCliente.TestNegocioAltaCliente;
import negocio.cliente.bajaCliente.TestNegocioBajaCliente;
import negocio.cliente.listarClientes.TestNegocioListarClientes;
import negocio.cliente.listarClientesNoDisponibles.TestNegocioListarClientesNoDisponibles;
import negocio.cliente.modificarCliente.TestNegocioModificarCliente;
import negocio.cliente.mostrarDatosCliente.TestNegocioMostrarDatosCliente;

@RunWith(Suite.class)
@SuiteClasses( { TestNegocioAltaCliente.class, TestNegocioBajaCliente.class, TestNegocioListarClientes.class,
	TestNegocioModificarCliente.class, TestNegocioListarClientesNoDisponibles.class, TestNegocioMostrarDatosCliente.class })
public class SuiteTestNegocioCliente {}
