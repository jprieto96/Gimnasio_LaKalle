package negocio.personal;

public class TPersonal {

	protected int id_personal;
	protected String nombre;
	protected String dni;
	protected String cuenta_bancaria;
	protected String telefono;
	protected int salario;
	protected boolean estado;

	public TPersonal(int id_personal) {
		this.id_personal = id_personal;
	}

	public TPersonal(int id_personal, String nombre, String dni, String cuenta_bancaria, String telefono, int salario,
			boolean estado) {
		super();
		this.id_personal = id_personal;
		this.nombre = nombre;
		this.dni = dni;
		this.cuenta_bancaria = cuenta_bancaria;
		this.telefono = telefono;
		this.salario = salario;
		this.estado = estado;
	}
	
	public TPersonal(int id_personal, String nombre, String dni, String cuenta_bancaria, String telefono, int salario) {
		super();
		this.id_personal = id_personal;
		this.nombre = nombre;
		this.dni = dni;
		this.cuenta_bancaria = cuenta_bancaria;
		this.telefono = telefono;
		this.salario = salario;
	}

	public TPersonal(String nombre, String dni, String cuenta_bancaria, String telefono, int salario, boolean estado) {
		this.nombre = nombre;
		this.dni = dni;
		this.cuenta_bancaria = cuenta_bancaria;
		this.telefono = telefono;
		this.salario = salario;
		this.estado = estado;
	}
	
	public TPersonal(String nombre, String dni, String cuenta_bancaria, String telefono, int salario) {
		this.nombre = nombre;
		this.dni = dni;
		this.cuenta_bancaria = cuenta_bancaria;
		this.telefono = telefono;
		this.salario = salario;
	}

	public int getIdPersonal() {
		return id_personal;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getCuenta_bancaria() {
		return cuenta_bancaria;
	}

	public void setCuenta_bancaria(String cuenta_bancaria) {
		this.cuenta_bancaria = cuenta_bancaria;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public int getSalario() {
		return salario;
	}

	public void setSalario(int salario) {
		this.salario = salario;
	}

	public boolean getEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public void setId_personal(int id_personal) {
		this.id_personal = id_personal;
	}

	@Override
	public String toString() {
		String status = estado ? "Disponible" : "No Disponible";
		return "<html>- <b><i>PERSONAL " + id_personal + "</i></b> -> <b>Nombre:</b> "
				+ nombre + ", <b>DNI</b>: " + dni + ", <b>Cuenta Bancaria</b>: "
				+ cuenta_bancaria + ", <b>Telefono</b>: " + telefono + ", <b>Salario</b>: "
				+ salario + ", <b>Estado Personal</b>: " + status;
	}

}
