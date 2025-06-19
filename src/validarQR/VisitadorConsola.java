package validarQR;

public class VisitadorConsola implements Visitador {

    @Override
    public void visitarQRValido(QRValido q) {
        System.out.println("[Visitador] QR válido: " + q.getDatos());
    }

    @Override
    public void visitarQRInvalido(QRInvalido q) {
        System.out.println("[Visitador] QR inválido: " + q.getDatos());
    }
}
