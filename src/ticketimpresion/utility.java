package ticketimpresion;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import javax.imageio.ImageIO;

import java.awt.geom.AffineTransform;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.MediaSizeName;
import javax.swing.JEditorPane;

public class utility {

    // Procedimiento necesario para la impresión del ticket.
    public void print_service(String htmlContent, int height_html) {

        try {

            // Se carga el código QR
            BufferedImage image = ImageIO.read(new File("qrcode.png"));

            // Crear un JEditorPane para renderizar HTML
            JEditorPane editorPane = new JEditorPane("text/html", htmlContent);
            editorPane.setSize(height_html, image.getWidth());
            editorPane.setEditable(false);

            int height = image.getWidth(); // Ajusta el ancho si es necesario
            int width = image.getHeight() + height_html ;

            BufferedImage combinedImage = new BufferedImage(width , height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = combinedImage.createGraphics();

            // Renderizar el contenido del editorPane en el Graphics2D
            editorPane.paint(g2d);

            // Dibujar el texto en la parte superior de la imagen combinada
            g2d.setColor(Color.BLACK);
            g2d.setFont(new Font("Arial", Font.PLAIN, 20));

            // Dibujar la imagen QR justo debajo del texto
            g2d.drawImage(image, height_html, 0, null);
            AffineTransform transform = new AffineTransform();
            transform.rotate(Math.toRadians(180), combinedImage.getWidth() / 2, combinedImage.getHeight() / 2);
            g2d.setTransform(transform);

            g2d.dispose();

            PrintPreview prueba = new  PrintPreview();

            prueba.showPrintPreview(this.rotate90DegreesClockwise(combinedImage));

            // Convertir el BufferedImage a un array de bytes para la impresión
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(this.rotate90DegreesClockwise(combinedImage), "PNG", baos);
            byte[] imageData = baos.toByteArray();

            // Crear un InputStream para el flujo de impresión
            InputStream inputStream = new ByteArrayInputStream(imageData);

            // Configurar el tipo de documento para la impresión
            DocFlavor flavor = DocFlavor.INPUT_STREAM.PNG;
            PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
            pras.add(MediaSizeName.ISO_A4);

            // Buscar la impresora predeterminada y enviar la impresión
            PrintService printService = PrintServiceLookup.lookupDefaultPrintService();
            if (printService != null) {
                DocPrintJob job = printService.createPrintJob();
                Doc doc = new SimpleDoc(inputStream, flavor, null);
                job.print(doc, pras);
                inputStream.close();
            } else {
                System.out.println("No se encontró ninguna impresora.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Rota la imagen 90 grados para dejarla de manera horizontal
    public BufferedImage rotate90DegreesClockwise(BufferedImage image) {

        // Crear un nuevo BufferedImage con las dimensiones invertidas
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage rotatedImage = new BufferedImage(height, width, image.getType());

        // Dibujar la imagen rotada
        Graphics2D g2d = rotatedImage.createGraphics();
        g2d.translate(height / 2, width / 2);
        g2d.rotate(Math.toRadians(90));
        g2d.translate(-width / 2, -height / 2);
        g2d.drawImage(image, 0, 0, null);
        g2d.dispose();

        return rotatedImage;
    }

}

    
