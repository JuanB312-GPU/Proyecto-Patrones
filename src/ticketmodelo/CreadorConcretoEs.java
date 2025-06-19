package ticketmodelo;

public class CreadorConcretoEs extends CreadorTickets {

    @Override
    public Modelo metodoFabrica(String moneda, String nom_casino, String moneda_divisa) {
        // Retorna una instancia del modelo en español.
        return new ModeloEs(moneda, nom_casino, moneda_divisa);
    }

}
