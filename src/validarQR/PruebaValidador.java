package validarQR;

import encriptadorQR.QRDecoder;
import ticketimpresion.Fachada;

public class PruebaValidador {

    public static void main(String[] args) {
    // Paso 1: Crear ticket (Fachada)
    Fachada fachada = new Fachada("Pesos Colombianos", "Royale", "Esp", "COP");
    fachada.impresionTicket(1, 120, 1000, 123456, 2);  // Ticket impreso con QR
    
    String datosLeidosDesdeQR = "{\"qr\":\"MS0xMjAtMTAwMC0xMjM0NTY=\"}";

    // Desencriptar la info
    String datosDecodificados = QRDecoder.decode(datosLeidosDesdeQR, true, true);
    System.out.println("[Builder] QR desencriptado: " + datosDecodificados);


    // (Tiempo después...) Paso 2: Escanear QR y validar
      // Esto lo da el escáner QR
    Comando comandoValidacion = new ComandoValidarQR(datosDecodificados);
    boolean resultado = comandoValidacion.ejecutar();

    if (resultado) {
        // Si el ticket es válido, se imprime un mensaje de éxito
        System.out.println("✅ Ticket válido." + 
                           "\nDatos del ticket: " + datosDecodificados);
    } else {
        System.out.println("❌ Ticket inválido.");
    }
}

}
