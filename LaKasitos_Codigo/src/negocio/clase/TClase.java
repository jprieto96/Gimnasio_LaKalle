/**
 * 
 */
package negocio.clase;

import java.time.LocalTime;

public class TClase {

	private int id;
	private LocalTime hora;
	private int id_aula;
	private int id_entrenador;
	private boolean estado;

	public TClase(int id) {
		this.id = id;
	}
	
	public TClase(int id, LocalTime hora) {
		this.id = id;
		this.hora = hora;
	}

	public TClase(int id, LocalTime hora, int id_aula, int id_entrenador, boolean estado) {
		this.id = id;
		this.hora = hora;
		this.id_aula = id_aula;
		this.id_entrenador = id_entrenador;
		this.estado = estado;
	}

	public TClase(LocalTime hora, int idAula, int idEntrenador) {
		this.hora = hora;
		this.id_aula = idAula;
		this.id_entrenador = idEntrenador;
		this.estado = true;
	}

	public TClase(int idAula, int idEntrenador) {
		this.id_aula = idAula;
		this.id_entrenador = idEntrenador;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public LocalTime getHora() {
		// begin-user-code
		return hora;
		// end-user-code
	}

	public int getIdAula() {
		// begin-user-code
		return id_aula;
		// end-user-code
	}

	public int getIdEntrenador() {
		// begin-user-code
		return id_entrenador;
		// end-user-code
	}

	public boolean getEstado() {
		// begin-user-code
		return estado;
		// end-user-code
	}

	public void setHora(LocalTime hora) {
		// begin-user-code
		this.hora = hora;
		// end-user-code
	}

	public void setIdEntrenador(int id_entrenador) {
		// begin-user-code
		this.id_entrenador = id_entrenador;
		// end-user-code
	}

	public void setIdAula(int id_aula) {
		// begin-user-code
		this.id_aula = id_aula;
		// end-user-code
	}

	public void setEstado(boolean estado) {
		// begin-user-code
		this.estado = estado;
		// end-user-code
	}

	@Override
	public String toString() {
		String status = estado ? "Disponible" : "No Disponible";
		return "<html>- <b><i>CLASE " + id + "</i></b> -> <b>Hora</b>: "
				+ hora + ", <b>ID Aula</b>: " + id_aula + ", <b>ID Entrenador</b>: "
					+ id_entrenador + ", <b>Estado Clase</b>: " + status + "</html>";
	}
}