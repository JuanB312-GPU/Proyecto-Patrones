package ticketmodelo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ConvertidorDeTasas {

    private double resultado;
   
    public ConvertidorDeTasas() {
    }

    public double convertir(double monto, String monedaOrigen, String monedaDestino) {
        
        try {
                // Configura tu clave API y monedas
                String apiKey = "f542c719557a0d3babc25578"; // ✅ Usa tu propia clave

                // Construye la URL para obtener tasas desde monedaOrigen
                String urlStr = String.format("https://v6.exchangerate-api.com/v6/%s/latest/%s", apiKey, monedaOrigen);
                URL url = new URL(urlStr);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");

                // Leer la respuesta
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder respuesta = new StringBuilder();
                String linea;
                while ((linea = reader.readLine()) != null) {
                    respuesta.append(linea);
                }
                reader.close();

                // Procesar JSON
                JSONObject json = new JSONObject(respuesta.toString());

                // Validar si la respuesta fue exitosa
                if (!json.getString("result").equals("success")) {
                    System.out.println("Error desde la API: " + json.toString(2));
                }

                // Obtener la tasa de conversión
                double tasa = json.getJSONObject("conversion_rates").getDouble(monedaDestino);

                // Usar el método para convertir;
                resultado = monto * tasa;

            } catch (Exception e) {
                System.out.println("Error durante la conversión:");
                e.printStackTrace();
            }
        return FormatResultado(resultado);
    }

    public double FormatResultado(double resultado) {

        BigDecimal bd = BigDecimal.valueOf(resultado);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        resultado = bd.doubleValue();
        
        return resultado;
    }

}