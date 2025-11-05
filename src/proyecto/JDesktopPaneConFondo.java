// Paquete del proyecto
package proyecto;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import java.net.URL; // Para cargar desde 'src'

/**
 * Esta es nuestra versión personalizada del JDesktopPane.
 * Su única misión es pintarse a sí mismo con una imagen de fondo.
 */
public class JDesktopPaneConFondo extends JDesktopPane {

    private Image imagenFondo;

    public JDesktopPaneConFondo() {
        
        // --- ¡¡¡IMPORTANTE!!! ---
        // 1. Pon tu imagen de fondo (ej. "menu_fondo.jpg") en la carpeta 'proyecto/assets/'.
        // 2. Escribe el nombre de esa imagen aquí:
        String nombreImagen = "/assets/fondom.jpg"; // (Puedes usar la misma del login si quieres)
        // -------------------------

        try {
            // Carga la imagen desde la carpeta 'assets'
            URL url = getClass().getResource("/assets/fondom.jpg");
            
            if (url == null) {
                System.err.println("No se encontró la imagen de fondo del menú: " + nombreImagen);
                imagenFondo = null;
            } else {
                imagenFondo = new ImageIcon(url).getImage();
            }
        } catch (Exception e) {
            System.err.println("Error al cargar imagen de fondo del menú: " + e.getMessage());
            imagenFondo = null;
        }
    }

    /**
     * Este es el método mágico.
     * Se llama cada vez que Swing necesita (re)dibujar el componente.
     */
    @Override
    protected void paintComponent(Graphics g) {
        // Primero, deja que Swing (o FlatLaf) dibuje su fondo por defecto
        super.paintComponent(g);

        // Ahora, nosotros pintamos nuestra imagen ENCIMA
        if (imagenFondo != null) {
            // Dibuja la imagen escalada para que ocupe todo el panel
            g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);
        }
    }
}