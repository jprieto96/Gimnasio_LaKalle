package negocio.personal;

public class TEntrenador extends TPersonal {

	private int id_entrenador;
	private int clases_dia;

	public TEntrenador(int idPersonal, String nombre, String dni, String cuenta_bancaria, String telefono, int salario,
			int id_entrenador, int clases_dia, boolean estado) {
		super(idPersonal, nombre, dni, cuenta_bancaria, telefono, salario, estado);
		this.id_entrenador = id_entrenador;
		this.clases_dia = clases_dia;
	}

	public TEntrenador(int idPersonal, String nombre, String dni, String cuenta_bancaria, String telefono, int salario,
			int clases_dia, boolean estado) {
		super(idPersonal, nombre, dni, cuenta_bancaria, telefono, salario, estado);
		this.clases_dia = clases_dia;
	}
	
	public TEntrenador(int idPersonal, String nombre, String dni, String cuenta_bancaria, String telefono, int salario,
			int clases_dia) {
		super(idPersonal, nombre, dni, cuenta_bancaria, telefono, salario);
		this.clases_dia = clases_dia;
	}

	public TEntrenador(String nombre, String dni, String cuenta_bancaria, String telefono, int salario, int clases_dia,
			boolean estado) {
		super(nombre, dni, cuenta_bancaria, telefono, salario, estado);
		this.clases_dia = clases_dia;
	}
	
	public TEntrenador(String nombre, String dni, String cuenta_bancaria, String telefono, int salario, int clases_dia) {
		super(nombre, dni, cuenta_bancaria, telefono, salario);
		this.clases_dia = clases_dia;
	}

	public TEntrenador(int id_personal, int id_entrenador, int clases_dia) {
		super(id_personal);
		this.id_entrenador = id_entrenador;
		this.clases_dia = clases_dia;
	}

	public int getId_entrenador() {
		return id_entrenador;
	}

	public int getClases_dia() {
		return clases_dia;
	}

	public void setClases_dia(int clases_dia) {
		this.clases_dia = clases_dia;
	}

	public void setId_entrenador(int id_entrenador) {
		this.id_entrenador = id_entrenador;
	}

	@Override
	public String toString() {
		return super.toString() + ", <b>ID Entrenador</b>: " + id_entrenador
					+ ", <b>Clases/Dia Max</b>: " + clases_dia + "</html>";
	}

}
