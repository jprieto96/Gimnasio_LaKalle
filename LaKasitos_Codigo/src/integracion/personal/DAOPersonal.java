package integracion.personal;

import java.util.List;
import java.util.Set;

import negocio.personal.TEntrenador;
import negocio.personal.TPersonal;

public interface DAOPersonal {

	public int create(TPersonal tPersonal, int evento);

	public boolean reactivate(int id);

	public boolean update(TPersonal tPersonal, int evento);

	public boolean delete(int id);

	public TPersonal readById(int id);

	public List<TPersonal> readAll();

	public List<TEntrenador> entrenadoresImpartiendoClases();

	public List<TPersonal> readAllUnavalaible();

	public TEntrenador readEntrenadorById(int id);

}
