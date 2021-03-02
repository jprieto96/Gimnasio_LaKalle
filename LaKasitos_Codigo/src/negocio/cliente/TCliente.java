/**
 * 
 */
package negocio.cliente;

public class TCliente {

	private Integer id;
	private String nombre;
	private String dni;
	private String telefono;
	private String cuenta_bancaria;
	private String domicilio;
	private String correo;
	private Boolean estado;

	public TCliente(Integer id, String nombre, String dni, String cuenta_bancaria, String telefono, String domicilio,
			String correo, boolean estado) {
		this.id = id;
		this.nombre = nombre;
		this.dni = dni;
		this.cuenta_bancaria = cuenta_bancaria;
		this.telefono = telefono;
		this.domicilio = domicilio;
		this.correo = correo;
		this.estado = estado;
	}

	public TCliente(String nombre, String dni, String cuenta_bancaria, String telefono, String domicilio, String correo) {
		this.nombre = nombre;
		this.dni = dni;
		this.telefono = telefono;
		this.cuenta_bancaria = cuenta_bancaria;
		this.domicilio = domicilio;
		this.correo = correo;
		this.estado = true;
	}
	
	public TCliente(Integer id, String nombre, String dni, String cuenta_bancaria, String telefono, String domicilio,
			String correo) {
		this.id = id;
		this.nombre = nombre;
		this.dni = dni;
		this.telefono = telefono;
		this.cuenta_bancaria = cuenta_bancaria;
		this.domicilio = domicilio;
		this.correo = correo;
		this.estado = true;
	}

	public Integer getId() {
		// begin-user-code
		return id;
		// end-user-code
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		// begin-user-code
		return nombre;
		// end-user-code
	}

	public String getDni() {
		// begin-user-code
		return dni;
		// end-user-code
	}

	public String getTelefono() {
		// begin-user-code
		return telefono;
		// end-user-code
	}

	public String getCuentaBancaria() {
		// begin-user-code
		return cuenta_bancaria;
		// end-user-code
	}

	public String getDomicilio() {
		// begin-user-code
		return domicilio;
		// end-user-code
	}

	public String getCorreo() {
		// begin-user-code
		return correo;
		// end-user-code
	}

	public Boolean getEstado() {
		// begin-user-code
		return estado;
		// end-user-code
	}

	public void setNombre(String nombre) {
		// begin-user-code
		this.nombre = nombre;
		// end-user-code
	}

	public void setDni(String dni) {
		// begin-user-code
		this.dni = dni;
		// end-user-code
	}

	public void setTelefono(String telefono) {
		// begin-user-code
		this.telefono = telefono;
		// end-user-code
	}

	public void setCuentaBancaria(String cuentaBancaria) {
		// begin-user-code
		this.cuenta_bancaria = cuentaBancaria;
		// end-user-code
	}

	public void setDomicilio(String domicilio) {
		// begin-user-code
		this.domicilio = domicilio;
		// end-user-code
	}

	public void setCorreo(String correo) {
		// begin-user-code
		this.correo = correo;
		// end-user-code
	}

	public void setEstado(Boolean estado) {
		// begin-user-code
		this.estado = estado;
		// end-user-code
	}
	
	@Override
	public String toString() {
		String status = estado ? "Disponible" : "No Disponible";
		return "<html>- <b><i>CLIENTE " + id + "</i></b> -> <b>Nombre:</b> "
				+ nombre + ", <b>DNI</b>: " + dni + ", <b>Cuenta Bancaria</b>: "
				+ cuenta_bancaria + ", <b>Telefono</b>: " + telefono + ", <b>Domicilio</b>: "
				+ domicilio + ", <b>Corrreo</b>: " +  correo + ", <b>Estado</b>: " + status;
	}
}