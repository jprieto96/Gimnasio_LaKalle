/**
 * 
 */
package integracion.matricula;

import negocio.matricula.TMatricula;

import java.util.List;

public interface DAOMatricula {

	public int create(TMatricula tMatricula);

	public boolean delete(int id);

	public boolean update(TMatricula tMatricula);

	public TMatricula readById(int id);

	public List<TMatricula> readAll();

	public List<TMatricula> readAllUnavalaible();

	public boolean reactivate(int id);

	public List<TMatricula> listarMatriculasPorClase(int id);

	public List<TMatricula> listarMatriculasPorCliente(int id);
}