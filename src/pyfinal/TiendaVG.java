/**Proyecto Final - Gestion De Tienda De Videojuegos
 *
 *Clase principal del proyecto
 *
 * @author Roberto Ramírez 7690-22-12700
 */

package pyfinal;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TiendaVG {
    private static JTextField codigoField;
    private static JTextField descripcionField;
    private static JTextField tipoField;
    private static JTextField cantidadField;
    private static JTextField precioField;
    private static JTable productosTable;
    private static DefaultTableModel tableModel;
    private static JTextField codigoFieldBusqueda;
    private static JTextField descripcionFieldBusqueda;	

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            crearVentanaInicioSesion();
        });
    }

    private static void crearVentanaInicioSesion() {
        JFrame frame = new JFrame("Inicio de Sesión");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 700);

        JPanel panel = new JPanel(new GridBagLayout());
        frame.add(panel);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(5, 5, 5, 5);

        JLabel usuarioLabel = new JLabel("Usuario:");
        JTextField usuarioField = new JTextField();
        JLabel contraseñaLabel = new JLabel("Contraseña:");
        JPasswordField contraseñaField = new JPasswordField();
        JButton ingresarButton = new JButton("Ingresar");
        JButton salirButton = new JButton("Salir");

        //constraints.gridx = 0;
        //constraints.gridy = 0;
        constraints.gridwidth = 1;
        panel.add(usuarioLabel, constraints);

        //constraints.gridx = 1;
        //constraints.gridy = 0;
        constraints.gridwidth = 2;
        panel.add(usuarioField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        panel.add(contraseñaLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        panel.add(contraseñaField, constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        panel.add(ingresarButton, constraints);

        constraints.gridx = 2;
        constraints.gridy = 2;
        panel.add(salirButton, constraints);

        ingresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = usuarioField.getText();
                String contraseña = new String(contraseñaField.getPassword());

                if (usuario.equals("rramirez") && contraseña.equals("1234")) {
                    frame.dispose();
                    crearVentanaTienda();
                } else {
                    JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        frame.setVisible(true);
    }


    private static void crearVentanaTienda() {
        JFrame frame = new JFrame("Tienda");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 700);

        JPanel panel = new JPanel(new BorderLayout());

        JPanel welcomePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel welcomeLabel = new JLabel("Bienvenido");
        //JLabel storeLabel = new JLabel("Tienda De VideoJuegos");
        welcomeLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        //storeLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        welcomePanel.add(welcomeLabel);
        //welcomePanel.add(storeLabel);

        
        JPanel imagePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        ImageIcon imagen = new ImageIcon("D:\\Eclipse\\ProyectoViedoJuegos\\Pyimagen.jpg");
        JLabel imagenLabel = new JLabel(imagen);
        imagePanel.add(imagenLabel);

        // Panel para los botones
        JPanel buttonPanel = new JPanel(new GridLayout(2, 3));
        buttonPanel.setPreferredSize(new Dimension(700, 200)); 

        JButton productosButton = new JButton("Productos");
        JButton actualizarProductoButton = new JButton("Actualizar Producto");
        JButton consultaEliminarButton = new JButton("Consulta y Elimina");
        JButton clientesButton = new JButton("Clientes");
        JButton ventasButton = new JButton("Ventas");
        JButton salirButton = new JButton("Salir");

        // Ajusta el tamaño de los botones
        Dimension buttonSize = new Dimension(150, 50); 
        productosButton.setPreferredSize(buttonSize);
        actualizarProductoButton.setPreferredSize(buttonSize);
        consultaEliminarButton.setPreferredSize(buttonSize);
        clientesButton.setPreferredSize(buttonSize);
        ventasButton.setPreferredSize(buttonSize);
        salirButton.setPreferredSize(buttonSize);

        buttonPanel.add(productosButton);
        buttonPanel.add(actualizarProductoButton);
        buttonPanel.add(consultaEliminarButton);
        buttonPanel.add(clientesButton);
        buttonPanel.add(ventasButton);
        buttonPanel.add(salirButton);

        // Agrega los paneles al panel principal
        panel.add(welcomePanel, BorderLayout.NORTH);
        panel.add(imagePanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Cierra la aplicación
            }
        });

        productosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                crearVentanaProductos();
            }
        });

        actualizarProductoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                crearVentanaActualizarProducto();
            }
        });

        consultaEliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                crearVentanaConsultaEliminar();
            }
        });

        clientesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                crearVentanaClientes();
            }
        });

        ventasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                crearVentanaVentas();
            }
        });

        frame.add(panel);
        frame.setVisible(true);
    }

    
    private static void crearVentanaVentas() {
    	JFrame frame = new JFrame("Ventas");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        frame.setSize(1200, 700);

        JPanel panel = new JPanel(new BorderLayout());
        frame.add(panel);

        JPanel inputPanel = new JPanel(new GridLayout(5, 2));

        JLabel clienteLabel = new JLabel("Seleccionar Cliente:");
        JComboBox<String> clientesComboBox = new JComboBox<>();
        JLabel productoLabel = new JLabel("Seleccionar Producto:");
        JComboBox<String> productosComboBox = new JComboBox<>();
        JLabel cantidadLabel = new JLabel("Cantidad:");
        JTextField cantidadField = new JTextField();
        JLabel totalLabel = new JLabel("Total:");
        JTextField totalField = new JTextField();

        cargarClientesDesdeBD(clientesComboBox);
        cargarProductosDesdeBD(productosComboBox);

        inputPanel.add(clienteLabel);
        inputPanel.add(clientesComboBox);
        inputPanel.add(productoLabel);
        inputPanel.add(productosComboBox);
        inputPanel.add(cantidadLabel);
        inputPanel.add(cantidadField);
        inputPanel.add(totalLabel);
        inputPanel.add(totalField);

        panel.add(inputPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(); 
        JButton agregarVentaButton = new JButton("Agregar Venta");
        JButton salirButton = new JButton("Salir"); 
        JButton regresarButton = new JButton("Regresar"); 

        buttonPanel.add(agregarVentaButton);
        buttonPanel.add(salirButton);
        buttonPanel.add(regresarButton);

        agregarVentaButton.setPreferredSize(new Dimension(200, 100));
        regresarButton.setPreferredSize(new Dimension(200, 100));
        salirButton.setPreferredSize(new Dimension(200, 100));
        panel.add(buttonPanel, BorderLayout.SOUTH);

        agregarVentaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String clienteSeleccionado = (String) clientesComboBox.getSelectedItem();
                String productoSeleccionado = (String) productosComboBox.getSelectedItem();
                String cantidad = cantidadField.getText();
                
                
                float precioProducto = obtenerPrecioProductoDesdeBD(productoSeleccionado);
                
                // Calcular el total
                try {
                    int cantidadInt = Integer.parseInt(cantidad);
                    float total = precioProducto * cantidadInt;
                    totalField.setText(String.valueOf(total));
                    
                    // Insertar la venta en la base de datos
                    if (insertarVentaEnBD(clienteSeleccionado, productoSeleccionado, cantidadInt, total)) {
                        JOptionPane.showMessageDialog(null, "Venta registrada exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al registrar la venta", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    totalField.setText("Error");
                }
            }
        });
        
        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        regresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Cierra la ventana actual
                crearVentanaTienda();
               
            }
        });

        frame.setVisible(true);
    }
    
    private static boolean insertarVentaEnBD(String cliente, String producto, int cantidad, float total) {
        try (Connection connection = BaseDeDatos.getConnection()) {
            // Obtener el ID del cliente seleccionado
            String clienteQuery = "SELECT nit FROM clientes WHERE nombre = ?";
            PreparedStatement clienteStatement = connection.prepareStatement(clienteQuery);
            clienteStatement.setString(1, cliente);
            ResultSet clienteResult = clienteStatement.executeQuery();

            int nitCliente = 0;
            if (clienteResult.next()) {
                nitCliente = clienteResult.getInt("nit");
            } else {
                return false; //Si no se encuentra el cliente
            }

            // Obtener el id del producto seleccionado y la cantidad actual
            String productoQuery = "SELECT codigo, cantidad FROM productos WHERE descripcion = ?";
            PreparedStatement productoStatement = connection.prepareStatement(productoQuery);
            productoStatement.setString(1, producto);
            ResultSet productoResult = productoStatement.executeQuery();

            int codigoProducto = 0;
            int cantidadProducto = 0;
            if (productoResult.next()) {
                codigoProducto = productoResult.getInt("codigo");
                cantidadProducto = productoResult.getInt("cantidad");
            } else {
                return false; // Si no encuentra el producto
            }

            if (cantidad <= cantidadProducto) {
                // Restar la cantidad vendida de la db
                String actualizarStockQuery = "UPDATE productos SET cantidad = ? WHERE codigo = ?";
                PreparedStatement actualizarStockStatement = connection.prepareStatement(actualizarStockQuery);
                actualizarStockStatement.setInt(1, cantidadProducto - cantidad);
                actualizarStockStatement.setInt(2, codigoProducto);
                actualizarStockStatement.executeUpdate();

                // Insertar la venta en bd
                String ventaQuery = "INSERT INTO ventas (cliente_nit, producto_codigo, cantidad, total) VALUES (?, ?, ?, ?)";
                PreparedStatement ventaStatement = connection.prepareStatement(ventaQuery);
                ventaStatement.setInt(1, nitCliente);
                ventaStatement.setInt(2, codigoProducto);
                ventaStatement.setInt(3, cantidad);
                ventaStatement.setFloat(4, total);

                int rowsAffected = ventaStatement.executeUpdate();

                return rowsAffected > 0; //si se logra insertar devuelve true
            } else {
                //Si no hay cantidad suficiente devuelve falso
                return false;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }


    
    public static float obtenerPrecioProductoDesdeBD(String productoSeleccionado) {
        try (Connection connection = BaseDeDatos.getConnection()) {
            String query = "SELECT precio FROM productos WHERE descripcion = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, productoSeleccionado);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getFloat("precio");
            } else {
                return 0.0f; //precio por defecto
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0.0f;
        }
    }

   
    private static void cargarClientesDesdeBD(JComboBox<String> comboBox) {
        try (Connection connection = BaseDeDatos.getConnection()) {
            String query = "SELECT nombre FROM clientes";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String nombreCliente = resultSet.getString("nombre");
                comboBox.addItem(nombreCliente);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private static void cargarProductosDesdeBD(JComboBox<String> comboBox) {
        try (Connection connection = BaseDeDatos.getConnection()) {
            String query = "SELECT descripcion FROM productos";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String descripcionProducto = resultSet.getString("descripcion");
                comboBox.addItem(descripcionProducto);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    
    private static void crearVentanaProductos() {
        JFrame frame = new JFrame("Gestión de Productos");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 700);
        JPanel panel = new JPanel();
        frame.add(panel);
        panel.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(7, 2));

        JLabel codigoLabel = new JLabel("Código:");
        codigoField = new JTextField();
        JLabel descripcionLabel = new JLabel("Descripción:");
        descripcionField = new JTextField();
        JLabel tipoLabel = new JLabel("Tipo:");
        tipoField = new JTextField();
        JLabel cantidadLabel = new JLabel("Cantidad:");
        cantidadField = new JTextField();
        JLabel precioLabel = new JLabel("Precio:");
        precioField = new JTextField();

        inputPanel.add(codigoLabel);
        inputPanel.add(codigoField);
        inputPanel.add(descripcionLabel);
        inputPanel.add(descripcionField);
        inputPanel.add(tipoLabel);
        inputPanel.add(tipoField);
        inputPanel.add(cantidadLabel);
        inputPanel.add(cantidadField);
        inputPanel.add(precioLabel);
        inputPanel.add(precioField);

        panel.add(inputPanel, BorderLayout.NORTH);

        productosTable = new JTable();
        JScrollPane tableScrollPane = new JScrollPane(productosTable);
        panel.add(tableScrollPane, BorderLayout.CENTER);

        JButton agregarButton = new JButton("Agregar");

        // Botones de "Regresar" y "Salir"
        JButton regresarButton = new JButton("Regresar");
        JButton salirButton = new JButton("Salir");

        // Panel para los botones "Agregar," "Regresar" y "Salir"
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(agregarButton);
        buttonPanel.add(regresarButton);
        buttonPanel.add(salirButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int codigo = Integer.parseInt(codigoField.getText());
                String descripcion = descripcionField.getText();
                String tipo = tipoField.getText();
                int cantidad = Integer.parseInt(cantidadField.getText());
                float precio = Float.parseFloat(precioField.getText());
                
                ViedoJuego videoJuego = new ViedoJuego(codigo, descripcion, tipo, cantidad, precio);
                videoJuego.agregarBD();

                codigoField.setText("");
                descripcionField.setText("");
                tipoField.setText("");
                cantidadField.setText("");
                precioField.setText("");

                actualizarListaProductos();
            }

        });

        regresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	frame.dispose();
            	crearVentanaTienda();
                
            }
        });

        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        tableModel = new DefaultTableModel();
        tableModel.addColumn("Código");
        tableModel.addColumn("Descripción");
        tableModel.addColumn("Tipo");
        tableModel.addColumn("Cantidad");
        tableModel.addColumn("Precio");

        //Asociar el modelo de la tabla
        productosTable.setModel(tableModel);

        frame.setVisible(true);

        actualizarListaProductos();
    }

    private static void actualizarListaProductos() {
        tableModel.setRowCount(0); //Resetea la tabla antes de cargar lo datos

        try (Connection connection = BaseDeDatos.getConnection()) {
            Statement statement = connection.createStatement();
            String query = "SELECT codigo, descripcion, tipo, cantidad, precio FROM productos";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int codigo = resultSet.getInt("codigo");
                String descripcion = resultSet.getString("descripcion");
                String tipo = resultSet.getString("tipo");
                int cantidad = resultSet.getInt("cantidad");
                float precio = resultSet.getFloat("precio");

                tableModel.addRow(new Object[]{codigo, descripcion, tipo, cantidad, precio});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void crearVentanaActualizarProducto() {
        JFrame frame = new JFrame("Actualizar Producto");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        frame.setSize(1200, 700);

        JPanel panel = new JPanel();
        frame.add(panel);
        panel.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(5, 2));

        JLabel codigoLabel = new JLabel("Código:");
        JTextField codigoActualizarField = new JTextField();
        JLabel descripcionLabel = new JLabel("Descripción:");
        JTextField descripcionActualizarField = new JTextField();
        JLabel tipoLabel = new JLabel("Tipo:");
        JTextField tipoActualizarField = new JTextField();
        JLabel cantidadLabel = new JLabel("Cantidad:");
        JTextField cantidadActualizarField = new JTextField();
        JLabel precioLabel = new JLabel("Precio:");
        JTextField precioActualizarField = new JTextField();

        inputPanel.add(codigoLabel);
        inputPanel.add(codigoActualizarField);
        inputPanel.add(descripcionLabel);
        inputPanel.add(descripcionActualizarField);
        inputPanel.add(tipoLabel);
        inputPanel.add(tipoActualizarField);
        inputPanel.add(cantidadLabel);
        inputPanel.add(cantidadActualizarField);
        inputPanel.add(precioLabel);
        inputPanel.add(precioActualizarField);

        panel.add(inputPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(); 
        JButton regresarButton = new JButton("Regresar");
        JButton actualizarProductoButton = new JButton("Actualizar Producto");
        JButton salirButton = new JButton("Salir"); // Botón para salir

       
        Dimension buttonSize = new Dimension(200, 100);
        regresarButton.setPreferredSize(buttonSize);
        actualizarProductoButton.setPreferredSize(buttonSize);
        salirButton.setPreferredSize(buttonSize);

        buttonPanel.add(regresarButton);
        buttonPanel.add(actualizarProductoButton);
        buttonPanel.add(salirButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        actualizarProductoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              
                int codigo = Integer.parseInt(codigoActualizarField.getText());
                String descripcion = descripcionActualizarField.getText();
                String tipo = tipoActualizarField.getText();
                int cantidad = Integer.parseInt(cantidadActualizarField.getText());
                float precio = Float.parseFloat(precioActualizarField.getText());

                try (Connection connection = BaseDeDatos.getConnection()) {
                    Statement statement = connection.createStatement();
                    String query = "SELECT codigo FROM productos WHERE codigo = " + codigo;
                    ResultSet resultSet = statement.executeQuery(query);

                    if (resultSet.next()) {
                        
                        String updateQuery = "UPDATE productos SET descripcion = ?, tipo = ?, cantidad = ?, precio = ? WHERE codigo = ?";
                        PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
                        updateStatement.setString(1, descripcion);
                        updateStatement.setString(2, tipo);
                        updateStatement.setInt(3, cantidad);
                        updateStatement.setFloat(4, precio);
                        updateStatement.setInt(5, codigo);
                        int rowsAffected = updateStatement.executeUpdate();

                        if (rowsAffected > 0) {
                            JOptionPane.showMessageDialog(null, "Producto actualizado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "Error al actualizar el producto", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        
                        String insertQuery = "INSERT INTO productos (codigo, descripcion, tipo, cantidad, precio) VALUES (?, ?, ?, ?, ?)";
                        PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
                        insertStatement.setInt(1, codigo);
                        insertStatement.setString(2, descripcion);
                        insertStatement.setString(3, tipo);
                        insertStatement.setInt(4, cantidad);
                        insertStatement.setFloat(5, precio);
                        int rowsAffected = insertStatement.executeUpdate();

                        if (rowsAffected > 0) {
                            JOptionPane.showMessageDialog(null, "Producto agregado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "Error al agregar el producto", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                
                codigoActualizarField.setText("");
                descripcionActualizarField.setText("");
                tipoActualizarField.setText("");
                cantidadActualizarField.setText("");
                precioActualizarField.setText("");
            }
        });

        regresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); 
                crearVentanaTienda();
                
            }
        });

        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); 
            }
        });

        frame.setVisible(true);
    }


    
    private static void crearVentanaConsultaEliminar() {
        JFrame frame = new JFrame("Consulta y Eliminación de Productos");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        frame.setSize(1200, 700);

        JPanel panel = new JPanel();
        frame.add(panel);
        panel.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(4, 2)); 

        JLabel buscarCodigoLabel = new JLabel("Buscar por Código:");
        JLabel buscarDescripcionLabel = new JLabel("Buscar por Descripción:");
        codigoFieldBusqueda = new JTextField();
        descripcionFieldBusqueda = new JTextField();
        JButton buscarButton = new JButton("Buscar");

        inputPanel.add(buscarCodigoLabel);
        inputPanel.add(codigoFieldBusqueda);
        inputPanel.add(buscarDescripcionLabel);
        inputPanel.add(descripcionFieldBusqueda);
        inputPanel.add(new JLabel()); 
        inputPanel.add(new JLabel()); 
        inputPanel.add(buscarButton);

        panel.add(inputPanel, BorderLayout.NORTH);

        
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Código");
        tableModel.addColumn("Descripción");
        tableModel.addColumn("Tipo");
        tableModel.addColumn("Cantidad");
        tableModel.addColumn("Precio");

        productosTable = new JTable(tableModel); 
        JScrollPane tableScrollPane = new JScrollPane(productosTable);
        panel.add(tableScrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton eliminarButton = new JButton("Eliminar Producto");
        JButton regresarButton = new JButton("Regresar");
        JButton salirButton = new JButton("Salir");

        
        Dimension buttonSize = new Dimension(200, 100);
        eliminarButton.setPreferredSize(buttonSize);
        regresarButton.setPreferredSize(buttonSize);
        salirButton.setPreferredSize(buttonSize);

        buttonPanel.add(eliminarButton);
        buttonPanel.add(regresarButton);
        buttonPanel.add(salirButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarProductos();
            }
        });

        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarProductoSeleccionado();
            }
        });

        regresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); 
                crearVentanaTienda();
                
            }
        });

        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); 
            }
        });

        frame.setVisible(true);
    }

    
    private static void crearVentanaClientes() {
        JFrame frame = new JFrame("Gestión de Clientes");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 700);

        JPanel panel = new JPanel();
        frame.add(panel);
        panel.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(5, 2));

        JLabel nitLabel = new JLabel("NIT:");
        JTextField nitField = new JTextField();
        JLabel nombreLabel = new JLabel("Nombre:");
        JTextField nombreField = new JTextField();
        JLabel direccionLabel = new JLabel("Dirección:");
        JTextField direccionField = new JTextField();
        JLabel telefonoLabel = new JLabel("Teléfono:");
        JTextField telefonoField = new JTextField();

        inputPanel.add(nitLabel);
        inputPanel.add(nitField);
        inputPanel.add(nombreLabel);
        inputPanel.add(nombreField);
        inputPanel.add(direccionLabel);
        inputPanel.add(direccionField);
        inputPanel.add(telefonoLabel);
        inputPanel.add(telefonoField);

       
        JButton registrarClienteButton = new JButton("Registrar Cliente");
        inputPanel.add(new JLabel());
        inputPanel.add(registrarClienteButton);

        panel.add(inputPanel, BorderLayout.NORTH);

       
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("NIT");
        tableModel.addColumn("Nombre");
        tableModel.addColumn("Dirección");
        tableModel.addColumn("Teléfono");

        
        JTable clientesTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(clientesTable);
        panel.add(tableScrollPane, BorderLayout.CENTER);

        
        JButton eliminarClienteButton = new JButton("Eliminar Cliente");

        
        JButton regresarButton = new JButton("Regresar");
        JButton salirButton = new JButton("Salir");

        
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(eliminarClienteButton);
        buttonPanel.add(regresarButton);
        buttonPanel.add(salirButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        
        registrarClienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nit = nitField.getText();
                String nombre = nombreField.getText();
                String direccion = direccionField.getText();
                String telefono = telefonoField.getText();

                
                
                nitField.setText("");
                nombreField.setText("");
                direccionField.setText("");
                telefonoField.setText("");

                Cliente cliente = new Cliente(nit, nombre, direccion, telefono);
                cliente.registrarCliente();
                
                actualizarListaClientes(tableModel);
            }
        });

        
        eliminarClienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int filaSeleccionada = clientesTable.getSelectedRow();

                if (filaSeleccionada == -1) {
                    JOptionPane.showMessageDialog(null, "Por favor, selecciona un cliente para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String nit = (String) tableModel.getValueAt(filaSeleccionada, 0);

                
                Cliente.eliminarCliente(nit);

                
                actualizarListaClientes(tableModel);
            }
        });

        
        regresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	frame.dispose();
            	crearVentanaTienda();
                
            }
        });

        
        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); 
            }
        });

        
        actualizarListaClientes(tableModel);

        frame.setVisible(true);
    }


    
    private static void actualizarListaClientes(DefaultTableModel tableModel) {
        
        tableModel.setRowCount(0);

        try (Connection connection = BaseDeDatos.getConnection()) {
            Statement statement = connection.createStatement();
            String query = "SELECT nit, nombre, direccion, telefono FROM clientes";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String nit = resultSet.getString("nit");
                String nombre = resultSet.getString("nombre");
                String direccion = resultSet.getString("direccion");
                String telefono = resultSet.getString("telefono");

                
                tableModel.addRow(new Object[]{nit, nombre, direccion, telefono});
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }



    


    
    
    
    
    private static void eliminarProductoSeleccionado() {
        int filaSeleccionada = productosTable.getSelectedRow();
        
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(null, "Por favor, selecciona un producto para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
            return; 
        }

        int codigo = (int) productosTable.getValueAt(filaSeleccionada, 0);

        try (Connection connection = BaseDeDatos.getConnection()) {
            String deleteQuery = "DELETE FROM productos WHERE codigo = ?";
            PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery);
            deleteStatement.setInt(1, codigo);
            int rowsAffected = deleteStatement.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Producto eliminado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                
                actualizarListaProductos();
            } else {
                JOptionPane.showMessageDialog(null, "Error al eliminar el producto.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    
    
    private static void buscarProductos() {
    	String codigoABuscar = codigoFieldBusqueda.getText().trim();
        String descripcionABuscar = descripcionFieldBusqueda.getText().trim();

        String query;
        if (!codigoABuscar.isEmpty()) {
            
            query = "SELECT codigo, descripcion, tipo, cantidad, precio FROM productos WHERE codigo = " + codigoABuscar;
        } else if (!descripcionABuscar.isEmpty()) {
            
            query = "SELECT codigo, descripcion, tipo, cantidad, precio FROM productos WHERE descripcion LIKE '%" + descripcionABuscar + "%'";
        } else {
            JOptionPane.showMessageDialog(null, "Por favor, ingresa un código o una descripción para buscar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        tableModel.setRowCount(0);

        try (Connection connection = BaseDeDatos.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int codigo = resultSet.getInt("codigo");
                String descripcion = resultSet.getString("descripcion");
                String tipo = resultSet.getString("tipo");
                int cantidad = resultSet.getInt("cantidad");
                float precio = resultSet.getFloat("precio");

                
                tableModel.addRow(new Object[]{codigo, descripcion, tipo, cantidad, precio});
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}



