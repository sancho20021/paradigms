package queue;

public class Node {
    private Object value;
    private Node next;

    public Node(Object value, Node next) {
        assert value != null;

        this.value = value;
        this.next = next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Node getNext() {
        return next;
    }

    public Object getValue() {
        return value;
    }
}
