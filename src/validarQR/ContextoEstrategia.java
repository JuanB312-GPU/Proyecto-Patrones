package validarQR;

public class ContextoEstrategia {

    private Estrategia estrategia;

    public void setEstrategia(Estrategia estrategia) {
        this.estrategia = estrategia;
    }

    public boolean validar(String datosQR) {
        if (estrategia == null) return false;
        return estrategia.validar(datosQR);
    }
}
