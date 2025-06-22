package validarQR;

public class ComandoValidarQR implements Comando {

    private ContextoEstrategia contexto;
    private final String datosQR;

    public ComandoValidarQR(String datosQR) {
        this.datosQR = datosQR;
    }

    @Override
    public boolean ejecutar() {
        // Estrategia
        contexto = new ContextoEstrategia();
        contexto.setEstrategia(new ValidacionSegura());
        boolean esValidoFormato = contexto.validar(datosQR);

        // Cadena de Responsabilidad
        Manejador m1 = new ManejadorFormato();
        Manejador m2 = new ManejadorContenido();
        Manejador m3 = new ManejadorDuplicado();

        m1.setSucesor(m2);
        m2.setSucesor(m3);

        boolean pasaCadena = m1.procesarPeticion(datosQR);

        // Visitor
        Visitador visitador = new VisitadorConsola();
        if (esValidoFormato && pasaCadena) {
            new QRValido(datosQR).aceptar(visitador);
            return true;
        } else {
            new QRInvalido(datosQR).aceptar(visitador);
            return false;
        }
    }
}
