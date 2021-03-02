package negocio.cliente;

import java.util.List;

import negocio.clase.TClase;
import negocio.matricula.TMatricula;

public class TDatosCompletosCliente {
	
	private TCliente cliente;
	private List<TClase> clases;
	private List<TMatricula> matriculas;
	
	public TDatosCompletosCliente(TCliente cliente, List<TClase> clases, List<TMatricula> matriculas) {
		this.cliente = cliente;
		this.clases = clases;
		this.matriculas = matriculas;
	}
	
	@Override
	public String toString() {
		String m = "Numero de matriculas del cliente: " + matriculas.size();
		String c = "Hora de sus clases: ";
		for (int i = 0; i < clases.size(); ++i) {
			if(i == clases.size() - 1)
				c += "Clase " + clases.get(i).getId() + " a las " + clases.get(i).getHora();
			else
				c += "Clase " + clases.get(i).getId() + " a las " + clases.get(i).getHora() + ", ";
		}
		if(matriculas.isEmpty() && clases.isEmpty())
			return cliente.toString() + "\n No se ha matriculado en ninguna clase";
		else 	
			return cliente.toString() + "\n" + m + "\n" + c;
	}

	public TCliente getCliente() {
		return cliente;
	}

	public List<TClase> getClases() {
		return clases;
	}

	public List<TMatricula> getMatriculas() {
		return matriculas;
	}
	
}
