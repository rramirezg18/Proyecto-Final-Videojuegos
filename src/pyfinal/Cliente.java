package pyfinal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Cliente {
	private String nit;
	private String nombre;
	private String direccion;
	private String telefono;
	public Cliente(String nit, String nombre, String direccion, String telefono) {
		super();
		this.nit = nit;
		this.nombre = nombre;
		this.direccion = direccion;
		this.telefono = telefono;
	}
	public String getNit() {
		return nit;
	}
	public void setNit(String nit) {
		this.nit = nit;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
		public void registrarCliente() {
		    try (Connection connection = BaseDeDatos.getConnection()) {
		        String query = "INSERT INTO clientes (nit, nombre, direccion, telefono) VALUES (?, ?, ?, ?)";
		        PreparedStatement preparedStatement = connection.prepareStatement(query);
		        preparedStatement.setString(1, getNit());
		        preparedStatement.setString(2, getNombre());
		        preparedStatement.setString(3, getDireccion());
		        preparedStatement.setString(4, getTelefono());

		        int rowsAffected = preparedStatement.executeUpdate();

		        if (rowsAffected > 0) {
		            //System.out.println("Cliente registrado exitosamente.");
		        } else {
		            //System.out.println("No se pudo registrar el cliente.");
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		}

	
	public static void eliminarCliente(String nit) {
        try (Connection connection = BaseDeDatos.getConnection()) {
            String deleteQuery = "DELETE FROM clientes WHERE nit = ?";
            PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery);
            deleteStatement.setString(1, nit);
            int rowsAffected = deleteStatement.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Cliente eliminado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Error al eliminar el cliente.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
	

}
