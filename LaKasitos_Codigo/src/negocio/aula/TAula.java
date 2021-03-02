/**
 * 
 */
package negocio.aula;

public class TAula {

	private int id;
	private int aforo;
	private boolean estado;
	
	public TAula() {}

	public TAula(int aforo) {
		this.aforo = aforo;
		this.estado = true;
	}

	public TAula(int id, int aforo) {
		this.id = id;
		this.aforo = aforo;
	}

	public TAula(int id, int aforo, boolean estado) {
		this.id = id;
		this.aforo = aforo;
		this.estado = estado;
	}

	public int getAforo() {
		// begin-user-code
		return aforo;
		// end-user-code
	}

	public int getId() {
		// begin-user-code
		return id;
		// end-user-code
	}

	public boolean getEstado() {
		// begin-user-code
		return estado;
		// end-user-code
	}

	public void setAforo(int aforo) {
		// begin-user-code
		this.aforo = aforo;
		// end-user-code
	}

	public void setEstado(boolean estado) {
		// begin-user-code
		this.estado = estado;
		// end-user-code
	}
	
	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		String status = estado ? "Disponible" : "No Disponible";
		return "<html>- <b><i>AULA " + id + "</i></b> -> <b>Aforo Máximo:</b> " 
				+ aforo + ", <b>Estado Aula</b>: " + status + "</html>";
	}
}