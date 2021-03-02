package integracion.personal.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;
import integracion.Conexion;
import integracion.personal.DAOPersonal;
import negocio.personal.TEntrenador;
import negocio.personal.TMantenimiento;
import negocio.personal.TPersonal;
import presentacion.controlador.Evento;

public class DAOPersonalImp implements DAOPersonal {

	@SuppressWarnings("resource")
	@Override
	public int create(TPersonal tPersonal, int evento) {
		int idPersonal = -1, id = -1;
		try {
			// Se da de alta al entrenador como personal
			Connection connection = Conexion.getConnection();
			String sentencia = "INSERT into personal (nombre, dni, cuenta_bancaria, telefono, salario)"
					+ " VALUES (?, ?, ?, ?, ?)";
			PreparedStatement ps = connection.prepareStatement(sentencia, java.sql.Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, tPersonal.getNombre());
			ps.setString(2, tPersonal.getDni());
			ps.setString(3, tPersonal.getCuenta_bancaria());
			ps.setString(4, tPersonal.getTelefono());
			ps.setInt(5, tPersonal.getSalario());

			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();

			while (rs.next()) {
				idPersonal = rs.getInt(1);
			}

			// Despues de darse de alta como personal se reutiliza su idPersonal y se le da de alta como entrenador o como personal de mantenimiento
			if (evento == Evento.ALTA_ENTRENADOR) {
				sentencia = "INSERT into entrenador (clases_dia, id_personal) VALUES (?, ?)";
				ps = connection.prepareStatement(sentencia, java.sql.Statement.RETURN_GENERATED_KEYS);
				ps.setInt(1, ((TEntrenador) tPersonal).getClases_dia());
				ps.setInt(2, idPersonal);

				ps.executeUpdate();
				rs = ps.getGeneratedKeys();

				while (rs.next()) {
					id = rs.getInt(1);
				}
			} else if (evento == Evento.ALTA_MANTENIMIENTO) {
				sentencia = "INSERT into mantenimiento (turno, id_personal) VALUES (?, ?)";
				ps = connection.prepareStatement(sentencia, java.sql.Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, ((TMantenimiento) tPersonal).getTurno());
				ps.setInt(2, idPersonal);

				ps.executeUpdate();
				rs = ps.getGeneratedKeys();

				while (rs.next()) {
					id = rs.getInt(1);
				}
			}
			connection.close();
		} catch (SQLException ex) {
			System.out.println(ex);
		}
		return id;
	}

	@Override
	public boolean reactivate(int id) {
		try {
			Connection connection = Conexion.getConnection();

			PreparedStatement ps = connection.prepareStatement("update personal set estado = true where id = ?");
			ps.setInt(1, id);

			ps.executeUpdate();

			connection.close();
			return true;
		} catch (SQLException ex) {
			return false;
		}
	}

	@SuppressWarnings("resource")
	@Override
	public boolean update(TPersonal tPersonal, int evento) {
		try {
			Connection connection = Conexion.getConnection();
			String sentencia = "UPDATE personal SET nombre = ?, dni = ?, cuenta_bancaria = ?, telefono = ?, salario = ? WHERE id = ?";
			PreparedStatement ps = connection.prepareStatement(sentencia);
			ps.setString(1, tPersonal.getNombre());
			ps.setString(2, tPersonal.getDni());
			ps.setString(3, tPersonal.getCuenta_bancaria());
			ps.setString(4, tPersonal.getTelefono());
			ps.setInt(5, tPersonal.getSalario());
			ps.setInt(6, tPersonal.getIdPersonal());

			ps.executeUpdate();

			if (evento == Evento.MODIFICAR_ENTRENADOR) {
				sentencia = "UPDATE entrenador SET clases_dia = ? WHERE id_personal = ?";
				ps = connection.prepareStatement(sentencia);
				ps.setInt(1, ((TEntrenador) tPersonal).getClases_dia());
				ps.setInt(2, tPersonal.getIdPersonal());

				int colsModificadas = ps.executeUpdate();
				if (colsModificadas == 0)
					return false;

			} else if (evento == Evento.MODIFICAR_MANTENIMIENTO) {
				sentencia = "UPDATE mantenimiento SET turno = ? WHERE id_personal = ?";
				ps = connection.prepareStatement(sentencia);
				ps.setString(1, ((TMantenimiento) tPersonal).getTurno());
				ps.setInt(2, tPersonal.getIdPersonal());

				int colsModificadas = ps.executeUpdate();
				if (colsModificadas == 0)
					return false;

			}
			connection.close();
			return true;
		} catch (SQLException ex) {
			System.out.println(ex);
			return false;
		}
	}

	@Override
	public boolean delete(int id) {
		try {
			Connection connection = Conexion.getConnection();

			PreparedStatement ps = connection.prepareStatement("update personal set estado = false where id = ?");
			ps.setInt(1, id);

			ps.executeUpdate();

			connection.close();
			return true;
		} catch (SQLException ex) {
			return false;
		}
	}

	@Override
	public TPersonal readById(int id) {
		TPersonal personal = null;
		try {
			Connection connection = Conexion.getConnection();

			String sentenciaMan = "SELECT p.*, m.id AS id_mantenimiento, m.turno " + "FROM personal AS p "
					+ "JOIN mantenimiento AS m ON m.id_personal = p.id " + "WHERE p.id = ? GROUP BY p.id";
			String sentenciaEnt = "SELECT p.*, e.id AS id_entrenador, e.clases_dia " + "FROM personal AS p "
					+ "JOIN entrenador AS e ON e.id_personal = p.id " + "WHERE p.id = ? GROUP BY p.id";
			PreparedStatement psEnt = connection.prepareStatement(sentenciaEnt);
			PreparedStatement psMan = connection.prepareStatement(sentenciaMan);
			psEnt.setInt(1, id);
			psMan.setInt(1, id);

			ResultSet rsEnt = psEnt.executeQuery();
			ResultSet rsMan = psMan.executeQuery();

			while (rsEnt.next()) {
				int idPersonal = rsEnt.getInt("id");
				String nombre = rsEnt.getString("nombre");
				String dni = rsEnt.getString("dni");
				String cb = rsEnt.getString("cuenta_bancaria");
				String tfno = rsEnt.getString("telefono");
				int salario = rsEnt.getInt("salario");
				boolean estado = rsEnt.getBoolean("estado");
				int idEntrenador = rsEnt.getInt("id_entrenador");
				int clasesDia = rsEnt.getInt("clases_dia");
				personal = new TEntrenador(idPersonal, nombre, dni, cb, tfno, salario, idEntrenador, clasesDia, estado);
			}

			while (rsMan.next()) {
				int idPersonal = rsMan.getInt("id");
				String nombre = rsMan.getString("nombre");
				String dni = rsMan.getString("dni");
				String cb = rsMan.getString("cuenta_bancaria");
				String tfno = rsMan.getString("telefono");
				int salario = rsMan.getInt("salario");
				boolean estado = rsMan.getBoolean("estado");
				int idMantenimiento = rsMan.getInt("id_mantenimiento");
				String turno = rsMan.getString("turno");
				personal = new TMantenimiento(idPersonal, nombre, dni, cb, tfno, salario, idMantenimiento, turno,
						estado);
			}

			connection.close();
		} catch (SQLException ex) {
			System.out.println(ex);
		}
		return personal;
	}

	@Override
	public List<TPersonal> readAll() {
		List<TPersonal> listaPersonal = new Vector<TPersonal>();
		try {
			Connection connection = Conexion.getConnection();

			String sentenciaMan = "SELECT p.*, m.id AS id_mantenimiento, m.turno " + "FROM personal AS p "
					+ "JOIN mantenimiento AS m ON p.id = m.id_personal";
			String sentenciaEn = "SELECT p.*, e.id AS id_entrenador, e.clases_dia " + "FROM personal AS p "
					+ "JOIN entrenador AS e ON p.id = e.id_personal";
			PreparedStatement psMan = connection.prepareStatement(sentenciaMan);
			PreparedStatement psEnt = connection.prepareStatement(sentenciaEn);

			ResultSet rsEnt = psEnt.executeQuery();
			ResultSet rsMan = psMan.executeQuery();

			while (rsEnt.next()) {
				int idPersonal = rsEnt.getInt("id");
				String nombre = rsEnt.getString("nombre");
				String dni = rsEnt.getString("dni");
				String cb = rsEnt.getString("cuenta_bancaria");
				String tfno = rsEnt.getString("telefono");
				int salario = rsEnt.getInt("salario");
				boolean estado = rsEnt.getBoolean("estado");
				int idEntrenador = rsEnt.getInt("id_entrenador");
				int clasesDia = rsEnt.getInt("clases_dia");
				listaPersonal.add(
						new TEntrenador(idPersonal, nombre, dni, cb, tfno, salario, idEntrenador, clasesDia, estado));
			}

			while (rsMan.next()) {
				int idPersonal = rsMan.getInt("id");
				String nombre = rsMan.getString("nombre");
				String dni = rsMan.getString("dni");
				String cb = rsMan.getString("cuenta_bancaria");
				String tfno = rsMan.getString("telefono");
				int salario = rsMan.getInt("salario");
				boolean estado = rsMan.getBoolean("estado");
				int idMantenimiento = rsMan.getInt("id_mantenimiento");
				String turno = rsMan.getString("turno");
				listaPersonal.add(
						new TMantenimiento(idPersonal, nombre, dni, cb, tfno, salario, idMantenimiento, turno, estado));
			}

			connection.close();
		} catch (SQLException ex) {
			System.out.println(ex);
		}
		return listaPersonal;
	}

	@Override
	public List<TEntrenador> entrenadoresImpartiendoClases() {
		List<TEntrenador> listaEntrenadores = new Vector<TEntrenador>();
		try {
			Connection connection = Conexion.getConnection();

			String sentencia = "SELECT p.*, e.id AS id_entrenador, e.clases_dia FROM entrenador AS e "
					+ "JOIN clase AS c ON e.id = c.id_entrenador JOIN  personal AS p ON p.id = e.id_personal "
					+ "GROUP BY e.id";
			PreparedStatement ps = connection.prepareStatement(sentencia);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String nombre = rs.getString("nombre");
				String dni = rs.getString("dni");
				String cb = rs.getString("cuenta_bancaria");
				String tfno = rs.getString("telefono");
				int salario = rs.getInt("salario");
				boolean estado = rs.getBoolean("estado");
				int idEntrenador = rs.getInt("id_entrenador");
				int clasesDia = rs.getInt("clases_dia");
				listaEntrenadores
						.add(new TEntrenador(id, nombre, dni, cb, tfno, salario, idEntrenador, clasesDia, estado));
			}

			connection.close();
		} catch (SQLException ex) {
			System.out.println(ex);
		}
		return listaEntrenadores;
	}

	@Override
	public List<TPersonal> readAllUnavalaible() {
		List<TPersonal> listaPersonal = new Vector<TPersonal>();
		try {
			Connection connection = Conexion.getConnection();

			String sentenciaMan = "SELECT p.*, m.id AS id_mantenimiento, m.turno " + "FROM personal AS p "
					+ "JOIN mantenimiento AS m ON p.id = m.id_personal " + "WHERE estado = false";
			String sentenciaEn = "SELECT p.*, e.id AS id_entrenador, e.clases_dia " + "FROM personal AS p "
					+ "JOIN entrenador AS e ON p.id = e.id_personal " + "WHERE estado = false";
			PreparedStatement psMan = connection.prepareStatement(sentenciaMan);
			PreparedStatement psEnt = connection.prepareStatement(sentenciaEn);

			ResultSet rsEnt = psEnt.executeQuery();
			ResultSet rsMan = psMan.executeQuery();

			while (rsEnt.next()) {
				int idPersonal = rsEnt.getInt("id");
				String nombre = rsEnt.getString("nombre");
				String dni = rsEnt.getString("dni");
				String cb = rsEnt.getString("cuenta_bancaria");
				String tfno = rsEnt.getString("telefono");
				int salario = rsEnt.getInt("salario");
				boolean estado = rsEnt.getBoolean("estado");
				int idEntrenador = rsEnt.getInt("id_entrenador");
				int clasesDia = rsEnt.getInt("clases_dia");
				listaPersonal.add(
						new TEntrenador(idPersonal, nombre, dni, cb, tfno, salario, idEntrenador, clasesDia, estado));
			}

			while (rsMan.next()) {
				int idPersonal = rsMan.getInt("id");
				String nombre = rsMan.getString("nombre");
				String dni = rsMan.getString("dni");
				String cb = rsMan.getString("cuenta_bancaria");
				String tfno = rsMan.getString("telefono");
				int salario = rsMan.getInt("salario");
				boolean estado = rsMan.getBoolean("estado");
				int idMantenimiento = rsMan.getInt("id_mantenimiento");
				String turno = rsMan.getString("turno");
				listaPersonal.add(
						new TMantenimiento(idPersonal, nombre, dni, cb, tfno, salario, idMantenimiento, turno, estado));
			}

			connection.close();
		} catch (SQLException ex) {
			System.out.println(ex);
		}
		return listaPersonal;
	}

	@Override
	public TEntrenador readEntrenadorById(int id) {
		TEntrenador entrenador = null;
		try {
			Connection connection = Conexion.getConnection();

			String sentencia = "SELECT p.*, e.id AS id_entrenador, e.clases_dia "
					+ "FROM gimnasio_lakalle.entrenador AS e "
					+ "JOIN gimnasio_lakalle.personal AS p ON e.id_personal = p.id " + "WHERE e.id = ?";
			PreparedStatement ps = connection.prepareStatement(sentencia);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int idPersonal = rs.getInt("id");
				String nombre = rs.getString("nombre");
				String dni = rs.getString("dni");
				String cb = rs.getString("cuenta_bancaria");
				String tfno = rs.getString("telefono");
				int salario = rs.getInt("salario");
				boolean estado = rs.getBoolean("estado");
				int idEntrenador = rs.getInt("id_entrenador");
				int clasesDia = rs.getInt("clases_dia");
				entrenador = new TEntrenador(idPersonal, nombre, dni, cb, tfno, salario, idEntrenador, clasesDia,
						estado);
			}

			connection.close();
		} catch (SQLException ex) {
			System.out.println(ex);
		}
		return entrenador;
	}

}
