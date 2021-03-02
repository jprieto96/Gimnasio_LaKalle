/**
 * 
 */
package integracion.factoria;

import integracion.aula.DAOAula;
import integracion.clase.DAOClase;
import integracion.cliente.DAOCliente;
import integracion.matricula.DAOMatricula;
import integracion.personal.DAOPersonal;

public abstract class FactoriaIntegracion {

	private static FactoriaIntegracion instancia;

	public static FactoriaIntegracion getInstancia() {
		// begin-user-code
		if (instancia == null)
			instancia = new FactoriaIntegracionImp();
		return instancia;
		// end-user-code
	}

	public abstract DAOAula generaDAOAula();

	public abstract DAOClase generaDAOClase();

	public abstract DAOCliente generaDAOCliente();

	public abstract DAOPersonal generaDAOPersonal();

	public abstract DAOMatricula generaDAOMatricula();

}