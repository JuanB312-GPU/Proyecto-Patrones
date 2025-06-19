package ticketmodelo;

import java.text.SimpleDateFormat;
import java.util.Locale;

import com.ibm.icu.text.RuleBasedNumberFormat;

public class ModeloEs extends Modelo {

    public ModeloEs(String moneda, String nom_casino, String moneda_divisa) {
        super(moneda, nom_casino , moneda_divisa);
    }

    @Override
    public void fecha_formato() {
        // Implementación específica para el formato de fecha en español.
        super.setFecha(new SimpleDateFormat("yyyy/MM/dd   HH:mm:ss"));
        // Aquí se puede definir cómo se debe mostrar la fecha en el ticket.
    }

    @Override
    public void lenguaje_moneda() {
        // Implementación específica para el lenguaje de la moneda en español.
        setFormateador(new RuleBasedNumberFormat(Locale.forLanguageTag("es"),
            RuleBasedNumberFormat.SPELLOUT));
        // Aquí se puede definir cómo se debe mostrar la moneda en el ticket.
    }

    @Override
    public String formato_factura() {
        // Implementación específica para el formato de factura en español.
        return "Esp";
        // Aquí se puede definir cómo se debe mostrar la factura en el ticket.
    }

}
