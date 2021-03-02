/**
 * 
 */
package integracion.clase;

import negocio.clase.TClase;
import java.util.List;

public interface DAOClase {

	public int create(TClase tClase);

	public boolean delete(int idClase);

	public boolean update(TClase tClase);

	public TClase readById(int idClase);

	public List<TClase> readAll();

	public List<TClase> readAllUnavalaible();

	public List<TClase> mostrarClasesPorEntrenador(int id);

	public List<TClase> mostrarClasesPorAula(int id);

	public boolean reactivate(int idClase);

	public boolean asignarClaseAAula(TClase clase);

	public boolean asignarClaseAEntrenador(TClase clase);

	public int clasesAlDiaPorEntrenador(int id);
}