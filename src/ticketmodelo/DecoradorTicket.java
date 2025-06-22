package ticketmodelo;

public abstract class DecoradorTicket extends Modelo {

    // Convertidor de tasas para la conversión de divisas.
    protected ConvertidorDeTasas convertidor;

    protected Modelo modelo;

    public DecoradorTicket(Modelo modelo) {
        super(modelo.getMoneda(), modelo.getNom_casino(), modelo.getMoneda_divisa());
        // Inicializa el modelo decorado.
        this.setDenominacion(modelo.getDenominacion());
        this.setFichas(modelo.getFichas());
        this.setModulo(modelo.getModulo());
        this.setTicket(modelo.getTicket());
        this.setPremio(modelo.getPremio());
        this.setPremio_letras(modelo.getPremio_letras());
        this.setFecha(modelo.getFecha_Format());
        this.setFormateador(modelo.getFormateador());

        this.modelo = modelo;
    }

    @Override
    public String formato_factura() {
        return modelo.formato_factura();
    }

    @Override
    public void fecha_formato() {
        modelo.fecha_formato();
    }

    @Override
    public void lenguaje_moneda() {
        modelo.lenguaje_moneda();
    }

    public abstract void conversion_divisa();

    // Puedes agregar más métodos decorados aquí si es necesario.

}


