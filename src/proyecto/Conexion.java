// Asegúrate de que esto coincida con tu paquete
package proyecto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Clase para manejar la conexión a la base de datos SQLite.
 * Utiliza el patrón Singleton para asegurar una única conexión.
 */
public class Conexion {

    // --- PASO 1: Definir la ubicación de la base de datos ---
    // "jdbc:sqlite:" es el protocolo.
    // "dojo.db" es el nombre del archivo que creamos.
    // NetBeans lo buscará en la raíz del proyecto (donde está el pom.xml o build.xml)
    private static final String URL = "jdbc:sqlite:dojo.db";

    // --- PASO 2: Variables del Singleton ---
    // 'instancia' guardará la única instancia de esta clase
    private static Conexion instancia;
    
    // 'connection' guardará la conexión activa a la BD
    private Connection connection;

    /**
     * Constructor privado para evitar que se creen instancias libremente.
     * Carga el driver de SQLite.
     */
    private Conexion() {
        try {
            // Cargar el driver JDBC de SQLite
            // Esto solo funciona si añadiste el archivo .JAR a "Libraries"
            Class.forName("org.sqlite.JDBC");
            
            // (La conexión real se hace en el método conectar())
            
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, 
                "Error: No se encontró el driver de SQLite (sqlite-jdbc.jar).\n" +
                "Asegúrate de añadir el .JAR a las 'Libraries' del proyecto.",
                "Error Crítico de Base de Datos", 
                JOptionPane.ERROR_MESSAGE);
            System.exit(1); // Cierra la aplicación si no hay driver
        }
    }

    /**
     * Método público estático para obtener la única instancia (Singleton).
     */
    public static Conexion getInstance() {
        // Si la instancia no existe, se crea
        if (instancia == null) {
            instancia = new Conexion();
        }
        return instancia;
    }

    /**
     * Intenta establecer la conexión con la base de datos.
     * @return La conexión (Connection) o null si falla.
     */
    public Connection conectar() {
        try {
            // Si la conexión está cerrada o es nula, crea una nueva
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(URL);
                System.out.println("Conexión a SQLite establecida con éxito.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, 
                "Error al conectar con la base de datos: " + e.getMessage(),
                "Error de Conexión", 
                JOptionPane.ERROR_MESSAGE);
        }
        return connection;
    }

    /**
     * Cierra la conexión a la base de datos.
     */
    public void desconectar() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Conexión a SQLite cerrada.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, 
                "Error al cerrar la conexión: " + e.getMessage(),
                "Error de Desconexión", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Método rápido para obtener la conexión ya establecida.
     * Es una combinación de getInstance() y conectar().
     * Este es el método que más usarás.
     */
    public static Connection getConexion() {
        return Conexion.getInstance().conectar();
    }

}
