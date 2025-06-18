package ticketmodelo;

public class CreadorConcretoEs extends CreadorTickets {

    @Override
    public Modelo metodoFabrica(String moneda, String nom_casino) {
        // Retorna una instancia del modelo en español.
        return new ModeloEs(moneda, nom_casino);
    }

}
