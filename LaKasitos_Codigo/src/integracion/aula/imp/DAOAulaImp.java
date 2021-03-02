
package integracion.aula.imp;

import integracion.Conexion;
import integracion.aula.DAOAula;
import negocio.aula.TAula;
import java.sql.*;
import java.util.List;
import java.util.Vector;

public class DAOAulaImp implements DAOAula {

	public int create(TAula tAula) {
		// begin-user-code
		int id = -1;
		try {
			Connection connection = Conexion.getConnection();

			PreparedStatement ps = connection.prepareStatement("insert into aula (aforo) values (?)",
					java.sql.Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, tAula.getAforo());

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

	public boolean update(TAula aula) {
		// begin-user-code
		try {
			Connection connection = Conexion.getConnection();

			PreparedStatement ps = connection.prepareStatement("update aula set aforo = ? where id = ?");
			ps.setInt(1, aula.getAforo());
			ps.setInt(2, aula.getId());

			ps.executeUpdate();

			connection.close();
			return true;
		} catch (SQLException ex) {
			System.out.println(ex);
			return false;
		}
		// end-user-code
	}

	public boolean delete(int id) {
		// begin-user-code
		try {
			Connection connection = Conexion.getConnection();

			PreparedStatement ps = connection.prepareStatement("update aula set estado = false where id = ?");
			ps.setInt(1, id);

			ps.executeUpdate();

			connection.close();
			return true;
		} catch (SQLException ex) {
			return false;
		}
		// end-user-code
	}

	public TAula readById(int id) {
		// begin-user-code
		TAula aula = null;
		try {
			Connection connection = Conexion.getConnection();

			PreparedStatement ps = connection.prepareStatement("select * from aula where id = ?");
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			while (rs.next())
				aula = new TAula(rs.getInt("id"), rs.getInt("aforo"), rs.getBoolean("estado"));

			connection.close();
		} catch (SQLException ex) {
			System.out.println(ex);
		}
		return aula;
		// end-user-code
	}

	public List<TAula> readAll() {
		// begin-user-code
		List<TAula> aulas = new Vector<TAula>();
		try {
			Connection connection = Conexion.getConnection();

			PreparedStatement ps = connection.prepareStatement("select * from aula");

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int idAula = rs.getInt("id");
				int aforoAula = rs.getInt("aforo");
				boolean estadoAula = rs.getBoolean("estado");
				aulas.add(new TAula(idAula, aforoAula, estadoAula));
			}

			connection.close();
		} catch (SQLException ex) {
			System.out.println(ex);
		}
		return aulas;
		// end-user-code
	}

	@Override
	public List<TAula> aulasImpartiendoClases() {
		// begin-user-code
		List<TAula> aulas = new Vector<TAula>();
		try {
			Connection connection = Conexion.getConnection();

			PreparedStatement ps = connection
					.prepareStatement("SELECT a.* FROM aula as a JOIN clase as c ON a.id = c.id_aula GROUP BY a.id");

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int idAula = rs.getInt("id");
				int aforoAula = rs.getInt("aforo");
				boolean estadoAula = rs.getBoolean("estado");
				aulas.add(new TAula(idAula, aforoAula, estadoAula));
			}

			connection.close();
		} catch (SQLException ex) {
			System.out.println(ex);
		}
		return aulas;
		// end-user-code
	}

	@Override
	public List<TAula> readAllUnavaliable() {
		List<TAula> aulas = new Vector<TAula>();
		try {
			Connection connection = Conexion.getConnection();

			PreparedStatement ps = connection.prepareStatement("select * from aula where estado = false");

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int idAula = rs.getInt("id");
				int aforoAula = rs.getInt("aforo");
				boolean estadoAula = rs.getBoolean("estado");
				aulas.add(new TAula(idAula, aforoAula, estadoAula));
			}

			connection.close();
		} catch (SQLException ex) {
			System.out.println(ex);
		}
		return aulas;
	}

	@Override
	public boolean reactivate(int idAula) {
		// begin-user-code
		try {
			Connection connection = Conexion.getConnection();

			PreparedStatement ps = connection.prepareStatement("update aula set estado = true where id = ?");
			ps.setInt(1, idAula);

			ps.executeUpdate();

			connection.close();
			return true;
		} catch (SQLException ex) {
			System.out.println(ex);
			return false;
		}
		// end-user-code
	}
}