package ticketmodelo;

public abstract class CreadorTickets {

    // Método abstracto para crear una factura.
    public abstract Modelo metodoFabrica(String moneda, String nom_casino, String moneda_divisa);

    public Modelo inicializadorFactura(String moneda, String nom_casino, String moneda_divisa) {

        Modelo modelo = metodoFabrica(moneda, nom_casino, moneda_divisa);
        modelo.fecha_formato();
        modelo.lenguaje_moneda();

        return modelo;
    }

}
