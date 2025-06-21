package encriptadorQR;
import java.util.Base64;

// PATRÓN BUILDER
public class QRBuilder {
    private QR qr = new QR();

    public QRBuilder withData(String data) {
        qr.setData(data);
        return this;
    }

    public QRBuilder encrypted(boolean flag) {
        qr.setEncriptado(flag);
        return this;
    }

    public QRBuilder encoding(String enc) {
        qr.setCodificado(enc);
        return this;
    }

    public QR build() {
        if (qr.isEncriptado()) {
            qr.setData(Base64.getEncoder().encodeToString(qr.getData().getBytes()));
        }
        if (qr.getCodificado() != null && qr.getCodificado().equalsIgnoreCase("JSON")) {
            qr.setData("{\"qr\":\"" + qr.getData() + "\"}");
        }
        return qr;
    }
}