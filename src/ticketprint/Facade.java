package ticketprint;
import java.io.IOException;
import com.google.zxing.WriterException;
import ticketmodelo.CreadorConcretoEs;
import ticketmodelo.CreadorConcretoIn;
import ticketmodelo.CreadorTickets;
import ticketmodelo.Modelo;
import validarQR.*;


public class Facade {

    // Modelo de la impresión.
    private CreadorTickets creadorFactura;
    private Modelo modelo;
    private utility utility;
    private QRCodeGenerator generatorQR;
    private SingletonLector singletonLector;

    // Sobrecarga de constructores de la fachada.
    public Facade(String moneda, String nom_casino, String idioma, String moneda_divisa) {

        // Dependiendo del idioma, se crea el modelo correspondiente.
        // Se utiliza el patrón Factory Method para crear el modelo.
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
        // Inicialización de los métodos del modelo.
        modelo = creadorFactura.inicializadorFactura(moneda, nom_casino, moneda_divisa);
        
    }

    public void print_ticket(int modulo, int fichas, int denominacion, int ticket, int conversion) {

        // Dependiendo de la conversión, se decora el modelo con el decorador correspondiente.
        modelo.var_model(modulo, fichas, denominacion, ticket);
        // Construir cadena QR como string
        String datosQR = modulo + "-" + fichas + "-" + denominacion + "-" + ticket;

        // ======== PATRÓN ESTRATEGIA =========
        ContextoEstrategia contexto = new ContextoEstrategia();
        contexto.setEstrategia(new ValidacionSegura());
        boolean esValidoFormato = contexto.validar(datosQR);

        // ======== PATRÓN CADENA DE RESPONSABILIDAD =========
        Manejador m1 = new ManejadorFormato();
        Manejador m2 = new ManejadorContenido();
        Manejador m3 = new ManejadorDuplicado();

        m1.setSucesor(m2);
        m2.setSucesor(m3);

        boolean pasaCadena = m1.procesarPeticion(datosQR);

        // ======== PATRÓN VISITOR =========
        Visitador visitador = new VisitadorConsola();

        if (esValidoFormato && pasaCadena) {
            QRValido valido = new QRValido(datosQR);
            valido.aceptar(visitador);
        } else {
            QRInvalido invalido = new QRInvalido(datosQR);
            invalido.aceptar(visitador);
            return; // Termina el método si el QR no pasa las validaciones
        }
        // Decorar el modelo dependiendo de la conversión
        switch (conversion) {
            case 0: // Sin conversión.
                modelo.var_premio(); // Calcula el premio en la moneda original.
                break;
            case 1: // Conversión a dólares.
                modelo = new ticketmodelo.DecoradorDolares(modelo);
                System.out.println(modelo.getNom_casino() + " - " + modelo.getPremio() + " - " + modelo.getPremio_letras() + " - " + modelo.getMoneda());
                break;
            case 2: // Conversión a euros.
                modelo = new ticketmodelo.DecoradorEuros(modelo);
                break;
            default:
                break;
        }

        utility = new utility();
        generatorQR = new QRCodeGenerator();
        singletonLector = SingletonLector.getInstance();
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
            // Reemplazar los marcadores en el HTML con los datos del modelo.
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
        // Ejemplo de uso de la fachada.
        Facade facade = new Facade("Pesos Colombianos", "Royale", "Esp", "COP");
        facade.print_ticket(1, 120, 1000, 123456, 2); // Imprime un ticket con conversión a dólares.
    }

}
