package validarQR;

import java.util.HashSet;
import java.util.Set;

public class ManejadorDuplicado extends Manejador {

    private static Set<String> qrRegistrados = new HashSet<>();

    @Override
    public boolean procesarPeticion(String datosQR) {
        if (qrRegistrados.contains(datosQR)) {
            System.out.println("[Cadena] QR duplicado detectado");
            return false;
        }
        qrRegistrados.add(datosQR);
        return sucesor == null || sucesor.procesarPeticion(datosQR);
    }
}

