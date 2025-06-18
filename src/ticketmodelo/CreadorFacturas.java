package Ticket_Modelo;

public abstract class Creador_Facturas {

    // Método abstracto para crear una factura.
    public abstract Modelo metodoFabrica(String moneda, String nom_casino);

    public Modelo inicializadorFactura(String moneda, String nom_casino) {

        Modelo modelo = metodoFabrica(moneda, nom_casino);
        modelo.fecha_formato();
        modelo.lenguaje_moneda();

        return modelo;
    }

}
