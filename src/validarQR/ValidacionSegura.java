package validarQR;

public class ValidacionSegura implements Estrategia {

    @Override
    public boolean validar(String datosQR) {
        // Valida que el QR tenga el formato "int-int-int-int"
        if (datosQR == null || !datosQR.matches("\\d+-\\d+-\\d+-\\d+")) {
            System.out.println("[Estrategia] QR inválido por formato");
            return false;
        }
        return true;
    }
}
