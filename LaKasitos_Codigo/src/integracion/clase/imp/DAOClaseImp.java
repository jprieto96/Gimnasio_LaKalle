/**
 * 
 */
package integracion.clase.imp;

import integracion.Conexion;
import integracion.clase.DAOClase;
import negocio.clase.TClase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.List;
import java.util.Vector;

public class DAOClaseImp implements DAOClase {

	public int create(TClase tClase) {
		int id = -1;
		try {
			Connection connection = Conexion.getConnection();

			PreparedStatement ps = connection.prepareStatement(
					"insert into clase (hora, id_aula, id_entrenador) values (?, ?, ?)",
					java.sql.Statement.RETURN_GENERATED_KEYS);
			ps.setTime(1, Time.valueOf(tClase.getHora()));
			ps.setInt(2, tClase.getIdAula());
			ps.setInt(3, tClase.getIdEntrenador());

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
	}

	public boolean delete(int idClase) {
		// begin-user-code
		try {
			Connection connection = Conexion.getConnection();

			PreparedStatement ps = connection.prepareStatement("update clase set estado = false where id = ?");
			ps.setInt(1, idClase);

			ps.executeUpdate();

			connection.close();
			return true;
		} catch (SQLException ex) {
			return false;
		}
		// end-user-code
	}

	public boolean update(TClase tClase) {
		// begin-user-code
		try {
			Connection connection = Conexion.getConnection();

			PreparedStatement ps = connection.prepareStatement("update clase set hora = ? where id = ?");
			ps.setTime(1, Time.valueOf(tClase.getHora()));
			ps.setInt(2, tClase.getId());

			ps.executeUpdate();

			connection.close();
			return true;
		} catch (SQLException ex) {
			System.out.println(ex);
			return false;
		}
		// end-user-code
	}

	public TClase readById(int idclase) {
		// begin-user-code
		TClase clase = null;
		try {
			Connection connection = Conexion.getConnection();

			PreparedStatement ps = connection.prepareStatement("select * from clase where id = ?");
			ps.setInt(1, idclase);

			ResultSet rs = ps.executeQuery();

			while (rs.next())
				clase = new TClase(rs.getInt("id"), rs.getTime("hora").toLocalTime(), rs.getInt("id_aula"),
						rs.getInt("id_entrenador"), rs.getBoolean("estado"));

			connection.close();
		} catch (SQLException ex) {
			System.out.println(ex);
		}
		return clase;
		// end-user-code
	}

	public List<TClase> readAll() {
		// begin-user-code
		List<TClase> clases = new Vector<TClase>();
		try {
			Connection connection = Conexion.getConnection();

			PreparedStatement ps = connection.prepareStatement("select * from clase");

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				LocalTime hora = rs.getTime("hora").toLocalTime();
				boolean estado = rs.getBoolean("estado");
				int id_aula = rs.getInt("id_aula");
				int id_entrenador = rs.getInt("id_entrenador");
				clases.add(new TClase(id, hora, id_aula, id_entrenador, estado));
			}

			connection.close();
		} catch (SQLException ex) {
			System.out.println(ex);
		}
		return clases;
		// end-user-code
	}

	public List<TClase> readAllUnavalaible() {
		// begin-user-code
		List<TClase> clases = new Vector<TClase>();
		try {
			Connection connection = Conexion.getConnection();

			PreparedStatement ps = connection.prepareStatement("select * from clase where estado = false");

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				LocalTime hora = rs.getTime("hora").toLocalTime();
				boolean estado = rs.getBoolean("estado");
				int id_aula = rs.getInt("id_aula");
				int id_entrenador = rs.getInt("id_entrenador");
				clases.add(new TClase(id, hora, id_aula, id_entrenador, estado));
			}

			connection.close();
		} catch (SQLException ex) {
			System.out.println(ex);
		}
		return clases;
		// end-user-code
	}

	public List<TClase> mostrarClasesPorEntrenador(int id) {
		// begin-user-code
		List<TClase> clases = new Vector<TClase>();
		try {
			Connection connection = Conexion.getConnection();

			PreparedStatement ps = connection.prepareStatement("select * from clase where id_entrenador = ?");
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int idClase = rs.getInt("id");
				LocalTime hora = rs.getTime("hora").toLocalTime();
				boolean estado = rs.getBoolean("estado");
				int idAula = rs.getInt("id_aula");
				int idEntrenador = rs.getInt("id_entrenador");
				clases.add(new TClase(idClase, hora, idAula, idEntrenador, estado));
			}

			connection.close();
		} catch (SQLException ex) {
			System.out.println(ex);
		}
		return clases;
		// end-user-code
	}

	public List<TClase> mostrarClasesPorAula(int id) {
		// begin-user-code
		List<TClase> clases = new Vector<TClase>();
		try {
			Connection connection = Conexion.getConnection();

			PreparedStatement ps = connection.prepareStatement("select * from clase where id_aula = ?");
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int idClase = rs.getInt("id");
				LocalTime hora = rs.getTime("hora").toLocalTime();
				boolean estado = rs.getBoolean("estado");
				int idAula = rs.getInt("id_aula");
				int idEntrenador = rs.getInt("id_entrenador");
				clases.add(new TClase(idClase, hora, idAula, idEntrenador, estado));
			}

			connection.close();
		} catch (SQLException ex) {
			System.out.println(ex);
		}
		return clases;
		// end-user-code
	}

	public boolean reactivate(int idClase) {
		// begin-user-code
		try {
			Connection connection = Conexion.getConnection();

			PreparedStatement ps = connection.prepareStatement("update clase set estado = true where id = ?");
			ps.setInt(1, idClase);

			ps.executeUpdate();

			connection.close();
			return true;
		} catch (SQLException ex) {
			System.out.println(ex);
			return false;
		}
		// end-user-code
	}

	@Override
	public boolean asignarClaseAAula(TClase clase) {
		try {
			Connection connection = Conexion.getConnection();

			PreparedStatement ps = connection.prepareStatement("update clase set id_aula = ? where id = ?");
			ps.setInt(1, clase.getIdAula());
			ps.setInt(2, clase.getId());

			ps.executeUpdate();

			connection.close();
			return true;
		} catch (SQLException ex) {
			System.out.println(ex);
			return false;
		}
	}

	@Override
	public boolean asignarClaseAEntrenador(TClase clase) {
		try {
			Connection connection = Conexion.getConnection();

			PreparedStatement ps = connection.prepareStatement("update clase set id_entrenador = ? where id = ?");
			ps.setInt(1, clase.getIdEntrenador());
			ps.setInt(2, clase.getId());

			ps.executeUpdate();

			connection.close();
			return true;
		} catch (SQLException ex) {
			System.out.println(ex);
			return false;
		}
	}

	@Override
	public int clasesAlDiaPorEntrenador(int id) {
		int nClases = -1;
		try {
			Connection connection = Conexion.getConnection();

			PreparedStatement ps = connection
					.prepareStatement("SELECT count(*) AS contador FROM clase where id_entrenador = ?");
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				nClases = rs.getInt("contador");
			}

			connection.close();
		} catch (SQLException ex) {
			System.out.println(ex);
		}
		return nClases;
	}
}