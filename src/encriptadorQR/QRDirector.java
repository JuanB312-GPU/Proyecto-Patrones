package encriptadorQR;

// PATRÓN BUILDER
public class QRDirector {
    private QRBuilder builder;

    public QRDirector(QRBuilder builder) {
        this.builder = builder;
    }

    public QR makeEncryptedJsonQR(String data) {
        return builder
            .withData(data)
            .encrypted(true)
            .encoding("JSON")
            .build();
    }

    public QR makePlainQR(String data) {
        return builder
            .withData(data)
            .encrypted(false)
            .build();
    }
}