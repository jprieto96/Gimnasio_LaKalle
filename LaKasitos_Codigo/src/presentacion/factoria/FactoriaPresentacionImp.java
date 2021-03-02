package presentacion.factoria;

import presentacion.Vista;

import presentacion.VistaMain;
import presentacion.aula.VistaAula;
import presentacion.aula.altaAula.VistaAltaAula;
import presentacion.aula.bajaAula.VistaBajaAula;
import presentacion.aula.listarAulas.VistaListarAulas;
import presentacion.aula.modificarAula.VistaModificarAula;
import presentacion.aula.mostrarDatosAula.VistaMostrarDatosAula;
import presentacion.clase.VistaClase;
import presentacion.clase.altaClase.VistaAltaClase;
import presentacion.clase.asignarClaseAAula.VistaAsignarClaseAAula;
import presentacion.clase.asignarClaseAEntrenador.VistaAsignarClaseAEntrenador;
import presentacion.clase.bajaClase.VistaBajaClase;
import presentacion.clase.listarClases.VistaListarClases;
import presentacion.clase.modificarClase.VistaModifcarClase;
import presentacion.clase.mostrarClasePorAula.VistaMostrarClasePorAula;
import presentacion.clase.mostrarClasePorEntrenador.VistaMostrarClasePorEntrenador;
import presentacion.clase.mostrarDatosClase.VistaMostrarDatosClase;
import presentacion.cliente.VistaCliente;
import presentacion.cliente.altaClientes.VistaAltaCliente;
import presentacion.cliente.bajaClientes.VistaBajaCliente;
import presentacion.cliente.listarClientes.VistaListarClientes;
import presentacion.cliente.modificarCliente.VistaModificarCliente;
import presentacion.cliente.mostrarDatosCliente.VistaMostrarDatosCliente;
import presentacion.matricula.VistaMatricula;
import presentacion.matricula.altaMatricula.VistaAltaMatricula;
import presentacion.matricula.bajaMatricula.VistaBajaMatricula;
import presentacion.matricula.listarMatriculas.VistaListarMatriculas;
import presentacion.matricula.modificarMatricula.VistaModificarMatricula;
import presentacion.matricula.mostrarMatriculasPorClase.VistaMostrarMatriculasPorClase;
import presentacion.matricula.mostrarMatriculasPorCliente.VistaMostrarMatriculasPorCliente;
import presentacion.personal.VistaPersonal;
import presentacion.personal.altaPersonal.VistaAltaPersonal;
import presentacion.personal.bajaPersonal.VistaBajaPersonal;
import presentacion.personal.listarPersonal.VistaListarPersonal;
import presentacion.personal.modificarPersonal.VistaModificarPersonal;
import presentacion.personal.mostrarDatosPersonal.VistaMostrarDatosPersonal;

public class FactoriaPresentacionImp extends FactoriaPresentacion {

	@Override
	public Vista generaVistaMain() {
		// begin-user-code
		return new VistaMain();
		// end-user-code
	}

	@Override
	public Vista generaVistaAula() {
		// begin-user-code
		return new VistaAula();
		// end-user-code
	}

	@Override
	public Vista generaVistaAltaAula() {
		// begin-user-code
		return new VistaAltaAula();
		// end-user-code
	}

	@Override
	public Vista generaVistaBajaAula() {
		// begin-user-code
		return new VistaBajaAula();
		// end-user-code
	}

	@Override
	public Vista generaVistaModificarAula() {
		// begin-user-code
		return new VistaModificarAula();
		// end-user-code
	}

	@Override
	public Vista generaVistaListarAulas() {
		// begin-user-code
		return new VistaListarAulas();
		// end-user-code
	}

	@Override
	public Vista generaVistaMostrarDatosPorAula() {
		// begin-user-code
		return new VistaMostrarDatosAula();
		// end-user-code
	}

	@Override
	public Vista generaVistaPersonal() {
		// begin-user-code
		return new VistaPersonal();
		// end-user-code
	}

	public Vista generaVistaCliente() {
		// begin-user-code
		return new VistaCliente();
		// end-user-code
	}

	public Vista generaVistaClase() {
		// begin-user-code
		return new VistaClase();
		// end-user-code
	}

	public Vista generaVistaMatricula() {
		// begin-user-code
		return new VistaMatricula();
		// end-user-code
	}

	@Override
	public Vista generaVistaAltaPersonal() {
		return new VistaAltaPersonal();
	}

	@Override
	public Vista generaVistaBajaPersonal() {
		return new VistaBajaPersonal();
	}

	@Override
	public Vista generaVistaModificarPersonal() {
		return new VistaModificarPersonal();
	}

	@Override
	public Vista generaVistaListarPersonal() {
		return new VistaListarPersonal();
	}

	@Override
	public Vista generaVistaMostrarDatosPorPersonal() {
		return new VistaMostrarDatosPersonal();
	}

	public Vista generaVistaAltaMatricula() {
		// begin-user-code
		return new VistaAltaMatricula();
		// end-user-code
	}

	public Vista generaVistaBajaMatricula() {
		// begin-user-code
		return new VistaBajaMatricula();
		// end-user-code
	}

	public Vista generaVistaModificarMatricula() {
		// begin-user-code
		return new VistaModificarMatricula();
		// end-user-code
	}

	public Vista generaVistaListarMatriculasPorClase() {
		// begin-user-code
		return new VistaMostrarMatriculasPorClase();
		// end-user-code
	}

	public Vista generaVistaListarMatriculasPorCliente() {
		// begin-user-code
		return new VistaMostrarMatriculasPorCliente();
		// end-user-code
	}

	public Vista generaVistaListarMatriculas() {
		// begin-user-code
		return new VistaListarMatriculas();
		// end-user-code
	}

	public Vista generaVistaAltaCliente() {
		// begin-user-code
		return new VistaAltaCliente();
		// end-user-code
	}

	public Vista generaVistaBajaCliente() {
		// begin-user-code
		return new VistaBajaCliente();
		// end-user-code
	}

	public Vista generaVistaListarClientes() {
		// begin-user-code
		return new VistaListarClientes();
		// end-user-code
	}

	public Vista generaVistaModificarCliente() {
		// begin-user-code
		return new VistaModificarCliente();
		// end-user-code
	}

	public Vista generaVistaMostrarDatosCliente() {
		// begin-user-code
		return new VistaMostrarDatosCliente();
		// end-user-code
	}

	@Override
	public Vista generaVistaAltaClase() {
		// begin-user-code
		return new VistaAltaClase();
		// end-user-code
	}

	@Override
	public Vista generaVistaBajaClase() {
		// begin-user-code
		return new VistaBajaClase();
		// end-user-code
	}

	@Override
	public Vista generaVistaModificarClase() {
		// begin-user-code
		return new VistaModifcarClase();
		// end-user-code
	}

	@Override
	public Vista generaVistaListarClases() {
		// begin-user-code
		return new VistaListarClases();
		// end-user-code
	}

	@Override
	public Vista generaVistaMostrarDatosClase() {
		// begin-user-code
		return new VistaMostrarDatosClase();
		// end-user-code
	}

	@Override
	public Vista generaVistaMostrarClasesPorAula() {
		// begin-user-code
		return new VistaMostrarClasePorAula();
		// end-user-code
	}

	@Override
	public Vista generaVistaMostrarClasesPorEntrenador() {
		// begin-user-code
		return new VistaMostrarClasePorEntrenador();
		// end-user-code
	}

	@Override
	public Vista generaVistaAsignarClaseAAula() {
		// begin-user-code
		return new VistaAsignarClaseAAula();
		// end-user-code
	}

	@Override
	public Vista generaVistaAsignarClaseAEntrenador() {
		return new VistaAsignarClaseAEntrenador();
	}
}