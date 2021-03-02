/**
 * 
 */
package integracion.factoria;

import integracion.aula.DAOAula;

import integracion.aula.imp.DAOAulaImp;
import integracion.clase.DAOClase;
import integracion.clase.imp.DAOClaseImp;
import integracion.cliente.DAOCliente;
import integracion.cliente.imp.DAOClienteImp;
import integracion.matricula.DAOMatricula;
import integracion.matricula.imp.DAOMatriculaImp;
import integracion.personal.DAOPersonal;
import integracion.personal.imp.DAOPersonalImp;

public class FactoriaIntegracionImp extends FactoriaIntegracion {

	public DAOAula generaDAOAula() {
		// begin-user-code
		return new DAOAulaImp();
		// end-user-code
	}

	public DAOClase generaDAOClase() {
		// begin-user-code
		return new DAOClaseImp();
		// end-user-code
	}

	public DAOCliente generaDAOCliente() {
		// begin-user-code
		return new DAOClienteImp();
		// end-user-code
	}

	@Override
	public DAOPersonal generaDAOPersonal() {
		// begin-user-code
		return new DAOPersonalImp();
		// end-user-code
	}

	public DAOMatricula generaDAOMatricula() {
		// begin-user-code
		return new DAOMatriculaImp();
		// end-user-code
	}

}