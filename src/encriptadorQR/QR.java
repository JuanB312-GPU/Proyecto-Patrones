package encriptadorQR;

// PATRÓN BUILDER
public class QR {
    private String data;
    private boolean encriptado;
    private String codificado; // tipo de serialización

    public void setData(String data) {
        this.data = data;
    }

    public void setEncriptado(boolean encriptado) {
        this.encriptado = encriptado;
    }

    public void setCodificado(String codificado) {
        this.codificado = codificado;
    }

    public String getData() {
        return data;
    }

    public boolean isEncriptado() {
        return encriptado;
    }

    public String getCodificado() {
        return codificado;
    }

    @Override
    public String toString() {
        return "QR{data='" + data + "', encrypted=" + encriptado + ", encoding='" + codificado + "'}";
    }
}