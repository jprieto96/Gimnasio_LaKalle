/**
 * 
 */
package negocio.matricula;

import java.util.List;

public interface SAMatricula {

	public int create(TMatricula tMatricula);

	public boolean delete(int id);

	public boolean update(TMatricula tMatricula);

	public TMatricula readById(int id);

	public List<TMatricula> readAll();

	public List<TMatricula> readAllUnavailable();

	public List<TMatricula> listarMatriculasPorClase(int id);

	public List<TMatricula> listarMatriculasPorCliente(int id);
}