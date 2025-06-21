package encriptadorQR;
import java.util.Stack;

//PATRÓN MEMENTO
public class QRMemento {
    private Stack<QRDataMemento> history = new Stack<>();

    public void push(QRDataMemento m) {
        history.push(m);
    }

    public QRDataMemento pop() {
        return history.isEmpty() ? null : history.pop();
    }
}