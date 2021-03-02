/**
 * 
 */
package negocio.matricula.imp;

import negocio.clase.TClase;
import negocio.cliente.ClienteTOA;
import negocio.cliente.TCliente;
import negocio.cliente.TDatosCompletosCliente;
import negocio.matricula.SAMatricula;
import negocio.matricula.TMatricula;
import java.util.List;

import integracion.clase.DAOClase;
import integracion.factoria.FactoriaIntegracion;
import integracion.matricula.DAOMatricula;

public class SAMatriculaImp implements SAMatricula {

	public int create(TMatricula tMatricula) {
		// begin-user-code
		int id = -1;
		if (tMatricula != null) {
			DAOMatricula daoMatricula = FactoriaIntegracion.getInstancia().generaDAOMatricula();
			DAOClase daoClase = FactoriaIntegracion.getInstancia().generaDAOClase();
			ClienteTOA clienteTOA = new ClienteTOA();
			TDatosCompletosCliente datosCliente = clienteTOA.datosCompletosCliente(tMatricula.getIdCliente());
			TClase clase = daoClase.readById(tMatricula.getIdClase());
			
			// Comprobacion de que la clase y el cliente esten activos en la BBDD,
			// que no se de de alta una matricula a un cliente a la misma clase
			// y que un cliente no se pueda dar de alta en dos clases diferentes a la misma hora
			boolean ok = clase.getEstado() && datosCliente.getCliente().getEstado();
			if(datosCliente != null && clase != null) {
				for (TMatricula m : datosCliente.getMatriculas()) {
					if(m.getEstado()) {
						if(matriculasMismaClase(tMatricula, m))
							ok = false;
						if(clase.getEstado() && matriculasALaMismaHora(clase, datosCliente.getClases(), m.getIdClase()))
							ok = false;
					}
				}
			}
			
			if(ok) {
				if (daoMatricula.readById(tMatricula.getId()) == null)
					id = daoMatricula.create(tMatricula);
				else {
					id = tMatricula.getId();
					daoMatricula.reactivate(id);
				}
			}
		}
		return id;
		// end-user-code
	}

	private boolean matriculasMismaClase(TMatricula m1, TMatricula m2) {
		return m1.getIdClase() == m2.getIdClase();
	}
	
	private boolean matriculasALaMismaHora(TClase clase, List<TClase> clases, int idClaseABuscar) {
		boolean ok = false;
		for (TClase c : clases) {
			if(c.getId() == idClaseABuscar && c.getEstado() && c.getHora().equals(clase.getHora()))
				ok = true;
		}
		return ok;
	}

	public boolean delete(int id) {
		// begin-user-code
		DAOMatricula daoMatricula = FactoriaIntegracion.getInstancia().generaDAOMatricula();
		TMatricula matricula = daoMatricula.readById(id);
		
		if (matricula == null)
			return false;
		
		return daoMatricula.delete(id);
		// end-user-code
	}

	public boolean update(TMatricula tMatricula) {
		// begin-user-code
		if (tMatricula != null) {
			DAOMatricula daoMatricula = FactoriaIntegracion.getInstancia().generaDAOMatricula();
			TMatricula matricula = daoMatricula.readById(tMatricula.getId());
			if (matricula == null)
				return false;
			
			return daoMatricula.update(tMatricula);
		} else
			return false;
		// end-user-code
	}

	public TMatricula readById(int id) {
		// begin-user-code
		DAOMatricula daoMatricula = FactoriaIntegracion.getInstancia().generaDAOMatricula();
		return daoMatricula.readById(id);
		// end-user-code
	}

	public List<TMatricula> readAll() {
		// begin-user-code
		DAOMatricula daoMatricula = FactoriaIntegracion.getInstancia().generaDAOMatricula();
		return daoMatricula.readAll();
		// end-user-code
	}

	public List<TMatricula> readAllUnavailable() {
		// begin-user-code
		DAOMatricula daoMatricula = FactoriaIntegracion.getInstancia().generaDAOMatricula();
		return daoMatricula.readAllUnavalaible();
		// end-user-code
	}

	public List<TMatricula> listarMatriculasPorClase(int id) {
		// begin-user-code
		DAOMatricula daoMatricula = FactoriaIntegracion.getInstancia().generaDAOMatricula();
		return daoMatricula.listarMatriculasPorClase(id);
		// end-user-code
	}

	public List<TMatricula> listarMatriculasPorCliente(int id) {
		// begin-user-code
		DAOMatricula daoMatricula = FactoriaIntegracion.getInstancia().generaDAOMatricula();
		return daoMatricula.listarMatriculasPorCliente(id);
		// end-user-code
	}
}