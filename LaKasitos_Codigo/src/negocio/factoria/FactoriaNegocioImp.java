/**
 * 
 */
package negocio.factoria;

import negocio.aula.SA_Aula;

import negocio.aula.imp.SA_AulaImp;
import negocio.clase.SA_Clase;
import negocio.clase.imp.SA_ClaseImp;
import negocio.cliente.SA_Cliente;
import negocio.cliente.imp.SA_ClienteImp;
import negocio.matricula.SAMatricula;
import negocio.matricula.imp.SAMatriculaImp;
import negocio.personal.SAPersonal;
import negocio.personal.imp.SAPersonalImp;

public class FactoriaNegocioImp extends FactoriaNegocio {

	public SA_Aula generaSAAula() {
		// begin-user-code
		return new SA_AulaImp();
		// end-user-code
	}

	@Override
	public SA_Clase generaSAClase() {
		// begin-user-code
		return new SA_ClaseImp();
		// end-user-code
	}

	public SA_Cliente generaSACliente() {
		// begin-user-code
		return new SA_ClienteImp();
		// end-user-code
	}

	@Override
	public SAPersonal generaSAPersonal() {
		// begin-user-code
		return new SAPersonalImp();
		// end-user-code
	}

	public SAMatricula generaSAMatricula() {
		// begin-user-code
		return new SAMatriculaImp();
		// end-user-code
	}
}