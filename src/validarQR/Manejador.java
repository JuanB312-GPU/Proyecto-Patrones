package validarQR;

public abstract class Manejador {

    protected Manejador sucesor;

    public void setSucesor(Manejador sucesor) {
        this.sucesor = sucesor;
    }

    public abstract boolean procesarPeticion(String datosQR);
}
