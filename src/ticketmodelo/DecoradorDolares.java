package ticketmodelo;

import java.text.DecimalFormat;

public class DecoradorDolares extends DecoradorTicket {

    public DecoradorDolares(Modelo modelo) {
        // Llama al constructor de la clase padre DecoradorTicket.
        super(modelo);
        // Inicializa el convertidor de tasas.
        // Este convertidor se encargará de convertir el premio a dólares.
        convertidor = new ConvertidorDeTasas();
        super.fecha_formato();
        super.lenguaje_moneda();
        // Realiza la conversión de divisas al crear el decorador.
        conversion_divisa();
    }

    @Override
    public void conversion_divisa() {
        // Convierte el premio a dólares usando el convertidor de tasas.
        double resultado = convertidor.convertir(modelo.getDenominacion() * modelo.getFichas(), modelo.getMoneda_divisa(), "USD");
        DecimalFormat formato = new DecimalFormat("0.00");
        System.out.println("Resultado de la conversión a dólares: " + resultado);

        setPremio(formato.format(resultado).replace(",", "."));
        setPremio_letras((modelo.getFormateador().format(resultado)).toUpperCase());
        setMoneda("Dólares Estadounidenses"); // Actualiza la moneda a dólares.

    }

    @Override
    public String formato_factura() {
        // Retorna el formato de factura del modelo decorado.
        return modelo.formato_factura();
    }

}
