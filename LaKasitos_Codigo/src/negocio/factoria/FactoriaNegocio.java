/**
 * 
 */
package negocio.factoria;

import negocio.aula.SA_Aula;

import negocio.clase.SA_Clase;
import negocio.cliente.SA_Cliente;
import negocio.matricula.SAMatricula;
import negocio.personal.SAPersonal;

public abstract class FactoriaNegocio {

	private static FactoriaNegocio instancia;

	public static FactoriaNegocio getInstancia() {
		// begin-user-code
		if (instancia == null)
			instancia = new FactoriaNegocioImp();
		return instancia;
		// end-user-code
	}

	public abstract SA_Aula generaSAAula();

	public abstract SA_Clase generaSAClase();

	public abstract SA_Cliente generaSACliente();

	public abstract SAPersonal generaSAPersonal();

	public abstract SAMatricula generaSAMatricula();

}