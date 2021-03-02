package integracion.cliente;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import integracion.cliente.altaCliente.TestIntegracionAltaCliente;
import integracion.cliente.bajaCliente.TestIntegracionBajaCliente;
import integracion.cliente.listarClientes.TestIntegracionListarClientes;
import integracion.cliente.listarClientesNoDisponibles.TestIntegracionListarClientesNoDisponibles;
import integracion.cliente.modificarCliente.TestIntegracionModificarCliente;
import integracion.cliente.mostrarDatosCliente.TestIntegracionMostrarDatosCliente;
import integracion.cliente.reactivarCliente.TestIntegracionReactivarCliente;

@RunWith(Suite.class)
@SuiteClasses( { TestIntegracionAltaCliente.class, TestIntegracionBajaCliente.class,
					TestIntegracionModificarCliente.class, TestIntegracionListarClientes.class,
						TestIntegracionMostrarDatosCliente.class, TestIntegracionListarClientesNoDisponibles.class, 
							TestIntegracionReactivarCliente.class })
public class SuiteTestIntegracionCliente {}
