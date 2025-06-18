package ticketmodelo;

public class CreadorConcretoIn extends CreadorTickets {

    @Override
    public Modelo metodoFabrica(String moneda, String nom_casino) {
        // Retorna una instancia del modelo en inglés.
        return new ModeloIn(moneda, nom_casino);
    }

}
