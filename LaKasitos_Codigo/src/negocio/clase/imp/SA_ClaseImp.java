/**
 * 
 */
package negocio.clase.imp;

import java.util.List;

import integracion.aula.DAOAula;
import integracion.clase.DAOClase;
import integracion.factoria.FactoriaIntegracion;
import integracion.matricula.DAOMatricula;
import integracion.personal.DAOPersonal;
import negocio.aula.TAula;
import negocio.clase.SA_Clase;
import negocio.clase.TClase;
import negocio.matricula.TMatricula;
import negocio.personal.TEntrenador;

public class SA_ClaseImp implements SA_Clase {

	public int create(TClase tClase) {
		int id = -1;
		if (tClase != null) {
			DAOClase daoClase = FactoriaIntegracion.getInstancia().generaDAOClase();
			DAOAula daoAula = FactoriaIntegracion.getInstancia().generaDAOAula();
			DAOPersonal daoPersonal = FactoriaIntegracion.getInstancia().generaDAOPersonal();
			boolean aulaDisponible = false, entrenadorDisponible = false;
			TClase clase = daoClase.readById(tClase.getId());
			TAula aula = daoAula.readById(tClase.getIdAula());
			TEntrenador entrenador = daoPersonal.readEntrenadorById(tClase.getIdEntrenador());
			List<TClase> clases = daoClase.mostrarClasesPorAula(tClase.getIdAula());

			// Comprobacion de que el aula este activa y que no hay otra clase a
			// la misma hora en el mismo aula para que se pueda dar de alta
			if (aula != null) {
				aulaDisponible = aula.getEstado();
				for (TClase c : clases) {
					if (seImpartenALaMismaHoraEnLaMismaAula(c, tClase))
						aulaDisponible = false;
				}
			}

			// comprobar tambien que no supera las clases al dia maximas
			// para el entrenador y que este activo el entrenador
			if (entrenador != null) {
				int clasesEntrenador = daoClase.clasesAlDiaPorEntrenador(entrenador.getId_entrenador());
				entrenadorDisponible = entrenador.getEstado() && clasesEntrenador + 1 <= entrenador.getClases_dia();
			}

			if (aulaDisponible && entrenadorDisponible) {
				if (clase == null) {
					id = daoClase.create(tClase);
				} else {
					id = tClase.getId();
					daoClase.reactivate(id);
				}
			}
		}
		return id;
	}

	private boolean seImpartenALaMismaHoraEnLaMismaAula(TClase clase1, TClase clase2) {
		return clase1.getEstado() && clase1.getId() != clase2.getId() && clase1.getIdAula() == clase2.getIdAula()
				&& clase1.getHora().equals(clase2.getHora());
	}

	public boolean delete(int idClase) {
		// begin-user-code
		DAOClase daoClase = FactoriaIntegracion.getInstancia().generaDAOClase();
		TClase clase = daoClase.readById(idClase);
		if (clase == null)
			return false;
		
		// Comprobar que no hay nadie matriculado en la clase antes de poeder darla de baja
		DAOMatricula daoMatricula = FactoriaIntegracion.getInstancia().generaDAOMatricula();
		List<TMatricula> matriculas = daoMatricula.listarMatriculasPorClase(idClase);
		boolean ok = true;
		for (TMatricula tMatricula : matriculas) {
			if(tMatricula.getEstado() && tMatricula.getIdClase() == idClase)
				ok = false;
		}
		
		if(ok)
			return daoClase.delete(idClase);
		else
			return false;
		// end-user-code
	}

	public boolean update(TClase tClase) {
		// begin-user-code
		if (tClase != null) {
			DAOClase daoClase = FactoriaIntegracion.getInstancia().generaDAOClase();
			TClase clase = daoClase.readById(tClase.getId());
			if (clase == null)
				return false;

			clase.setHora(tClase.getHora());
			List<TClase> clases = daoClase.mostrarClasesPorAula(clase.getIdAula());

			// Comprobacion de que que no hay otra clase a la misma hora en el
			// mismo aula para que se pueda modificar
			boolean aulaDisponible = true;
			for (TClase c : clases) {
				if (seImpartenALaMismaHoraEnLaMismaAula(c, clase))
					aulaDisponible = false;
			}

			if (aulaDisponible)
				return daoClase.update(tClase);
			else
				return false;
		} else
			return false;
		// end-user-code
	}

	public TClase readById(int id) {
		// begin-user-code
		DAOClase daoClase = FactoriaIntegracion.getInstancia().generaDAOClase();
		return daoClase.readById(id);
		// end-user-code
	}

	public List<TClase> readAll() {
		// begin-user-code
		DAOClase daoClase = FactoriaIntegracion.getInstancia().generaDAOClase();
		return daoClase.readAll();
		// end-user-code
	}

	public List<TClase> readAllUnavalaible() {
		// begin-user-code
		DAOClase daoClase = FactoriaIntegracion.getInstancia().generaDAOClase();
		return daoClase.readAllUnavalaible();
		// end-user-code
	}

	public List<TClase> mostrarClasesPorEntrenador(int id) {
		// begin-user-code
		DAOClase daoClase = FactoriaIntegracion.getInstancia().generaDAOClase();
		return daoClase.mostrarClasesPorEntrenador(id);
		// end-user-code
	}

	public List<TClase> mostrarClasesPorAula(int id) {
		// begin-user-code
		DAOClase daoClase = FactoriaIntegracion.getInstancia().generaDAOClase();
		return daoClase.mostrarClasesPorAula(id);
		// end-user-code
	}

	@Override
	public boolean asignarClaseAAula(TClase clase) {
		if (clase == null)
			return false;

		DAOClase daoClase = FactoriaIntegracion.getInstancia().generaDAOClase();
		DAOAula daoAula = FactoriaIntegracion.getInstancia().generaDAOAula();
		TClase claserecogida = daoClase.readById(clase.getId());

		if (claserecogida == null)
			return false;

		clase.setHora(claserecogida.getHora());

		TAula aula = daoAula.readById(clase.getIdAula());
		List<TClase> clasesPorAula = daoClase.mostrarClasesPorAula(clase.getIdAula());
		boolean aulaDisponible = false;

		// Comprobacion de que el aula este activa y que no hay otra clase a la
		// misma hora en el mismo aula para que se pueda dar de alta
		if (aula != null) {
			aulaDisponible = aula.getEstado();
			for (TClase c : clasesPorAula) {
				if (seImpartenALaMismaHoraEnLaMismaAula(c, clase))
					aulaDisponible = false;
			}
		}

		if (aulaDisponible)
			return daoClase.asignarClaseAAula(clase);
		else
			return false;

	}

	@Override
	public boolean asignarClaseAEntrenador(TClase clase) {
		if (clase == null)
			return false;

		DAOClase daoClase = FactoriaIntegracion.getInstancia().generaDAOClase();
		DAOPersonal daoPersonal = FactoriaIntegracion.getInstancia().generaDAOPersonal();
		TEntrenador entrenador = daoPersonal.readEntrenadorById(clase.getIdEntrenador());
		TClase claserecogida = daoClase.readById(clase.getId());

		if (claserecogida == null)
			return false;

		boolean entrenadorDisponible = false;
		if (entrenador != null) {
			int clasesEntrenador = daoClase.clasesAlDiaPorEntrenador(entrenador.getId_entrenador());
			entrenadorDisponible = entrenador.getEstado() && clasesEntrenador + 1 <= entrenador.getClases_dia();
		}

		if (entrenadorDisponible)
			return daoClase.asignarClaseAEntrenador(clase);
		else
			return false;
	}

}