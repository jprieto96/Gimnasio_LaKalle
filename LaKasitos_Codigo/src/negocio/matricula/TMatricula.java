/**
 * 
 */
package negocio.matricula;

public class TMatricula {

	private int id;
	private int idCliente;
	private int idClase;
	private String nivel;
	private boolean estado;
	
	public TMatricula(int id, int idCliente, int idClase, String nivel, boolean estado) {
		this.id = id;
		this.idCliente = idCliente;
		this.idClase = idClase;
		this.nivel = nivel;
		this.estado = estado;
	}

	public TMatricula(int idCliente, int idClase, String nivel) {
		this.idCliente = idCliente;
		this.idClase = idClase;
		this.nivel = nivel;
	}
	
	public String getNivel() {
		return nivel;
	}
	
	public void setNivel(String nivel) {
		this.nivel = nivel;
	}
	
	public boolean getEstado() {
		return estado;
	}
	
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	
	public int getId() {
		return id;
	}
	
	public int getIdCliente() {
		return idCliente;
	}
	
	public int getIdClase() {
		return idClase;
	}
	
	@Override
	public String toString() {
		String status = estado ? "Disponible" : "No Disponible";
		return "ID: " + id + ", ID cliente: " + idCliente + ", ID Clase: " + idClase + ", Nivel: " + nivel + ", Estado: " + status;
	}
}