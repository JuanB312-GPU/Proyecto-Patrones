package ticketprint;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class SingletonLector {

    // Patrón Singleton para la clase Singleton_Lector.
    private static SingletonLector instance;
    // Objeto properties.
    private Properties properties;

    // Se declara el método que permite obtener la instancia de la clase Singleton_Lector.
    public static SingletonLector getInstance() {
        if (instance == null) {
            instance = new SingletonLector();
        }
        return instance;
    }

    public String read_format(String nombre_plantilla) {

        properties = new Properties();
        // Se inicializa el objeto properties para leer el archivo .properties
        // que contiene el formato HTML.

        try (FileInputStream input = new FileInputStream(nombre_plantilla + ".properties")) {

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
