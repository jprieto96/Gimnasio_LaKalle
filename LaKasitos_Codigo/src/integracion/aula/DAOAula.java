/**
 * 
 */
package integracion.aula;

import java.util.List;
import java.util.Set;

import negocio.aula.TAula;

public interface DAOAula {

	public int create(TAula tAula);

	public boolean reactivate(int idAula);

	public boolean update(TAula aula);

	public boolean delete(int id);

	public TAula readById(int id);

	public List<TAula> readAll();

	public List<TAula> aulasImpartiendoClases();

	public List<TAula> readAllUnavaliable();
}