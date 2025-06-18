package Ticket_Modelo;

import java.text.SimpleDateFormat;
import java.util.Locale;

import com.ibm.icu.text.RuleBasedNumberFormat;

public class Modelo_In extends Modelo {

    public Modelo_In(String moneda, String nom_casino) {
        super(moneda, nom_casino);
    }

    @Override
    public void fecha_formato() {
        // Implementación específica para el formato de fecha en inglés.
        setFecha(new SimpleDateFormat("MM/dd/yyyy HH:mm:ss"));
        // Aquí se puede definir cómo se debe mostrar la fecha en el ticket.
    }

    @Override
    public void lenguaje_moneda() {
        // Implementación específica para el lenguaje de la moneda en inglés.
        setFormateador(new RuleBasedNumberFormat(Locale.forLanguageTag("en"),
            RuleBasedNumberFormat.SPELLOUT));
        // Aquí se puede definir cómo se debe mostrar la moneda en el ticket.
    }

    @Override
    public String formato_factura() {
        // Implementación específica para el formato de factura en inglés.
        return "Ing";
        // Aquí se puede definir cómo se debe mostrar la factura en el ticket.
    }

}
