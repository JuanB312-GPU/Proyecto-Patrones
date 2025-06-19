package validarQR;

public class ManejadorFormato extends Manejador {

    @Override
    public boolean procesarPeticion(String datosQR) {
        if (!datosQR.matches("\\d+-\\d+-\\d+-\\d+")) {
            System.out.println("[Cadena] Formato incorrecto");
            return false;
        }
        return sucesor == null || sucesor.procesarPeticion(datosQR);
    }
}
