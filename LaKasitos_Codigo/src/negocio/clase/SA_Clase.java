/**
 * 
 */
package negocio.clase;

import java.util.List;

public interface SA_Clase {

	public int create(TClase tClase);

	public boolean delete(int idClase);

	public boolean update(TClase tClase);

	public TClase readById(int id);

	public List<TClase> readAll();

	public List<TClase> readAllUnavalaible();

	public List<TClase> mostrarClasesPorEntrenador(int id);

	public List<TClase> mostrarClasesPorAula(int id);

	public boolean asignarClaseAAula(TClase clase);

	public boolean asignarClaseAEntrenador(TClase clase);
}