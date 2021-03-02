/**
 * 
 */
package integracion.cliente.imp;

import java.sql.*;
import java.util.List;
import java.util.Vector;

import integracion.Conexion;
import integracion.cliente.DAOCliente;
import negocio.cliente.TCliente;

public class DAOClienteImp implements DAOCliente {

	public Integer create(TCliente tCliente) {
		// begin-user-code
		int id = -1;
		try {
			Connection connection = Conexion.getConnection();

			PreparedStatement ps = connection.prepareStatement(
					"insert into cliente (dni, nombre, telefono, cuenta_bancaria, domicilio, correo_electronico) values (?, ?, ?, ?, ?, ?)",
					java.sql.Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, tCliente.getDni());
			ps.setString(2, tCliente.getNombre());
			ps.setString(3, tCliente.getTelefono());
			ps.setString(4, tCliente.getCuentaBancaria());
			ps.setString(5, tCliente.getDomicilio());
			ps.setString(6, tCliente.getCorreo());

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

	public Boolean update(TCliente tCliente) {
		// begin-user-code
		try {
			Connection connection = Conexion.getConnection();

			PreparedStatement ps = connection.prepareStatement(
					"update cliente set dni = ?, nombre = ?, telefono = ?, cuenta_bancaria = ?, domicilio = ?, correo_electronico = ? where id = ?");
			ps.setString(1, tCliente.getDni());
			ps.setString(2, tCliente.getNombre());
			ps.setString(3, tCliente.getTelefono());
			ps.setString(4, tCliente.getCuentaBancaria());
			ps.setString(5, tCliente.getDomicilio());
			ps.setString(6, tCliente.getCorreo());
			ps.setInt(7, tCliente.getId());

			ps.executeUpdate();

			connection.close();
			return true;
		} catch (SQLException ex) {
			System.out.println(ex);
			return false;
		}
		// end-user-code
	}

	public Boolean delete(Integer id) {
		// begin-user-code
		try {
			Connection connection = Conexion.getConnection();

			PreparedStatement ps = connection.prepareStatement("update cliente set estado = false where id = ?");
			ps.setInt(1, id);

			ps.executeUpdate();

			connection.close();
			return true;
		} catch (SQLException ex) {
			return false;
		}
		// end-user-code
	}

	public TCliente readById(Integer id) {
		// begin-user-code
		TCliente cliente = null;
		try {
			Connection connection = Conexion.getConnection();

			PreparedStatement ps = connection.prepareStatement("select * from cliente where id = ?");
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			while (rs.next())
				cliente = new TCliente(rs.getInt("id"), rs.getString("nombre"), rs.getString("dni"),
						rs.getString("cuenta_bancaria"), rs.getString("telefono"), rs.getString("domicilio"),
						rs.getString("correo_electronico"), rs.getBoolean("estado"));

			connection.close();
		} catch (SQLException ex) {
			System.out.println(ex);
		}
		return cliente;
		// end-user-code
	}

	public List<TCliente> readAll() {
		// begin-user-code
		List<TCliente> clientes = new Vector<TCliente>();
		try {
			Connection connection = Conexion.getConnection();

			PreparedStatement ps = connection.prepareStatement("select * from cliente");

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int idCliente = rs.getInt("id");
				String dniCliente = rs.getString("dni");
				String nombreCliente = rs.getString("nombre");
				String telefonoCliente = rs.getString("telefono");
				String cuentaBancariaCliente = rs.getString("cuenta_bancaria");
				String domicilioCliente = rs.getString("domicilio");
				String correoCliente = rs.getString("correo_electronico");
				boolean estadoCliente = rs.getBoolean("estado");
				clientes.add(new TCliente(idCliente, nombreCliente, dniCliente, cuentaBancariaCliente, telefonoCliente,
						domicilioCliente, correoCliente, estadoCliente));
			}

			connection.close();
		} catch (SQLException ex) {
			System.out.println(ex);
		}
		return clientes;
		// end-user-code
	}

	public List<TCliente> readAllUnavailable() {
		// begin-user-code
		List<TCliente> clientes = new Vector<TCliente>();
		try {
			Connection connection = Conexion.getConnection();

			PreparedStatement ps = connection.prepareStatement("select * from cliente where estado = false");

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int idCliente = rs.getInt("id");
				String dniCliente = rs.getString("dni");
				String nombreCliente = rs.getString("nombre");
				String telefonoCliente = rs.getString("telefono");
				String cuentaBancariaCliente = rs.getString("cuenta_bancaria");
				String domicilioCliente = rs.getString("domicilio");
				String correoCliente = rs.getString("correo_electronico");
				boolean estadoCliente = rs.getBoolean("estado");
				clientes.add(new TCliente(idCliente, nombreCliente, dniCliente, cuentaBancariaCliente, telefonoCliente,
						domicilioCliente, correoCliente, estadoCliente));
			}

			connection.close();
		} catch (SQLException ex) {
			System.out.println(ex);
		}
		return clientes;
		// end-user-code
	}

	public Boolean reactivate(Integer idCliente) {
		// begin-user-code
		try {
			Connection connection = Conexion.getConnection();

			PreparedStatement ps = connection.prepareStatement("update cliente set estado = true where id = ?");
			ps.setInt(1, idCliente);

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