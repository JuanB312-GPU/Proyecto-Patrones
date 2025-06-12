package ticket_print;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.client.j2se.MatrixToImageWriter;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

// Genera el código QR usando una librería de C. abierto.
public class QRCodeGenerator {

    // Para mejoras futuras, la data grabada en el qr debe ser manejada por esta variable...
    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void generateQRCode(String filePath, int width, int height)
            throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        
        Map<com.google.zxing.EncodeHintType, Object> hints = new HashMap<>();
        hints.put(com.google.zxing.EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hints.put(com.google.zxing.EncodeHintType.MARGIN, 1); 

        BitMatrix bitMatrix = qrCodeWriter.encode(data, BarcodeFormat.QR_CODE, width, height, hints);

        Path path = FileSystems.getDefault().getPath(filePath);
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
    }
}