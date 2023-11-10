package pyfinal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class ViedoJuego extends producto implements Gestion {
	
	public ViedoJuego(int codigo, String descripcion, String tipo, int cantidad, float precio) {
	    super(codigo, descripcion, tipo, cantidad, precio);
	}


	 public void agregarBD() {
	        try (Connection connection = BaseDeDatos.getConnection()) {
	            String query = "INSERT INTO productos (codigo, descripcion, tipo, cantidad, precio) VALUES (?, ?, ?, ?, ?)";
	            PreparedStatement preparedStatement = connection.prepareStatement(query);
	            preparedStatement.setInt(1, getCodigo());
	            preparedStatement.setString(2, getDescripcion());
	            preparedStatement.setString(3, getTipo());
	            preparedStatement.setInt(4, getCantidad());
	            preparedStatement.setFloat(5, getPrecio());

	            int rowsAffected = preparedStatement.executeUpdate();

	            if (rowsAffected > 0) {
	                //System.out.println("Producto agregado a la base de datos.");
	            } else {
	                //System.out.println("No se pudo agregar el producto a la base de datos.");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

}
