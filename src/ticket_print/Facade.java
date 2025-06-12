package ticket_print;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.google.zxing.WriterException;

public class Facade {

    Properties properties = new Properties();

    // Modelo de la impresión.
    private Model modelo;

    public Model getModelo() {
        return modelo;
    }

    public void setModelo(Model modelo) {
        this.modelo = modelo;
    }

    // Sobrecarga de constructores de la fachada.
    public Facade(String moneda, String nom_casino) {

        modelo = new Model(moneda, nom_casino);

    }

    public Facade() {
    }

    public void print_ticket(int modulo, int fichas, int denominacion, int ticket, String htmlString) {

        utility utility = new utility();
        QRCodeGenerator generatorQR = new QRCodeGenerator();

        generatorQR.setData(modulo + "-" + fichas + "-" + denominacion + "-" + ticket); // Datos grabados en el qr.
        String filePath = "qrcode.png"; // Nombre del archivo de salida, con el qr.
        int width = 300;
        int height = 300;

        try {
            // Generar el código QR
            generatorQR.generateQRCode(filePath, width, height);
            System.out.println("Código QR generado en: " + filePath);
        } catch (WriterException | IOException e) {
            System.out.println("Ocurrió un error al generar el código QR: " + e.getMessage());
        }

        try {
            // Los datos variables entran en el modelo...
            modelo.var_model(modulo, fichas, denominacion, ticket);

            String receiptData = String.format(htmlString, modelo.getNom_casino(), modelo.getPremio(),
                    modelo.getPremio_letters(), modelo.getMoneda(), modelo.getModulo(),
                    modelo.getFichas(), modelo.getDenominacion(), modelo.getFecha(), modelo.getTicket());
            utility.print_service(receiptData, 360);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String read_format() {

        Properties properties = new Properties();

        try (FileInputStream input = new FileInputStream("template.properties")) {

            // Cargar el archivo .properties
            properties.load(input);

            // Obtener la cadena HTML
            String htmlString = properties.getProperty("html.template");

            System.out.println(htmlString);

            return htmlString;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;

    }

}
