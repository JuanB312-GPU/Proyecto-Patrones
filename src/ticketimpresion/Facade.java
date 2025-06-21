package ticketimpresion;

import java.io.IOException;
import com.google.zxing.WriterException;

import encriptadorQR.QR;
import encriptadorQR.QRBuilder;
import encriptadorQR.QRDataMemento;
import encriptadorQR.QRDataOriginator;
import encriptadorQR.QRDirector;
import encriptadorQR.QRMemento;
import ticketmodelo.CreadorConcretoEs;
import ticketmodelo.CreadorConcretoIn;
import ticketmodelo.CreadorTickets;
import ticketmodelo.Modelo;

public class Facade {

    private CreadorTickets creadorFactura;
    private Modelo modelo;
    private utility utility;
    private QRCodeGenerator generatorQR;
    private SingletonLector singletonLector;

    public Facade(String moneda, String nom_casino, String idioma, String moneda_divisa) {
        switch (idioma) {
            case "Esp":
                creadorFactura = new CreadorConcretoEs();
                break;
            case "Ing":
                creadorFactura = new CreadorConcretoIn();
                break;
            default:
                break;
        }
        modelo = creadorFactura.inicializadorFactura(moneda, nom_casino, moneda_divisa);
    }

    public void print_ticket(int modulo, int fichas, int denominacion, int ticket, int conversion) {
        modelo.var_model(modulo, fichas, denominacion, ticket);

        // === construir string base ===
        String datosBase = modulo + "-" + fichas + "-" + denominacion + "-" + ticket;

        //MEMENTO
        // Crear originador y cuidador
        QRDataOriginator originator = new QRDataOriginator();
        QRMemento caretaker = new QRMemento();

        // Guardar estado antes de encriptar
        originator.setState(datosBase);
        caretaker.push(originator.save());

        // === CONSTRUCCIÓN DEL QR CON BUILDER ===
        QRDirector director = new QRDirector(new QRBuilder());
        QR qr = director.makeEncryptedJsonQR(datosBase);
        System.out.println("[BUILDER] QR encriptado: " + qr.getData());
        String datosQR = qr.getData();

        // === RESTAURAR ESTADO ORIGINAL ===
        //Aqui la idea es ver los datos originales del QR
        QRDataMemento memento = caretaker.pop();
        if (memento != null) {
            originator.restore(memento);
            String estadoOriginal = originator.getState();
            System.out.println("[RESTORE] Estado original del QR (antes de codificar): " + estadoOriginal);
        }


        // === Decorar según conversión ===
        switch (conversion) {
            case 0:
                modelo.var_premio();
                break;
            case 1:
                modelo = new ticketmodelo.DecoradorDolares(modelo);
                break;
            case 2:
                modelo = new ticketmodelo.DecoradorEuros(modelo);
                break;
            default:
                break;
        }

        utility = new utility();
        generatorQR = new QRCodeGenerator();
        singletonLector = SingletonLector.getInstance();
        String htmlString = singletonLector.read_format(modelo.formato_factura());

        if (htmlString == null) {
            System.err.println("Error: plantilla HTML no encontrada.");
            return;
        }

        generatorQR.setData(datosQR);
        String filePath = "qrcode.png";
        int width = 300;
        int height = 300;

        try {
            generatorQR.generateQRCode(filePath, width, height);
            System.out.println("Código QR generado en: " + filePath);
        } catch (WriterException | IOException e) {
            System.out.println("Ocurrió un error al generar el código QR: " + e.getMessage());
        }

        try {
            String receiptData = String.format(htmlString, modelo.getNom_casino(), modelo.getPremio(),
                    modelo.getPremio_letras(), modelo.getMoneda(), modelo.getModulo(),
                    modelo.getFichas(), modelo.getDenominacion(), modelo.getFecha(), modelo.getTicket());
            System.out.println(receiptData);
            utility.print_service(receiptData, 360);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Facade facade = new Facade("Pesos Colombianos", "Royale", "Esp", "COP");
        facade.print_ticket(1, 120, 1000, 123456, 2);
    }
}


