package negocio.cliente;

import java.util.ArrayList;
import java.util.List;

import integracion.clase.DAOClase;
import integracion.cliente.DAOCliente;
import integracion.factoria.FactoriaIntegracion;
import integracion.matricula.DAOMatricula;
import negocio.clase.TClase;
import negocio.matricula.TMatricula;

public class ClienteTOA {
	
	public TDatosCompletosCliente datosCompletosCliente(int idCliente) {
		DAOCliente daoCliente = FactoriaIntegracion.getInstancia().generaDAOCliente();
		DAOClase daoClase = FactoriaIntegracion.getInstancia().generaDAOClase();
		DAOMatricula daoMatricula = FactoriaIntegracion.getInstancia().generaDAOMatricula();
		
		TCliente cliente = daoCliente.readById(idCliente);
		List<TClase> clases = new ArrayList<TClase>();
		List<TMatricula> matriculas = new ArrayList<TMatricula>();
		if(cliente != null) {
			matriculas = daoMatricula.listarMatriculasPorCliente(idCliente);
			for (TMatricula tMatricula : matriculas) {
				clases.add(daoClase.readById(tMatricula.getIdClase()));
			}
			return new TDatosCompletosCliente(cliente, clases, matriculas);
		}
		else 
			return null;
	}

}
