/**
 * 
 */
package negocio.personal.imp;

import negocio.personal.SAPersonal;
import negocio.personal.TEntrenador;
import negocio.personal.TPersonal;
import java.util.List;
import integracion.factoria.FactoriaIntegracion;
import integracion.personal.DAOPersonal;

public class SAPersonalImp implements SAPersonal {
	
	@Override
	public int create(TPersonal tPersonal, int evento) {
		int id = -1;
		if(tPersonal != null) {
			DAOPersonal daoPersonal = FactoriaIntegracion.getInstancia().generaDAOPersonal();
			if(daoPersonal.readById(tPersonal.getIdPersonal()) == null)
				id = daoPersonal.create(tPersonal, evento);
			else {
				id = tPersonal.getIdPersonal();
				daoPersonal.reactivate(id);
			}	
		}
		return id;
	}

	@Override
	public boolean update(TPersonal tPersonal, int evento) {
		if (tPersonal != null) {
			DAOPersonal daoPersonal = FactoriaIntegracion.getInstancia().generaDAOPersonal();
			TPersonal a = daoPersonal.readById(tPersonal.getIdPersonal());
			if (a != null)
				return daoPersonal.update(tPersonal, evento);
			else
				return false;
		} 
		else
			return false;
	}

	@Override
	public boolean delete(int id) {
		boolean ok = false;
		DAOPersonal daoPersonal = FactoriaIntegracion.getInstancia().generaDAOPersonal();
		TPersonal personal = daoPersonal.readById(id);
		if (personal == null)
			return false;
		
		// Comprobar que nigun entrenador este impartiendo clases para poder eliminarlo
		try {
			TEntrenador entrenador = (TEntrenador) personal;
			if(entrenador != null) {
				List<TEntrenador> listaPersonal = daoPersonal.entrenadoresImpartiendoClases();
				for (TEntrenador p : listaPersonal) {
					if (p.getId_entrenador() == entrenador.getId_entrenador())
						ok = true;
				}
			}
		}
		catch (ClassCastException e) {
			System.out.println(e.getMessage());
		}
		

		if (!ok) {
			return daoPersonal.delete(id);
		} else
			return false;
	}

	@Override
	public TPersonal readById(int id) {
		DAOPersonal daoPersonal = FactoriaIntegracion.getInstancia().generaDAOPersonal();
		return daoPersonal.readById(id);
	}

	@Override
	public List<TPersonal> readAll() {
		DAOPersonal daoPersonal = FactoriaIntegracion.getInstancia().generaDAOPersonal();
		return daoPersonal.readAll();
	}

	@Override
	public List<TPersonal> readAllUnavalaible() {
		DAOPersonal daoPersonal = FactoriaIntegracion.getInstancia().generaDAOPersonal();
		return daoPersonal.readAllUnavalaible();
	}
	
}