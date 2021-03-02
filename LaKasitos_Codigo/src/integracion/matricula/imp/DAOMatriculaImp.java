/**
 * 
 */
package integracion.matricula.imp;

import integracion.Conexion;
import integracion.matricula.DAOMatricula;
import negocio.matricula.TMatricula;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

public class DAOMatriculaImp implements DAOMatricula {

	public int create(TMatricula tMatricula) {
		// begin-user-code
		int id = -1;
		try {
			Connection connection = Conexion.getConnection();

			PreparedStatement ps = connection.prepareStatement("insert into matricula (id_cliente, id_clase, nivel) values (?, ?, ?)",
					java.sql.Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, tMatricula.getIdCliente());
			ps.setInt(2, tMatricula.getIdClase());
			ps.setString(3, tMatricula.getNivel());

			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();

			while (rs.next()) {
				id = rs.getInt(1);
			}

			connection.close();
		} catch (SQLException ex) {
			System.out.println(ex);
		}
		return id;
		// end-user-code
	}

	public boolean delete(int id) {
		// begin-user-code
		try {
			Connection connection = Conexion.getConnection();

			PreparedStatement ps = connection.prepareStatement("update matricula set estado = false where id = ?");
			ps.setInt(1, id);

			ps.executeUpdate();

			connection.close();
			return true;
		} catch (SQLException ex) {
			return false;
		}
		// end-user-code
	}

	public boolean update(TMatricula tMatricula) {
		// begin-user-code
		try {
			Connection connection = Conexion.getConnection();

			PreparedStatement ps = connection.prepareStatement("update matricula set nivel = ? where id = ?");
			ps.setString(1, tMatricula.getNivel());
			ps.setInt(2, tMatricula.getId());

			ps.executeUpdate();

			connection.close();
			return true;
		} catch (SQLException ex) {
			System.out.println(ex);
			return false;
		}
		// end-user-code
	}

	public TMatricula readById(int id) {
		// begin-user-code
		TMatricula matricula = null;
		try {
			Connection connection = Conexion.getConnection();

			PreparedStatement ps = connection.prepareStatement("select * from matricula where id = ?");
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			while (rs.next())
				matricula = new TMatricula(rs.getInt("id"), rs.getInt("id_cliente"), rs.getInt("id_clase"), rs.getString("nivel"), rs.getBoolean("estado"));

			connection.close();
		} catch (SQLException ex) {
			System.out.println(ex);
		}
		return matricula;
		// end-user-code
	}

	public List<TMatricula> readAll() {
		// begin-user-code
		List<TMatricula> matriculas = new Vector<TMatricula>();
		try {
			Connection connection = Conexion.getConnection();

			PreparedStatement ps = connection.prepareStatement("select * from matricula");

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				int idCliente = rs.getInt("id_cliente");
				int idClase = rs.getInt("id_clase");
				String nivel = rs.getString("nivel");
				boolean estado = rs.getBoolean("estado");
				matriculas.add(new TMatricula(id, idCliente, idClase, nivel, estado));
			}

			connection.close();
		} catch (SQLException ex) {
			System.out.println(ex);
		}
		return matriculas;
		// end-user-code
	}

	public List<TMatricula> readAllUnavalaible() {
		// begin-user-code
		List<TMatricula> matriculas = new Vector<TMatricula>();
		try {
			Connection connection = Conexion.getConnection();

			PreparedStatement ps = connection.prepareStatement("select * from matricula where estado = false");

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				int idCliente = rs.getInt("id_cliente");
				int idClase = rs.getInt("id_clase");
				String nivel = rs.getString("nivel");
				boolean estado = rs.getBoolean("estado");
				matriculas.add(new TMatricula(id, idCliente, idClase, nivel, estado));
			}

			connection.close();
		} catch (SQLException ex) {
			System.out.println(ex);
		}
		return matriculas;
		// end-user-code
	}

	public boolean reactivate(int id) {
		// begin-user-code
		try {
			Connection connection = Conexion.getConnection();

			PreparedStatement ps = connection.prepareStatement("update matricula set estado = true where id = ?");
			ps.setInt(1, id);

			ps.executeUpdate();

			connection.close();
			return true;
		} catch (SQLException ex) {
			System.out.println(ex);
			return false;
		}
		// end-user-code
	}

	public List<TMatricula> listarMatriculasPorClase(int id) {
		// begin-user-code
		List<TMatricula> matriculas = new Vector<TMatricula>();
		try {
			Connection connection = Conexion.getConnection();

			PreparedStatement ps = connection.prepareStatement("select * from matricula where id_clase = ?");
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int idMatricula = rs.getInt("id");
				int idCliente = rs.getInt("id_cliente");
				int idClase = rs.getInt("id_clase");
				String nivel = rs.getString("nivel");
				boolean estado = rs.getBoolean("estado");
				matriculas.add(new TMatricula(idMatricula, idCliente, idClase, nivel, estado));
			}

			connection.close();
		} catch (SQLException ex) {
			System.out.println(ex);
		}
		return matriculas;
		// end-user-code
	}

	public List<TMatricula> listarMatriculasPorCliente(int id) {
		// begin-user-code
		List<TMatricula> matriculas = new Vector<TMatricula>();
		try {
			Connection connection = Conexion.getConnection();

			PreparedStatement ps = connection.prepareStatement("select * from matricula where id_cliente = ?");
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int idMatricula = rs.getInt("id");
				int idCliente = rs.getInt("id_cliente");
				int idClase = rs.getInt("id_clase");
				String nivel = rs.getString("nivel");
				boolean estado = rs.getBoolean("estado");
				matriculas.add(new TMatricula(idMatricula, idCliente, idClase, nivel, estado));
			}

			connection.close();
		} catch (SQLException ex) {
			System.out.println(ex);
		}
		return matriculas;
		// end-user-code
	}
}