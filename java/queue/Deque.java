package queue;

import java.util.Arrays;

public class Deque {
    //a[1]...a[n]
    //operations with first and last elements

    //inv: n>=0
    //\forall i = 1..n a[i]!=null

    private int head, size;
    private Object[] elements = new Object[5];


    //pre: element!=null
    public void enqueue(Object element) {
        assert element != null;

        ensureCapacity(size + 1);
        elements[mod(head + size)] = element;
        size++;
    }
    //post: n' = n + 1 && \forall i = 1..n: a'[i] = a[i] && a[n'] = element

    //pre: n>0
    public Object dequeue() {
        assert size > 0;
        Object element = elements[head];
        elements[head] = null;
        head = inc(head);
        size--;
        return element;
    }
    // post: ℝ = a[1] ∧ n = n' − 1 ∧ ∀i=1..n : a[i] = a'[i+1]

    //pre: n>0
    public Object element() {
        assert size > 0;

        return elements[head];
    }
    // post: ℝ = a[1] ∧ n = n' ∧ ∀i=1..n : a[i]' = a[i]

    public int size() {
        return size;
    }
    // post: ℝ = n ∧ n = n' c ∀i=1..n : a[i]' = a[i]

    public boolean isEmpty() {
        return size == 0;
    }
    // post: ℝ = n > 0 ∧ n = n' ∧ ∀i=1..n : a[i]' = a[i]

    public void clear() {
        Arrays.fill(elements, null);
        head = 0;
        size = 0;
    }
    // post: ∀i=1..n : a[i]' = null ∧ n' = 0

    private void ensureCapacity(int capacity) {
        if (capacity > elements.length) {
            Object[] temp = new Object[2 * capacity];
            for (int i = 0; i < capacity; i++) {
                temp[i] = elements[mod(head + i)];
            }
            elements = temp;
            head = 0;
        }
    }

    private int inc(int x) {
        return mod(x + 1);
    }

    private int mod(int x) {
        return (x + elements.length) % elements.length;
    }
}
