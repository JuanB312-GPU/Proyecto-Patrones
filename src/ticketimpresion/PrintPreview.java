package ticketimpresion;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PrintPreview {

    public void showPrintPreview(BufferedImage qrImage) {
        // Crear un JFrame para la ventana de previsualización
        JFrame previewFrame = new JFrame("Previsualización de Impresión");
        previewFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        previewFrame.setSize(new Dimension(300, 400));
        
        // Crear un panel para renderizar la imagen
        JPanel imagePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Dibujar la imagen centrada en el panel
                int x = (getWidth() - qrImage.getWidth()) / 2;
                int y = (getHeight() - qrImage.getHeight()) / 2;
                g.drawImage(qrImage, x, y, this);
            }
        };
        
        imagePanel.setPreferredSize(new Dimension(qrImage.getWidth(), qrImage.getHeight()));
        
        // Agregar el panel al frame
        previewFrame.add(imagePanel, BorderLayout.CENTER);
        
        // Mostrar la ventana de previsualización
        previewFrame.pack();
        previewFrame.setVisible(true);
    }

}