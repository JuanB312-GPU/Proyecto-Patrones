package encriptadorQR;

//PATRÓN MEMENTO
public class QRDataMemento {
    private final String state;

    public QRDataMemento(String state) {
        this.state = state;
    }

    public String getSavedState() {
        return state;
    }
}