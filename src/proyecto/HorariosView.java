package proyecto;

import java.awt.BorderLayout;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class HorariosView extends JInternalFrame {

    private JTable tablaHorario;
    private DefaultTableModel modeloHorario;

    public HorariosView() {
        super("Cronograma General de Clases", true, true, true, true);
        setSize(1000, 600);
        initComponents();
        cargarDatosHorario();
    }

    private void initComponents() {
        // Columnas: Hora + Días de la semana
        String[] columnas = {"Hora", "Lunes", "Martes", "Miercoles", "Jueves", "Viernes"};
        modeloHorario = new DefaultTableModel(columnas, 0);
        tablaHorario = new JTable(modeloHorario);
        
        // Configuración visual básica
        tablaHorario.setRowHeight(40); // Filas más altas para que se vea bien
        tablaHorario.getTableHeader().setReorderingAllowed(false);
        
        // Inicializar las filas vacías de 8am a 5pm (17:00)
        for (int h = 8; h <= 17; h++) {
            modeloHorario.addRow(new Object[]{h + ":00", "", "", "", "", ""});
        }

        add(new JScrollPane(tablaHorario), BorderLayout.CENTER);
        
        JButton btnRefrescar = new JButton("Actualizar Calendario");
        btnRefrescar.addActionListener(e -> cargarDatosHorario());
        add(btnRefrescar, BorderLayout.SOUTH);
    }

    private void cargarDatosHorario() {
        // Limpiar datos de la tabla (manteniendo las filas de horas)
        for (int i = 0; i < modeloHorario.getRowCount(); i++) {
            for (int j = 1; j < modeloHorario.getColumnCount(); j++) {
                modeloHorario.setValueAt("", i, j);
            }
        }

        String sql = "SELECT h.dia, h.hora, h.nivel_clase, p.nombre, p.apellido " +
                     "FROM horarios h JOIN profesores p ON h.profesor_id = p.id";

        try (Connection conn = Conexion.getConexion(); 
             Statement stmt = conn.createStatement(); 
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String dia = rs.getString("dia");
                int hora = rs.getInt("hora");
                String textoCelda = rs.getString("nombre") + " " + rs.getString("apellido") + 
                                    " (" + rs.getString("nivel_clase") + ")";

                // Mapear datos a la celda correcta
                int colIndex = getColumnIndex(dia);
                int rowIndex = hora - 8; // Si es las 8:00, fila 0. Si es las 9:00, fila 1.

                if (colIndex != -1 && rowIndex >= 0 && rowIndex < modeloHorario.getRowCount()) {
                    // Si ya hay texto, lo concatenamos (por si hay 2 profes a la misma hora)
                    String valorActual = (String) modeloHorario.getValueAt(rowIndex, colIndex);
                    if (!valorActual.isEmpty()) {
                        textoCelda = valorActual + " / " + textoCelda;
                    }
                    modeloHorario.setValueAt(textoCelda, rowIndex, colIndex);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error cargando horario: " + e.getMessage());
        }
    }

    private int getColumnIndex(String dia) {
        switch (dia) {
            case "Lunes": return 1;
            case "Martes": return 2;
            case "Miercoles": return 3;
            case "Jueves": return 4;
            case "Viernes": return 5;
            default: return -1;
        }
    }
}