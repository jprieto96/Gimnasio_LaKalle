package negocio.personal;

public class TMantenimiento extends TPersonal {

	private int id_mantenimiento;
	private String turno;

	public TMantenimiento(int id_personal, String nombre, String dni, String cuenta_bancaria, String telefono,
			int salario, int id_mantenimiento, String turno, boolean estado) {
		super(id_personal, nombre, dni, cuenta_bancaria, telefono, salario, estado);
		this.id_mantenimiento = id_mantenimiento;
		this.turno = turno;
	}

	public TMantenimiento(int idPersonal, String nombre, String dni, String cuenta_bancaria, String telefono,
			int salario, String turno, boolean estado) {
		super(idPersonal, nombre, dni, cuenta_bancaria, telefono, salario, estado);
		this.turno = turno;
	}

	public TMantenimiento(String nombre, String dni, String cuenta_bancaria, String telefono, int salario, String turno,
			boolean estado) {
		super(nombre, dni, cuenta_bancaria, telefono, salario, estado);
		this.turno = turno;
	}

	public int getId_mantenimiento() {
		return id_mantenimiento;
	}

	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}

	@Override
	public String toString() {
		return super.toString() + ", <b>ID Mantenimiento</b>: " + id_mantenimiento
						+ ", <b>Turno</b>: " + turno + "</html>";
	}
}
