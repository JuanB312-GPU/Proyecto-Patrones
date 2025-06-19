package validarQR;

public class ManejadorContenido extends Manejador {

    @Override
    public boolean procesarPeticion(String datosQR) {
        String[] partes = datosQR.split("-");
        int fichas = Integer.parseInt(partes[1]);
        if (fichas <= 0) {
            System.out.println("[Cadena] Cantidad de fichas inválida");
            return false;
        }
        return sucesor == null || sucesor.procesarPeticion(datosQR);
    }
}
