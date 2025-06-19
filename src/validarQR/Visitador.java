package validarQR;

public interface Visitador {
    void visitarQRValido(QRValido q);
    void visitarQRInvalido(QRInvalido q);
}
