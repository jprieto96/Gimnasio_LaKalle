/**
 * 
 */
package negocio.cliente.imp;

import negocio.cliente.ClienteTOA;
import negocio.cliente.SA_Cliente;
import negocio.cliente.TCliente;
import negocio.cliente.TDatosCompletosCliente;
import negocio.matricula.TMatricula;

import java.util.List;

import integracion.cliente.DAOCliente;
import integracion.factoria.FactoriaIntegracion;
import integracion.matricula.DAOMatricula;

public class SA_ClienteImp implements SA_Cliente {

	public Integer create(TCliente tCliente) {
		// begin-user-code
		int id = -1;
		if (tCliente != null) {
			DAOCliente daoCliente = FactoriaIntegracion.getInstancia().generaDAOCliente();
			if (daoCliente.readById(tCliente.getId()) == null)
				id = daoCliente.create(tCliente);
			else {
				id = tCliente.getId();
				daoCliente.reactivate(id);
			}
		}
		return id;
		// end-user-code
	}

	public Boolean delete(Integer idCliente) {
		// begin-user-code
		boolean ok = false;
		DAOCliente daoCliente = FactoriaIntegracion.getInstancia().generaDAOCliente();
		TCliente cliente = daoCliente.readById(idCliente);
		if (cliente == null)
			return false;
		
		// Comprobar antes de borrar el cliente que no tenga ninguna matriculacion activa
		DAOMatricula daoMatricula = FactoriaIntegracion.getInstancia().generaDAOMatricula();
		List<TMatricula> matriculas = daoMatricula.listarMatriculasPorCliente(idCliente);
		for (TMatricula tMatricula : matriculas) {
			if (tMatricula.getEstado() && tMatricula.getIdCliente() == idCliente)
				ok = true;
		}
		if (!ok) {
			return daoCliente.delete(idCliente);
		} else
			return false;
		// end-user-code
	}

	public Boolean update(TCliente cliente) {
		// begin-user-code
		if (cliente != null) {
			DAOCliente daoCliente = FactoriaIntegracion.getInstancia().generaDAOCliente();
			TCliente c = daoCliente.readById(cliente.getId());
			if (c != null)
				return daoCliente.update(cliente);
			else
				return false;
		} else
			return false;
		// end-user-code
	}

	public TDatosCompletosCliente readById(Integer id) {
		// begin-user-code
		ClienteTOA clienteTOA = new ClienteTOA();
		return clienteTOA.datosCompletosCliente(id);
		// end-user-code
	}

	public List<TCliente> readAll() {
		// begin-user-code
		DAOCliente daoCliente = FactoriaIntegracion.getInstancia().generaDAOCliente();
		return daoCliente.readAll();
		// end-user-code
	}

	public List<TCliente> readAllUnavailable() {
		// begin-user-code
		DAOCliente daoCliente = FactoriaIntegracion.getInstancia().generaDAOCliente();
		return daoCliente.readAllUnavailable();
		// end-user-code
	}
}