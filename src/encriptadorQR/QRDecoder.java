package encriptadorQR;

import java.util.Base64;

public class QRDecoder {

    public static String decode(String data, boolean isJson, boolean isEncrypted) {
        String rawData = data;

        // Si viene en formato JSON extrae el valor
        if (isJson && data.startsWith("{\"qr\":\"") && data.endsWith("\"}")) {
            rawData = data.substring(7, data.length() - 2);  // Aqui removemos el {"qr":" y "}
        }

        // Si está encriptado decodifica Base64
        if (isEncrypted) {
            try {
                byte[] decodedBytes = Base64.getDecoder().decode(rawData);
                return new String(decodedBytes);
            } catch (IllegalArgumentException e) {
                System.err.println("Error al decodificar Base64: " + e.getMessage());
                return null;
            }
        }

        return rawData;
    }
}
