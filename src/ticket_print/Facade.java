package ticket_print;
import java.io.IOException;
import com.google.zxing.WriterException;
import Ticket_Modelo.Creador_Concreto_Es;
import Ticket_Modelo.Creador_Concreto_In;
import Ticket_Modelo.Creador_Facturas;
import Ticket_Modelo.Modelo;

public class Facade {

    // Modelo de la impresión.
    private Creador_Facturas creadorFactura;
    private Modelo modelo;
    private utility utility;
    private QRCodeGenerator generatorQR;
    private Singleton_Lector singletonLector;

    // Sobrecarga de constructores de la fachada.
    public Facade(String moneda, String nom_casino, String idioma) {

        // Dependiendo del idioma, se crea el modelo correspondiente.
        // Se utiliza el patrón Factory Method para crear el modelo.
        switch (idioma) {
            case "Esp":
                creadorFactura = new Creador_Concreto_Es();
                break;
            case "Ing":
                creadorFactura = new Creador_Concreto_In();
                break;
            default:
                break;
        }
        // Inicialización de los métodos del modelo.
        modelo = creadorFactura.inicializadorFactura(moneda, nom_casino);
        
    }

    public void print_ticket(int modulo, int fichas, int denominacion, int ticket) {

        utility = new utility();
        generatorQR = new QRCodeGenerator();
        singletonLector = Singleton_Lector.getInstance();
        String htmlString = singletonLector.read_format(modelo.formato_factura()); // Carga el formato HTML desde el archivo properties.

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

}
