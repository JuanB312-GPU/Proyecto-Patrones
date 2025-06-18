package ticketmodelo;

public class Creador_Concreto_In extends Creador_Facturas {

    @Override
    public Modelo metodoFabrica(String moneda, String nom_casino) {
        // Retorna una instancia del modelo en inglés.
        return new Modelo_In(moneda, nom_casino);
    }

}
