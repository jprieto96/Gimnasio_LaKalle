/**
 * 
 */
package integracion.cliente;

import negocio.cliente.TCliente;

import java.util.List;

public interface DAOCliente {

	public Integer create(TCliente tCliente);

	public Boolean update(TCliente tCliente);

	public Boolean delete(Integer id);

	public TCliente readById(Integer id);

	public List<TCliente> readAll();

	public List<TCliente> readAllUnavailable();

	public Boolean reactivate(Integer idCliente);
	
}