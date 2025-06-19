package validarQR;

public class QRValido implements ElementoQR {

    private String datos;

    public QRValido(String datos) {
        this.datos = datos;
    }

    public String getDatos() {
        return datos;
    }

    @Override
    public void aceptar(Visitador v) {
        v.visitarQRValido(this);
    }
}
