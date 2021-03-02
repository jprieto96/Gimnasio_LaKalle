/**
 * 
 */
package presentacion.factoria;

import presentacion.Vista;

public abstract class FactoriaPresentacion {

	private static FactoriaPresentacion instancia;

	public static FactoriaPresentacion getInstancia() {
		// begin-user-code
		if (instancia == null)
			instancia = new FactoriaPresentacionImp();
		return instancia;
		// end-user-code
	}

	public abstract Vista generaVistaMain();

	public abstract Vista generaVistaAltaClase();

	public abstract Vista generaVistaBajaClase();

	public abstract Vista generaVistaModificarClase();

	public abstract Vista generaVistaListarClases();

	public abstract Vista generaVistaMostrarDatosClase();

	public abstract Vista generaVistaMostrarClasesPorAula();

	public abstract Vista generaVistaMostrarClasesPorEntrenador();

	public abstract Vista generaVistaAsignarClaseAAula();

	public abstract Vista generaVistaAsignarClaseAEntrenador();

	public abstract Vista generaVistaAula();

	public abstract Vista generaVistaAltaAula();

	public abstract Vista generaVistaBajaAula();

	public abstract Vista generaVistaModificarAula();

	public abstract Vista generaVistaListarAulas();

	public abstract Vista generaVistaMostrarDatosPorAula();

	public abstract Vista generaVistaPersonal();

	public abstract Vista generaVistaCliente();

	public abstract Vista generaVistaClase();

	public abstract Vista generaVistaMatricula();

	public abstract Vista generaVistaAltaPersonal();

	public abstract Vista generaVistaBajaPersonal();

	public abstract Vista generaVistaModificarPersonal();

	public abstract Vista generaVistaListarPersonal();

	public abstract Vista generaVistaMostrarDatosPorPersonal();

	public abstract Vista generaVistaAltaMatricula();

	public abstract Vista generaVistaBajaMatricula();

	public abstract Vista generaVistaModificarMatricula();

	public abstract Vista generaVistaListarMatriculasPorClase();

	public abstract Vista generaVistaListarMatriculasPorCliente();

	public abstract Vista generaVistaListarMatriculas();

	public abstract Vista generaVistaAltaCliente();

	public abstract Vista generaVistaBajaCliente();

	public abstract Vista generaVistaListarClientes();

	public abstract Vista generaVistaModificarCliente();

	public abstract Vista generaVistaMostrarDatosCliente();
}