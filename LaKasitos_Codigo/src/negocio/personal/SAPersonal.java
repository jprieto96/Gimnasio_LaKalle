/**
 * 
 */
package negocio.personal;

import java.util.List;

public interface SAPersonal {

	public int create(TPersonal tPersonal, int evento);

	public boolean update(TPersonal tPersonal, int evento);

	public boolean delete(int id);

	public TPersonal readById(int id);

	public List<TPersonal> readAll();

	public List<TPersonal> readAllUnavalaible();
	
}