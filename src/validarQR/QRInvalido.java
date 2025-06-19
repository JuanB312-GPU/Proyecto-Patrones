package validarQR;

public class QRInvalido implements ElementoQR {

    private String datos;

    public QRInvalido(String datos) {
        this.datos = datos;
    }

    public String getDatos() {
        return datos;
    }

    @Override
    public void aceptar(Visitador v) {
        v.visitarQRInvalido(this);
    }
}
