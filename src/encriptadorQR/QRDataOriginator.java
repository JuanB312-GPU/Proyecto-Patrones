package encriptadorQR;

//PATRÓN MEMENTO
public class QRDataOriginator {
    private String data;

    public void setState(String data) {
        this.data = data;
    }

    public String getState() {
        return data;
    }

    public QRDataMemento save() {
        return new QRDataMemento(data);
    }

    public void restore(QRDataMemento m) {
        this.data = m.getSavedState();
    }
}