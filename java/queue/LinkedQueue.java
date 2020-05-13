package queue;

public class LinkedQueue extends AbstractQueue {
    protected Node head;
    protected Node tail;

    @Override
    public void enqueue(Object element) {
        assert element != null;
        size++;
        if (tail == null) {
            head = tail = new Node(element, null);
        } else {
            tail.setNext(new Node(element, null));
            tail = tail.getNext();
        }
    }

    @Override
    public Object dequeue() {
        assert size > 0;
        size--;
        Object result = head.getValue();
        head = head.getNext();
        if (head == null) {
            tail = null;
        }
        return result;
    }

    @Override
    public Object element() {
        assert size > 0;
        return head.getValue();
    }

    @Override
    public void clear() {
        size = 0;
        head = null;
        tail = null;
    }
}
