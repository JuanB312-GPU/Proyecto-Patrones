package Ticket_Modelo;

public class Creador_Concreto_Es extends Creador_Facturas {

    @Override
    public Modelo metodoFabrica(String moneda, String nom_casino) {
        // Retorna una instancia del modelo en español.
        return new Modelo_Es(moneda, nom_casino);
    }

}
