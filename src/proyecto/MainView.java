package proyecto;

import proyecto.LoginView;
import proyecto.EstudiantesView;
import proyecto.RepresentantesView;
import proyecto.HistorialView;
import java.awt.Image; 
import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.net.URL;
import javax.swing.JOptionPane; 
import java.awt.BorderLayout; 

public class MainView extends javax.swing.JFrame {

    private LoginView vistaLoginDeOrigen;
    
    public MainView() {
        initComponents();
        configurarVentana();
        crearToolBar(); 
    }
    
    public MainView(LoginView login) {
        initComponents();
        this.vistaLoginDeOrigen = login; 
        configurarVentana();
        crearToolBar(); 
    }
    
    private void configurarVentana() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        this.setExtendedState(MAXIMIZED_BOTH); 
    }
    
    private void crearToolBar() {
        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false); 
        
        int iconAncho = 50;
        int iconAlto = 50;
        
        JButton btnEstudiantes = new JButton("Estudiantes");
        btnEstudiantes.setToolTipText("Gestionar Estudiantes");
        btnEstudiantes.setIcon(cargarIconoEscalado("estudiante.png", iconAncho, iconAlto));
        btnEstudiantes.addActionListener(this::itemGestionEstudiantesActionPerformed);
        
        JButton btnRepresentantes = new JButton("Representantes");
        btnRepresentantes.setToolTipText("Gestionar Representantes");
        btnRepresentantes.setIcon(cargarIconoEscalado("padre.png", iconAncho, iconAlto));
        btnRepresentantes.addActionListener(this::itemGestionRepresentantesActionPerformed);

        JButton btnHistorial = new JButton("Historial");
        btnHistorial.setToolTipText("Ver Historial General");
        btnHistorial.setIcon(cargarIconoEscalado("historial.png", iconAncho, iconAlto));
        btnHistorial.addActionListener(this::itemVerHistorialActionPerformed);

        JButton btnCerrar = new JButton("Cerrar Sesión");
        btnCerrar.setToolTipText("Volver a la pantalla de Login");
        btnCerrar.setIcon(cargarIconoEscalado("cerrar.png", iconAncho, iconAlto));
        btnCerrar.addActionListener(this::itemCerrarSesionActionPerformed);
        
        JButton btnSalir = new JButton("Salir");
        btnSalir.setToolTipText("Cerrar la aplicación por completo");
         btnSalir.setIcon(cargarIconoEscalado("salir.png", iconAncho, iconAlto));
        btnSalir.addActionListener(this::itemSalirActionPerformed);
        
        toolBar.add(btnEstudiantes);
        toolBar.add(btnRepresentantes);
        toolBar.add(btnHistorial);
        toolBar.add(new JToolBar.Separator()); 
        toolBar.add(btnCerrar);
        toolBar.add(btnSalir);
        
        this.getContentPane().setLayout(new java.awt.BorderLayout());
        this.getContentPane().add(toolBar, java.awt.BorderLayout.NORTH);
        this.getContentPane().add(escritorioPrincipal, java.awt.BorderLayout.CENTER);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        escritorioPrincipal = new JDesktopPaneConFondo();
        
        menuBar = new javax.swing.JMenuBar();
        menuArchivo = new javax.swing.JMenu();
        itemCerrarSesion = new javax.swing.JMenuItem();
        itemSalir = new javax.swing.JMenuItem();
        menuGestion = new javax.swing.JMenu();
        itemGestionEstudiantes = new javax.swing.JMenuItem();
        itemGestionRepresentantes = new javax.swing.JMenuItem();
        menuHistorial = new javax.swing.JMenu();
        itemVerHistorial = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Menú Principal - Sistema de Administración de Dojo");

        escritorioPrincipal.setBackground(new java.awt.Color(45, 45, 45));

        javax.swing.GroupLayout escritorioPrincipalLayout = new javax.swing.GroupLayout(escritorioPrincipal);
        escritorioPrincipal.setLayout(escritorioPrincipalLayout);
        escritorioPrincipalLayout.setHorizontalGroup(
            escritorioPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );
        escritorioPrincipalLayout.setVerticalGroup(
            escritorioPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 579, Short.MAX_VALUE)
        );

        menuArchivo.setText("Archivo");
        menuArchivo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        itemCerrarSesion.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        itemCerrarSesion.setText("Cerrar Sesión");
        itemCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemCerrarSesionActionPerformed(evt);
            }
        });
        menuArchivo.add(itemCerrarSesion);

        itemSalir.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        itemSalir.setText("Salir");
        itemSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemSalirActionPerformed(evt);
            }
        });
        menuArchivo.add(itemSalir);

        menuBar.add(menuArchivo);

        menuGestion.setText("Gestión");
        menuGestion.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        itemGestionEstudiantes.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        itemGestionEstudiantes.setText("Estudiantes");
        itemGestionEstudiantes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemGestionEstudiantesActionPerformed(evt);
            }
        });
        menuGestion.add(itemGestionEstudiantes);

        itemGestionRepresentantes.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        itemGestionRepresentantes.setText("Representantes");
        itemGestionRepresentantes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemGestionRepresentantesActionPerformed(evt);
            }
        });
        menuGestion.add(itemGestionRepresentantes);

        menuBar.add(menuGestion);

        menuHistorial.setText("Historial");
        menuHistorial.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        itemVerHistorial.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        itemVerHistorial.setText("Exámenes y Certificados");
        itemVerHistorial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemVerHistorialActionPerformed(evt);
            }
        });
        menuHistorial.add(itemVerHistorial);

        menuBar.add(menuHistorial);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(escritorioPrincipal)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(escritorioPrincipal)
        );

        pack();
    }// </editor-fold>                        

   private ImageIcon cargarIconoEscalado(String nombreIcono, int ancho, int alto) {
        try {
            URL url = getClass().getResource("/assets/" + nombreIcono);
            
            if (url != null) {
                ImageIcon originalIcon = new ImageIcon(url);
                Image img = originalIcon.getImage();
                Image scaledImg = img.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
                return new ImageIcon(scaledImg);
            }
        } catch (Exception e) {
            System.err.println("Icono no encontrado o error al escalar: " + nombreIcono);
        }
        return null; 
    }
    
    private void itemCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {                                                 
        this.dispose();
        if (vistaLoginDeOrigen != null) {
            vistaLoginDeOrigen.setVisible(true);
        } else {
            new LoginView().setVisible(true);
        }
    }                                                

    private void itemSalirActionPerformed(java.awt.event.ActionEvent evt) {                                          
        System.exit(0);
    }                                         

    private void itemGestionEstudiantesActionPerformed(java.awt.event.ActionEvent evt) {                                                       
        EstudiantesView vistaEstudiantes = new EstudiantesView();
        escritorioPrincipal.add(vistaEstudiantes);
        vistaEstudiantes.setVisible(true);
        try {
            vistaEstudiantes.setMaximum(true); 
            vistaEstudiantes.setSelected(true); 
        } catch (java.beans.PropertyVetoException e) {}
    }                                                      

    private void itemGestionRepresentantesActionPerformed(java.awt.event.ActionEvent evt) {                                                          
        RepresentantesView vistaRepresentantes = new RepresentantesView();
        escritorioPrincipal.add(vistaRepresentantes);
        vistaRepresentantes.setVisible(true);
        try {
            vistaRepresentantes.setMaximum(true); 
            vistaRepresentantes.setSelected(true); 
        } catch (java.beans.PropertyVetoException e) {}
    }                                                         

    private void itemVerHistorialActionPerformed(java.awt.event.ActionEvent evt) {                                                 
        HistorialView vistaHistorial = new HistorialView();
        escritorioPrincipal.add(vistaHistorial);
        vistaHistorial.setVisible(true);
        try {
            vistaHistorial.setMaximum(true); 
            vistaHistorial.setSelected(true); 
        } catch (java.beans.PropertyVetoException e) {}
    }                                                

    public static void main(String args[]) {
        try {
            javax.swing.UIManager.setLookAndFeel(new com.formdev.flatlaf.FlatLightLaf());
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JDesktopPane escritorioPrincipal;
    private javax.swing.JMenuItem itemCerrarSesion;
    private javax.swing.JMenuItem itemGestionEstudiantes;
    private javax.swing.JMenuItem itemGestionRepresentantes;
    private javax.swing.JMenuItem itemSalir;
    private javax.swing.JMenuItem itemVerHistorial;
    private javax.swing.JMenu menuArchivo;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu menuGestion;
    private javax.swing.JMenu menuHistorial;
    // End of variables declaration                   
}
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents



    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
