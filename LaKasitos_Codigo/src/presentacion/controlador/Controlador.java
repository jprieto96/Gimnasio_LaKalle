/**
 * 
 */
package presentacion.controlador;

public abstract class Controlador {

	private static Controlador instancia;

	public static Controlador getInstancia() {
		// begin-user-code
		if (instancia == null)
			instancia = new ControladorImp();
		return instancia;
		// end-user-code
	}

	public abstract void accion(int evento, Object object);
}