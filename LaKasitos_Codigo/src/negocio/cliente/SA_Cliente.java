/**
 * 
 */
package negocio.cliente;

import java.util.List;

public interface SA_Cliente {

	public Integer create(TCliente tCliente);

	public Boolean delete(Integer idCliente);

	public Boolean update(TCliente cliente);

	public TDatosCompletosCliente readById(Integer id);

	public List<TCliente> readAll();

	public List<TCliente> readAllUnavailable();
}