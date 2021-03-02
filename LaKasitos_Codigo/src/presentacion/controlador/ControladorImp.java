/**
 * 
 */
package presentacion.controlador;

import presentacion.Vista;
import presentacion.factoria.FactoriaPresentacion;

import java.util.List;
import negocio.aula.SA_Aula;
import negocio.aula.TAula;
import negocio.clase.SA_Clase;
import negocio.clase.TClase;
import negocio.cliente.SA_Cliente;
import negocio.cliente.TCliente;
import negocio.cliente.TDatosCompletosCliente;
import negocio.factoria.FactoriaNegocio;
import negocio.matricula.SAMatricula;
import negocio.matricula.TMatricula;
import negocio.personal.SAPersonal;
import negocio.personal.TPersonal;

public class ControladorImp extends Controlador {

	public void accion(int evento, Object object) {
		// begin-user-code
		Vista vista;
		TAula tAula;
		TClase tClase;
		TPersonal tPersonal;
		TCliente tCliente;
		TMatricula tMatricula;
		List<TAula> aulas;
		List<TPersonal> listaPersonal;
		List<TClase> clases;
		List<TCliente> clientes;
		List<TMatricula> matriculas;
		int res = -1;
		SA_Aula saAula = FactoriaNegocio.getInstancia().generaSAAula();
		SAPersonal saPersonal = FactoriaNegocio.getInstancia().generaSAPersonal();
		SA_Clase saClase = FactoriaNegocio.getInstancia().generaSAClase();
		SA_Cliente saCliente = FactoriaNegocio.getInstancia().generaSACliente();
		SAMatricula saMatricula = FactoriaNegocio.getInstancia().generaSAMatricula();
		switch (evento) {
		case Evento.ALTA_AULA:
			vista = FactoriaPresentacion.getInstancia().generaVistaAltaAula();
			tAula = (TAula) object;
			res = saAula.create(tAula);
			if (res > 0)
				vista.actualizar(Evento.ALTA_AULA_OK, new Integer(res));
			else
				vista.actualizar(Evento.ALTA_AULA_ERROR, null);
			break;
		case Evento.BAJA_AULA:
			vista = FactoriaPresentacion.getInstancia().generaVistaBajaAula();
			boolean ok = saAula.delete((Integer)object);
			if (ok)
				vista.actualizar(Evento.BAJA_AULA_OK, (Integer)object);
			else
				vista.actualizar(Evento.BAJA_AULA_ERROR, null);
			break;
		case Evento.MODIFICAR_AULA:
			vista = FactoriaPresentacion.getInstancia().generaVistaModificarAula();
			tAula = (TAula) object;
			if (saAula.update(tAula))
				vista.actualizar(Evento.MODIFICAR_AULA_OK, new Integer(tAula.getId()));
			else
				vista.actualizar(Evento.MODIFICAR_AULA_ERROR, null);
			break;
		case Evento.LISTAR_AULAS:
			vista = FactoriaPresentacion.getInstancia().generaVistaListarAulas();
			aulas = saAula.readAll();
			vista.actualizar(Evento.LISTAR_AULAS_OK, aulas);
			break;
		case Evento.MOSTRAR_AULA_POR_ID:
			vista = FactoriaPresentacion.getInstancia().generaVistaMostrarDatosPorAula();
			tAula = (TAula) object;
			TAula aula = saAula.readById(tAula.getId());
			if (aula != null)
				vista.actualizar(Evento.MOSTRAR_AULA_POR_ID_OK, aula);
			else
				vista.actualizar(Evento.MOSTRAR_AULA_POR_ID_ERROR, null);
			break;
		case Evento.LISTAR_AULAS_NO_DISPONIBLES:
			vista = FactoriaPresentacion.getInstancia().generaVistaAltaAula();
			aulas = saAula.readAllUnavailable();
			vista.actualizar(Evento.LISTAR_AULAS_NO_DISPONIBLES_OK, aulas);
			break;
		case Evento.ALTA_ENTRENADOR:
			vista = FactoriaPresentacion.getInstancia().generaVistaAltaPersonal();
			tPersonal = (TPersonal) object;
			res = saPersonal.create(tPersonal, evento);
			if (res > 0)
				vista.actualizar(Evento.ALTA_ENTRENADOR_OK, new Integer(res));
			else
				vista.actualizar(Evento.ALTA_ENTRENADOR_ERROR, null);
			break;
		case Evento.ALTA_MANTENIMIENTO:
			vista = FactoriaPresentacion.getInstancia().generaVistaAltaPersonal();
			tPersonal = (TPersonal) object;
			res = saPersonal.create(tPersonal, evento);
			if (res > 0)
				vista.actualizar(Evento.ALTA_MANTENIMIENTO_OK, new Integer(res));
			else
				vista.actualizar(Evento.ALTA_MANTENIMIENTO_ERROR, null);
			break;
		case Evento.LISTAR_PERSONAL_NO_DISPONIBLE:
			vista = FactoriaPresentacion.getInstancia().generaVistaAltaPersonal();
			listaPersonal = saPersonal.readAllUnavalaible();
			if (!listaPersonal.isEmpty())
				vista.actualizar(Evento.LISTAR_PERSONAL_NO_DISPONIBLE_OK, listaPersonal);
			else
				vista.actualizar(Evento.LISTAR_PERSONAL_NO_DISPONIBLE_ERROR, listaPersonal);
			break;
		case Evento.BAJA_PERSONAL:
			vista = FactoriaPresentacion.getInstancia().generaVistaBajaPersonal();
			Integer idPersonal = (Integer) object;
			ok = saPersonal.delete(idPersonal);
			if (ok)
				vista.actualizar(Evento.BAJA_PERSONAL_OK, idPersonal);
			else
				vista.actualizar(Evento.BAJA_PERSONAL_ERROR, null);
			break;
		case Evento.LISTAR_PERSONAL:
			vista = FactoriaPresentacion.getInstancia().generaVistaListarPersonal();
			listaPersonal = saPersonal.readAll();
			if (!listaPersonal.isEmpty())
				vista.actualizar(Evento.LISTAR_PERSONAL_OK, listaPersonal);
			else
				vista.actualizar(Evento.LISTAR_PERSONAL_ERROR, listaPersonal);
			break;
		case Evento.MOSTRAR_PERSONAL_POR_ID:
			vista = FactoriaPresentacion.getInstancia().generaVistaMostrarDatosPorPersonal();
			tPersonal = (TPersonal) object;
			TPersonal personal = saPersonal.readById(tPersonal.getIdPersonal());
			if (personal != null)
				vista.actualizar(Evento.MOSTRAR_PERSONAL_POR_ID_OK, personal);
			else
				vista.actualizar(Evento.MOSTRAR_PERSONAL_POR_ID_ERROR, null);
			break;
		case Evento.MODIFICAR_ENTRENADOR:
			vista = FactoriaPresentacion.getInstancia().generaVistaModificarPersonal();
			tPersonal = (TPersonal) object;
			if (saPersonal.update(tPersonal, evento))
				vista.actualizar(Evento.MODIFICAR_ENTRENADOR_OK, null);
			else
				vista.actualizar(Evento.MODIFICAR_ENTRENADOR_ERROR, null);
			break;
		case Evento.MODIFICAR_MANTENIMIENTO:
			vista = FactoriaPresentacion.getInstancia().generaVistaModificarPersonal();
			tPersonal = (TPersonal) object;
			if (saPersonal.update(tPersonal, evento))
				vista.actualizar(Evento.MODIFICAR_MANTENIMIENTO_OK, null);
			else
				vista.actualizar(Evento.MODIFICAR_MANTENIMIENTO_ERROR, null);
			break;
		case Evento.ALTA_CLASE:
			vista = FactoriaPresentacion.getInstancia().generaVistaAltaClase();
			tClase = (TClase) object;
			res = saClase.create(tClase);
			if (res > 0)
				vista.actualizar(Evento.ALTA_CLASE_OK, new Integer(res));
			else
				vista.actualizar(Evento.ALTA_CLASE_ERROR, null);
			break;
		case Evento.LISTAR_CLASES_NO_DISPONIBLES:
			vista = FactoriaPresentacion.getInstancia().generaVistaAltaClase();
			clases = saClase.readAllUnavalaible();
			vista.actualizar(Evento.LISTAR_CLASES_NO_DISPONIBLES_OK, clases);
			break;
		case Evento.BAJA_CLASE:
			vista = FactoriaPresentacion.getInstancia().generaVistaBajaClase();
			tClase = (TClase) object;
			ok = saClase.delete(tClase.getId());
			if (ok)
				vista.actualizar(Evento.BAJA_CLASE_OK, new Integer(tClase.getId()));
			else
				vista.actualizar(Evento.BAJA_CLASE_ERROR, null);
			break;
		case Evento.LISTAR_CLASES:
			vista = FactoriaPresentacion.getInstancia().generaVistaListarClases();
			clases = saClase.readAll();
			vista.actualizar(Evento.LISTAR_CLASES_OK, clases);
			break;
		case Evento.MODIFICAR_CLASE:
			vista = FactoriaPresentacion.getInstancia().generaVistaModificarClase();
			tClase = (TClase) object;
			if (saClase.update(tClase))
				vista.actualizar(Evento.MODIFICAR_CLASE_OK, new Integer(tClase.getId()));
			else
				vista.actualizar(Evento.MODIFICAR_CLASE_ERROR, null);
			break;
		case Evento.MOSTRAR_CLASE_POR_ID:
			vista = FactoriaPresentacion.getInstancia().generaVistaMostrarDatosClase();
			tClase = (TClase) object;
			TClase clase = saClase.readById(tClase.getId());
			if (clase != null)
				vista.actualizar(Evento.MOSTRAR_CLASE_POR_ID_OK, clase);
			else
				vista.actualizar(Evento.MOSTRAR_CLASE_POR_ID_ERROR, null);
			break;
		case Evento.MOSTRAR_CLASE_POR_ENTRENADOR:
			vista = FactoriaPresentacion.getInstancia().generaVistaMostrarClasesPorEntrenador();
			tClase = (TClase) object;
			clases = saClase.mostrarClasesPorEntrenador(tClase.getIdEntrenador());
			if (!clases.isEmpty())
				vista.actualizar(Evento.MOSTRAR_CLASE_POR_ENTRENADOR_OK, clases);
			else
				vista.actualizar(Evento.MOSTRAR_CLASE_POR_ENTRENADOR_ERROR, new Integer(tClase.getIdEntrenador()));
			break;
		case Evento.MOSTRAR_CLASE_POR_AULA:
			vista = FactoriaPresentacion.getInstancia().generaVistaMostrarClasesPorAula();
			tClase = (TClase) object;
			clases = saClase.mostrarClasesPorAula(tClase.getIdAula());
			if (!clases.isEmpty())
				vista.actualizar(Evento.MOSTRAR_CLASE_POR_AULA_OK, clases);
			else
				vista.actualizar(Evento.MOSTRAR_CLASE_POR_AULA_ERROR, new Integer(tClase.getIdAula()));
			break;
		case Evento.ASIGNAR_CLASE_A_AULA:
			vista = FactoriaPresentacion.getInstancia().generaVistaAsignarClaseAAula();
			tClase = (TClase) object;
			if (saClase.asignarClaseAAula(tClase))
				vista.actualizar(Evento.ASIGNAR_CLASE_A_AULA_OK, new Integer(tClase.getIdAula()));
			else
				vista.actualizar(Evento.ASIGNAR_CLASE_A_AULA_ERROR, null);
			break;
		case Evento.ASIGNAR_CLASE_A_ENTRENADOR:
			vista = FactoriaPresentacion.getInstancia().generaVistaAsignarClaseAEntrenador();
			tClase = (TClase) object;
			if (saClase.asignarClaseAEntrenador(tClase))
				vista.actualizar(Evento.ASIGNAR_CLASE_A_ENTRENADOR_OK, new Integer(tClase.getIdEntrenador()));
			else
				vista.actualizar(Evento.ASIGNAR_CLASE_A_ENTRENADOR_ERROR, null);
			break;
		case Evento.ALTA_CLIENTE:
			vista = FactoriaPresentacion.getInstancia().generaVistaAltaCliente();
			tCliente = (TCliente) object;
			res = saCliente.create(tCliente);
			if (res > 0)
				vista.actualizar(Evento.ALTA_CLIENTE_OK, new Integer(res));
			else
				vista.actualizar(Evento.ALTA_CLIENTE_ERROR, null);
			break;
		case Evento.BAJA_CLIENTE:
			vista = FactoriaPresentacion.getInstancia().generaVistaBajaCliente();
			ok = saCliente.delete((Integer) object);
			if (ok)
				vista.actualizar(Evento.BAJA_CLIENTE_OK, (Integer) object);
			else
				vista.actualizar(Evento.BAJA_CLIENTE_ERROR, null);
			break;
		case Evento.MODIFICAR_CLIENTE:
			vista = FactoriaPresentacion.getInstancia().generaVistaModificarCliente();
			tCliente = (TCliente) object;
			if (saCliente.update(tCliente))
				vista.actualizar(Evento.MODIFICAR_CLIENTE_OK, new Integer(tCliente.getId()));
			else
				vista.actualizar(Evento.MODIFICAR_CLIENTE_ERROR, null);
			break;
		case Evento.LISTAR_CLIENTES:
			vista = FactoriaPresentacion.getInstancia().generaVistaListarClientes();
			clientes = saCliente.readAll();
			vista.actualizar(Evento.LISTAR_CLIENTES_OK, clientes);
			break;
		case Evento.MOSTRAR_CLIENTE_POR_ID:
			vista = FactoriaPresentacion.getInstancia().generaVistaMostrarDatosCliente();
			TDatosCompletosCliente cliente = saCliente.readById((Integer) object);
			if (cliente != null)
				vista.actualizar(Evento.MOSTRAR_CLIENTE_POR_ID_OK, cliente);
			else
				vista.actualizar(Evento.MOSTRAR_CLIENTE_POR_ID_ERROR, null);
			break;
		case Evento.LISTAR_CLIENTES_NO_DISPONIBLES:
			vista = FactoriaPresentacion.getInstancia().generaVistaAltaCliente();
			clientes = saCliente.readAllUnavailable();
			vista.actualizar(Evento.LISTAR_CLIENTES_NO_DISPONIBLES_OK, clientes);
			break;
		case Evento.ALTA_MATRICULA:
			vista = FactoriaPresentacion.getInstancia().generaVistaAltaMatricula();
			tMatricula = (TMatricula) object;
			res = saMatricula.create(tMatricula);
			if (res > 0)
				vista.actualizar(Evento.ALTA_MATRICULA_OK, new Integer(res));
			else
				vista.actualizar(Evento.ALTA_MATRICULA_ERROR, null);
			break;
		case Evento.BAJA_MATRICULA:
			vista = FactoriaPresentacion.getInstancia().generaVistaBajaMatricula();
			Integer id = (Integer) object;
			if (saMatricula.delete(id))
				vista.actualizar(Evento.BAJA_MATRICULA_OK, id);
			else
				vista.actualizar(Evento.BAJA_MATRICULA_ERROR, null);
			break;
		case Evento.MODIFICAR_MATRICULA:
			vista = FactoriaPresentacion.getInstancia().generaVistaModificarMatricula();
			tMatricula = (TMatricula) object;
			if (saMatricula.update(tMatricula))
				vista.actualizar(Evento.MODIFICAR_MATRICULA_OK, new Integer(tMatricula.getId()));
			else
				vista.actualizar(Evento.MODIFICAR_MATRICULA_ERROR, null);
			break;
		case Evento.LISTAR_MATRICULAS:
			vista = FactoriaPresentacion.getInstancia().generaVistaListarMatriculas();
			matriculas = saMatricula.readAll();
			if(!matriculas.isEmpty())
				vista.actualizar(Evento.LISTAR_MATRICULAS_OK, matriculas);
			else
				vista.actualizar(Evento.LISTAR_MATRICULAS_ERROR, null);
			break;
		case Evento.MOSTRAR_MATRICULA_POR_ID_CLASE:
			vista = FactoriaPresentacion.getInstancia().generaVistaListarMatriculasPorClase();
			matriculas = saMatricula.listarMatriculasPorClase((Integer) object);
			if(!matriculas.isEmpty())
				vista.actualizar(Evento.MOSTRAR_MATRICULA_POR_ID_CLASE_OK, matriculas);
			else
				vista.actualizar(Evento.MOSTRAR_MATRICULA_POR_ID_CLASE_ERROR, (Integer) object);
			break;
		case Evento.MOSTRAR_MATRICULA_POR_ID_CLIENTE:
			vista = FactoriaPresentacion.getInstancia().generaVistaListarMatriculasPorCliente();
			matriculas = saMatricula.listarMatriculasPorCliente((Integer) object);
			if(!matriculas.isEmpty())
				vista.actualizar(Evento.MOSTRAR_MATRICULA_POR_ID_CLIENTE_OK, matriculas);
			else
				vista.actualizar(Evento.MOSTRAR_MATRICULA_POR_ID_CLIENTE_ERROR, (Integer) object);
			break;
		case Evento.LISTAR_MATRICULAS_NO_DISPONIBLES:
			vista = FactoriaPresentacion.getInstancia().generaVistaAltaMatricula();
			matriculas = saMatricula.readAllUnavailable();
			if(!matriculas.isEmpty())
				vista.actualizar(Evento.LISTAR_MATRICULAS_NO_DISPONIBLES_OK, matriculas);
			else
				vista.actualizar(Evento.LISTAR_MATRICULAS_NO_DISPONIBLES_ERROR, null);
			break;
		default:
			break;
		}
		// end-user-code
	}
}