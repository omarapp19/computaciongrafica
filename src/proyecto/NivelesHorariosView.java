package proyecto;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;
import javax.swing.table.DefaultTableModel;
import javax.swing.plaf.basic.BasicInternalFrameUI;

public class NivelesHorariosView extends javax.swing.JInternalFrame {


    private boolean modoEdicion = false; 
    private TableRowSorter<DefaultTableModel> sorter;
  
    public NivelesHorariosView() {
        initComponents();
        
        cargarTablaNiveles();

 
    DefaultTableModel modelo = (DefaultTableModel) tablaNiveles.getModel();  // Cambiar tablaRepresentantes a tablaNiveles
    sorter = new TableRowSorter<>(modelo);
    tablaNiveles.setRowSorter(sorter);
    
    cmbNivel = new javax.swing.JComboBox<>(new String[]{"Kyu 10", "Kyu 9", "Kyu 8", "Kyu 7", "Kyu 6", "Kyu 5", "Kyu 4", "Kyu 3", "Kyu 2", "Kyu 1", "Shodan (Dan 1)", "Nidan (Dan 2)"});
    cmbNivel.setFont(new java.awt.Font("Segoe UI", 0, 14));
  
    txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
        @Override
        public void keyReleased(java.awt.event.KeyEvent evt) {
   
            filtrarTablaNiveles();
        }
    });
 
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);
        

    habilitarCampos(false);
    btnGuardar.setEnabled(false);
    btnCancelar.setEnabled(false);
    btnNuevo.setEnabled(true);
    
    cargarTablaNiveles();
    }
    

    private void habilitarCampos(boolean enabled) {
    cmbNivel.setEnabled(enabled);  // Cambiar txtCedula
    txtHorario.setEnabled(enabled);  // Cambiar txtNombre, txtTelefono, txtEmail (solo uno para horario)
}

    private void limpiarCampos() {
    cmbNivel.setSelectedIndex(0);  // Cambiar txtCedula
    txtHorario.setText("");  // Cambiar txtNombre, etc.
}
    
    
    
    
 
private void filtrarTablaNiveles() {  // Cambiar nombre
    String texto = txtBuscar.getText().trim();
    if (texto.isEmpty()) {
        sorter.setRowFilter(null);
    } else {
        sorter.setRowFilter(RowFilter.regexFilter("(?i)" + texto));
    }
}

private void cargarTablaNiveles() {  // Cambiar nombre
    DefaultTableModel modeloTabla = (DefaultTableModel) tablaNiveles.getModel();  // Cambiar tablaRepresentantes
    modeloTabla.setRowCount(0); 

    Connection conn = Conexion.getConexion();
    String sql = "SELECT nivel, horario FROM niveles";  // Cambiar consulta

    try (PreparedStatement pstmt = conn.prepareStatement(sql);
         ResultSet rs = pstmt.executeQuery()) {
        
        while (rs.next()) {
            Object[] fila = new Object[2];  // Cambiar a 2 columnas
            fila[0] = rs.getString("nivel");
            fila[1] = rs.getString("horario");
            modeloTabla.addRow(fila);
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error al cargar niveles: " + e.getMessage());
    }
}

private boolean nivelYaExiste(String nivel) {  // Cambiar nombre y lógica
    Connection conn = Conexion.getConexion();
    String sql = "SELECT COUNT(*) FROM niveles WHERE nivel = ?";
    
    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, nivel);
        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error al verificar nivel: " + e.getMessage());
    }
    return false;
}


 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        panelConsulta = new javax.swing.JPanel();
        lblBuscar = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaNiveles = new javax.swing.JTable();  // Cambiado de tablaRepresentantes
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        panelDetalle = new javax.swing.JPanel();
        btnNuevo = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        lblNivel = new javax.swing.JLabel();  // Nuevo
        lblHorario = new javax.swing.JLabel();  // Nuevo
        txtHorario = new javax.swing.JTextField();  // Nuevo
        lblTitulo = new javax.swing.JLabel();
        btnCerrar = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Gestión de Niveles");
        // Cambiado
        panelConsulta.setBackground(new java.awt.Color(245, 245, 245));
        panelConsulta.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Consultar Niveles", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N
        
        lblBuscar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblBuscar.setText("Buscar:");

        txtBuscar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        tablaNiveles.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tablaNiveles.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cédula", "Nombre", "Teléfono", "Email"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tablaNiveles);

        btnModificar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnModificar.setText("Seleccionar para Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnEliminar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(204, 0, 0));
        btnEliminar.setText("Eliminar Seleccionado");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelConsultaLayout = new javax.swing.GroupLayout(panelConsulta);
        panelConsulta.setLayout(panelConsultaLayout);
        panelConsultaLayout.setHorizontalGroup(
            panelConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelConsultaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(panelConsultaLayout.createSequentialGroup()
                        .addComponent(lblBuscar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBuscar))
                    .addGroup(panelConsultaLayout.createSequentialGroup()
                        .addComponent(btnModificar, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelConsultaLayout.setVerticalGroup(
            panelConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelConsultaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBuscar)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnModificar)
                    .addComponent(btnEliminar))
                .addContainerGap())
        );

        panelDetalle.setBackground(new java.awt.Color(255, 255, 255));
        panelDetalle.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Detalle del Nivel", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        btnNuevo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnNuevo.setText("Nuevo Registro");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnGuardar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnGuardar.setText("Guardar Cambios");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnCancelar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        lblNivel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblNivel.setText("Nivel:");

        lblHorario.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblHorario.setText("Horario:");

        txtHorario.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        javax.swing.GroupLayout panelDetalleLayout = new javax.swing.GroupLayout(panelDetalle);
        panelDetalle.setLayout(panelDetalleLayout);
        panelDetalleLayout.setHorizontalGroup(
            panelDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDetalleLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelDetalleLayout.createSequentialGroup()
                        .addComponent(btnNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE))
                    .addGroup(panelDetalleLayout.createSequentialGroup()
                        .addGroup(panelDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNivel)
                            .addComponent(lblHorario))
                        .addGap(18, 18, 18)
                        .addGroup(panelDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbNivel, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)  // Agregado
                            .addComponent(txtHorario))))
                .addContainerGap())
        );
        panelDetalleLayout.setVerticalGroup(
            panelDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDetalleLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(panelDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNivel)
                    .addComponent(cmbNivel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))  // Agregado
                .addGap(18, 18, 18)
                .addGroup(panelDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblHorario)
                    .addComponent(txtHorario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTitulo.setText("Módulo de Asignación de Horarios a Niveles");  // Cambiado

        btnCerrar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCerrar.setForeground(new java.awt.Color(153, 0, 0));
        btnCerrar.setText("X");
        btnCerrar.setToolTipText("Cerrar esta ventana");
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panelConsulta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelDetalle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblTitulo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCerrar)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTitulo)
                    .addComponent(btnCerrar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelConsulta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelDetalle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>                        

  
    
    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {                                          
        this.dispose();
    }                                         

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {                                         
        limpiarCampos();
        habilitarCampos(true);
        btnNuevo.setEnabled(false);
        btnGuardar.setEnabled(true);
        btnCancelar.setEnabled(true);
        btnModificar.setEnabled(false);
        btnEliminar.setEnabled(false);
        txtCedula.requestFocus();
        this.modoEdicion = false; 
    }                                        

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {                                            
        limpiarCampos();
        habilitarCampos(false);
        btnNuevo.setEnabled(true);
        btnGuardar.setEnabled(false);
        btnCancelar.setEnabled(false);
        btnModificar.setEnabled(true);
        btnEliminar.setEnabled(true);
        this.modoEdicion = false; 
    }                                           

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {                                           
    String nivel = (String) cmbNivel.getSelectedItem();  // Cambiar cedula
    String horario = txtHorario.getText().trim();  // Cambiar nombre, etc.

    if (nivel == null || horario.isEmpty()) {  // Cambiar validación
        JOptionPane.showMessageDialog(this, "El Nivel y el Horario son obligatorios.", "Campos Vacíos", JOptionPane.WARNING_MESSAGE);
        return;
    }
    
    Connection conn = Conexion.getConexion();
    
    if (this.modoEdicion) {
        String sql = "UPDATE niveles SET horario = ? WHERE nivel = ?";  // Cambiar SQL
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, horario);
            pstmt.setString(2, nivel);
            
            int filasAfectadas = pstmt.executeUpdate();
            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(this, "¡Horario asignado con éxito!");
                cargarTablaNiveles();  // Cambiar
                btnCancelarActionPerformed(null); 
            }
        } catch (SQLException e) {
             JOptionPane.showMessageDialog(this, "Error al modificar: " + e.getMessage());
        }
    } else {
        if (nivelYaExiste(nivel)) {  // Cambiar
            JOptionPane.showMessageDialog(this, "El Nivel '" + nivel + "' ya tiene horario asignado.", "Nivel Duplicado", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String sql = "INSERT INTO niveles (nivel, horario) VALUES (?, ?)";  // Cambiar
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nivel);
            pstmt.setString(2, horario);
            
            int filasAfectadas = pstmt.executeUpdate();
            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(this, "¡Horario asignado con éxito!");
                cargarTablaNiveles();  // Cambiar
                btnCancelarActionPerformed(null); 
            }
        } catch (SQLException e) {
             JOptionPane.showMessageDialog(this, "Error al guardar: " + e.getMessage());
        }
    }
}                                          

private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {                                             
    int fila = tablaNiveles.getSelectedRow();  // Cambiar tabla
    if (fila < 0) {
        JOptionPane.showMessageDialog(this, "Debe seleccionar un nivel de la tabla.", "Error", JOptionPane.WARNING_MESSAGE);
        return;
    }
    
    String nivel = tablaNiveles.getValueAt(fila, 0).toString();  // Cambiar
    String horario = (tablaNiveles.getValueAt(fila, 1) != null) ? tablaNiveles.getValueAt(fila, 1).toString() : "";
    
    cmbNivel.setSelectedItem(nivel);  // Cambiar
    txtHorario.setText(horario);  // Cambiar
    
    habilitarCampos(true);
    btnNuevo.setEnabled(false);
    btnGuardar.setEnabled(true);
    btnCancelar.setEnabled(true);
    cmbNivel.setEnabled(false);  // Cambiar: no permitir cambiar nivel en edición
    
    this.modoEdicion = true; 
}                                            

private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {                                            
    int filaSeleccionada = tablaNiveles.getSelectedRow();  // Cambiar
    
    if (filaSeleccionada < 0) {
        JOptionPane.showMessageDialog(this, "Debe seleccionar un nivel para eliminar.", "Error", JOptionPane.WARNING_MESSAGE);
        return;
    }
    
    String nivel = tablaNiveles.getValueAt(filaSeleccionada, 0).toString();  // Cambiar
    
    int confirmacion = JOptionPane.showConfirmDialog(this, 
            "¿Está seguro de que desea eliminar el horario del nivel: " + nivel + "?",  // Cambiar mensaje
            "Confirmar Eliminación", 
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE);
    
    if (confirmacion == JOptionPane.YES_OPTION) {
        Connection conn = Conexion.getConexion();
        String sql = "DELETE FROM niveles WHERE nivel = ?";  // Cambiar
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nivel);
            int filasAfectadas = pstmt.executeUpdate();
            
            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(this, "Horario eliminado con éxito.");
                cargarTablaNiveles();  // Cambiar
                limpiarCampos();
                btnCancelarActionPerformed(null);
            }
        } catch (SQLException e) {
             JOptionPane.showMessageDialog(this, "Error al eliminar: " + e.getMessage(), "Error SQL", JOptionPane.ERROR_MESSAGE);
        }
    }
}                                        

                  
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBuscar;
    private javax.swing.JComboBox<String> cmbNivel;
    private javax.swing.JLabel lblNivel;
    private javax.swing.JTextField txtHorario;
    private javax.swing.JLabel lblHorario;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel panelConsulta;
    private javax.swing.JPanel panelDetalle;
    private javax.swing.JTable tablaNiveles;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtCedula;
    
               
}
