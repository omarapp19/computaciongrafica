package proyecto;

import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ProfesoresView extends JInternalFrame {

    private JTextField txtNombre, txtApellido, txtCedula, txtNivelProfesor;
    private JComboBox<String> cmbDia, cmbHora;
    private JTextField txtNivelClase; // El nivel que dará en esa hora
    private JTable tablaProfesores;
    private DefaultTableModel modeloTabla;

    public ProfesoresView() {
        super("Gestión de Profesores y Horarios", true, true, true, true);
        setSize(900, 600);
        inicializarBaseDeDatos(); // Crea las tablas si no existen
        initComponents();
        cargarProfesores();
    }

    private void inicializarBaseDeDatos() {
        try (Connection conn = Conexion.getConexion(); Statement stmt = conn.createStatement()) {
            // Tabla Profesores
            stmt.execute("CREATE TABLE IF NOT EXISTS profesores ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "nombre TEXT, apellido TEXT, cedula TEXT, nivel TEXT)");
            
            // Tabla Horarios
            stmt.execute("CREATE TABLE IF NOT EXISTS horarios ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "profesor_id INTEGER, dia TEXT, hora INTEGER, nivel_clase TEXT, "
                    + "FOREIGN KEY(profesor_id) REFERENCES profesores(id))");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void initComponents() {
        JPanel panelFormulario = new JPanel(new GridLayout(2, 1)); // Dividido en Datos Profe y Datos Horario
        
        // --- PANEL DATOS PROFESOR ---
        JPanel panelProfe = new JPanel(new GridLayout(5, 2, 5, 5));
        panelProfe.setBorder(BorderFactory.createTitledBorder("1. Registrar Profesor"));
        
        txtNombre = new JTextField();
        txtApellido = new JTextField();
        txtCedula = new JTextField();
        txtNivelProfesor = new JTextField(); // Ej: Cinturón Negro 2do Dan
        
        panelProfe.add(new JLabel("Nombre:")); panelProfe.add(txtNombre);
        panelProfe.add(new JLabel("Apellido:")); panelProfe.add(txtApellido);
        panelProfe.add(new JLabel("Cédula:")); panelProfe.add(txtCedula);
        panelProfe.add(new JLabel("Nivel (Dan/Cinturón):")); panelProfe.add(txtNivelProfesor);
        
        JButton btnGuardarProfe = new JButton("Guardar Profesor");
        btnGuardarProfe.addActionListener(e -> guardarProfesor());
        panelProfe.add(new JLabel("")); panelProfe.add(btnGuardarProfe);

        // --- PANEL ASIGNAR HORARIO ---
        JPanel panelHorario = new JPanel(new GridLayout(5, 2, 5, 5));
        panelHorario.setBorder(BorderFactory.createTitledBorder("2. Asignar Clase a Profesor Seleccionado"));
        
        cmbDia = new JComboBox<>(new String[]{"Lunes", "Martes", "Miercoles", "Jueves", "Viernes"});
        
        // Horas de 8 a 17 (5 PM)
        String[] horas = new String[10];
        for(int i=0; i<10; i++) horas[i] = (i+8) + ":00";
        cmbHora = new JComboBox<>(horas);
        
        txtNivelClase = new JTextField(); // Ej: Principiantes, Avanzados
        
        JButton btnAsignarHorario = new JButton("Asignar Horario");
        btnAsignarHorario.addActionListener(e -> asignarHorario());

        panelHorario.add(new JLabel("Día:")); panelHorario.add(cmbDia);
        panelHorario.add(new JLabel("Hora Inicio:")); panelHorario.add(cmbHora);
        panelHorario.add(new JLabel("Nivel/Grupo de la clase:")); panelHorario.add(txtNivelClase);
        panelHorario.add(new JLabel("")); panelHorario.add(btnAsignarHorario);

        panelFormulario.add(panelProfe);
        panelFormulario.add(panelHorario);

        // --- TABLA ---
        modeloTabla = new DefaultTableModel(new String[]{"ID", "Nombre", "Apellido", "Nivel"}, 0);
        tablaProfesores = new JTable(modeloTabla);
        
        setLayout(new BorderLayout());
        add(panelFormulario, BorderLayout.WEST);
        add(new JScrollPane(tablaProfesores), BorderLayout.CENTER);
    }

    private void guardarProfesor() {
        String sql = "INSERT INTO profesores(nombre, apellido, cedula, nivel) VALUES(?,?,?,?)";
        try (Connection conn = Conexion.getConexion(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, txtNombre.getText());
            pstmt.setString(2, txtApellido.getText());
            pstmt.setString(3, txtCedula.getText());
            pstmt.setString(4, txtNivelProfesor.getText());
            pstmt.executeUpdate();
            cargarProfesores();
            JOptionPane.showMessageDialog(this, "Profesor guardado.");
            limpiarCamposProfe();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private void asignarHorario() {
        int fila = tablaProfesores.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un profesor de la tabla primero.");
            return;
        }
        int idProfe = Integer.parseInt(modeloTabla.getValueAt(fila, 0).toString());
        String horaStr = (String) cmbHora.getSelectedItem();
        int horaInt = Integer.parseInt(horaStr.split(":")[0]); // Extraer solo el 8, 9, etc.

        String sql = "INSERT INTO horarios(profesor_id, dia, hora, nivel_clase) VALUES(?,?,?,?)";
        try (Connection conn = Conexion.getConexion(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idProfe);
            pstmt.setString(2, (String) cmbDia.getSelectedItem());
            pstmt.setInt(3, horaInt);
            pstmt.setString(4, txtNivelClase.getText());
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Clase asignada correctamente.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al asignar horario: " + e.getMessage());
        }
    }

    private void cargarProfesores() {
        modeloTabla.setRowCount(0);
        try (Connection conn = Conexion.getConexion(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery("SELECT * FROM profesores")) {
            while (rs.next()) {
                modeloTabla.addRow(new Object[]{rs.getInt("id"), rs.getString("nombre"), rs.getString("apellido"), rs.getString("nivel")});
            }
        } catch (SQLException e) { e.printStackTrace(); }
    }
    
    private void limpiarCamposProfe() {
        txtNombre.setText(""); txtApellido.setText(""); txtCedula.setText(""); txtNivelProfesor.setText("");
    }
}