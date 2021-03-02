package negocio.aula.imp;

import negocio.aula.SA_Aula;
import negocio.aula.TAula;
import java.util.List;
import integracion.aula.DAOAula;
import integracion.factoria.FactoriaIntegracion;

public class SA_AulaImp implements SA_Aula {

	public int create(TAula tAula) {
		// begin-user-code
		int id = -1;
		if (tAula != null) {
			DAOAula daoAula = FactoriaIntegracion.getInstancia().generaDAOAula();
			if (daoAula.readById(tAula.getId()) == null)
				id = daoAula.create(tAula);
			else {
				id = tAula.getId();
				daoAula.reactivate(id);
			}
		}
		return id;
		// end-user-code
	}

	public boolean update(TAula aula) {
		// begin-user-code
		if (aula != null) {
			DAOAula daoAula = FactoriaIntegracion.getInstancia().generaDAOAula();
			TAula a = daoAula.readById(aula.getId());
			if (a != null)
				return daoAula.update(aula);
			else
				return false;
		} else
			return false;
		// end-user-code
	}

	public boolean delete(int idAula) {
		// begin-user-code
		boolean ok = false;
		DAOAula daoAula = FactoriaIntegracion.getInstancia().generaDAOAula();
		TAula aula = daoAula.readById(idAula);
		if (aula == null)
			return false;
		List<TAula> aulas = daoAula.aulasImpartiendoClases();
		for (TAula tAula : aulas) {
			if (tAula.getId() == aula.getId())
				ok = true;
		}
		if (!ok) {
			return daoAula.delete(idAula);
		} else
			return false;
		// end-user-code
	}

	public TAula readById(int id) {
		// begin-user-code
		DAOAula daoAula = FactoriaIntegracion.getInstancia().generaDAOAula();
		return daoAula.readById(id);
		// end-user-code
	}

	public List<TAula> readAll() {
		// begin-user-code
		DAOAula daoAula = FactoriaIntegracion.getInstancia().generaDAOAula();
		return daoAula.readAll();
		// end-user-code
	}

	@Override
	public List<TAula> readAllUnavailable() {
		DAOAula daoAula = FactoriaIntegracion.getInstancia().generaDAOAula();
		return daoAula.readAllUnavaliable();
	}
}