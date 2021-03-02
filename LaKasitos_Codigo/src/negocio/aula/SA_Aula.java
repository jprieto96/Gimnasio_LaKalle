/**
 * 
 */
package negocio.aula;

import java.util.List;

public interface SA_Aula {

	public int create(TAula tAula);

	public boolean update(TAula aula);

	public boolean delete(int idAula);

	public TAula readById(int id);

	public List<TAula> readAll();

	public List<TAula> readAllUnavailable();
	
}