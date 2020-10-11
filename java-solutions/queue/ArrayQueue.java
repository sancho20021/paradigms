package queue;

import java.util.Arrays;

public class ArrayQueue<E> extends AbstractQueue<E>  implements IndexedQueue<E>{
    //a[1]...a[n]
    //operations with first and last elements

    //inv: n>=0
    //\forall i = 1..n a[i]!=null

    private int head;
    private E[] elements = (E[])new Object[5];

    @Override
    //pre: element!=null
    public void enqueue(E element) {
        assert element != null;

        ensureCapacity(size + 1);
        elements[mod(head + size)] = element;
        size++;
    }
    //post: n' = n + 1 && \forall i = 1..n: a'[i] = a[i] && a[n'] = element

    //pre: n>0
    public E dequeue() {
        assert size > 0;
        E element = elements[head];
        elements[head] = null;
        head = inc(head);
        size--;
        return element;
    }
    // post: ℝ = a[1] ∧ n = n' − 1 ∧ ∀i=1..n : a[i] = a'[i+1]

    //pre: n>0
    public E element() {
        assert size > 0;

        return elements[head];

    }
    // post: ℝ = a[1] ∧ n = n' ∧ ∀i=1..n : a[i]' = a[i]

    public void clear() {
        Arrays.fill(elements, null);
        head = 0;
        size = 0;
    }
    // post:  n' = 0

    //pre: 1 <= i <= n
    public E get(int i) {
        assert 0 <= i && i < size;
        return elements[mod(head + i)];
    }
    //post: ℝ = a[i+1] ∧ n = n' ∧ ∀i=1..n : a[i]' = a[i]

    //pre: 1 <= i <=n & element != null
    public void set(int i, E element) {
        assert size > i && i >= 0 && element != null;
        elements[mod(head + i)] = element;
    }
    //post: n = n' ∧ ∀j={1..j}, {j+2..n} : a[j]' = a[j] && a[i+1]' = element

    //pre: element!=null
    public void push(E element) {
        assert element != null;

        ensureCapacity(size + 1);
        elements[mod(head - 1)] = element;
        size++;
        head = dec(head);
    }
    //post: n' = n + 1 && \forall i = 1..n: a'[i+1] = a[i] && a[1] = element

    //pre: n>0
    public E peek() {
        assert size > 0;

        return elements[mod(head + size - 1)];
    }
    // post: ℝ = a[n] ∧ n = n' ∧ ∀i=1..n : a[i]' = a[i]

    //pre: n>0
    public E remove() {
        assert size > 0;
        int tail = mod(head + size - 1);
        E element = elements[tail];
        elements[tail] = null;
        size--;
        return element;
    }
    // post: ℝ = a[n] ∧ n = n' − 1 ∧ ∀i=1..n : a[i] = a'[i]

    //pre: 0 <= capacity <= |elements| + 1
    //post: capacity <= |elements'| && n = n' ∧ ∀i=1..n : a[i]' = a[i]
    private void ensureCapacity(int capacity) {
        if (capacity > elements.length) {
            E[] temp = (E[])new Object[2 * capacity];
            int firsttnd = Math.min(elements.length, head + size) - head;
            System.arraycopy(elements, head, temp, 0, firsttnd);
            System.arraycopy(elements, mod(head + firsttnd), temp, firsttnd, elements.length - firsttnd);
            elements = temp;
            head = 0;
        }
    }

    //pre: |elements| > 0 && x >= - |elements|
    //post: immutable && 0 < = R < |elements| && \exist k \in Z: |R-1-x| = k * |elements|
    private int inc(int x) {
        return mod(x + 1);
    }

    //pre: |elements| > 0 && x >= - |elements|
    //post: immutable && 0 < = R < |elements| && \exist k \in Z: |R-x| = k * |elements|
    private int mod(int x) {
        return (x + elements.length) % elements.length;
    }

    //pre: |elements| > 0 && x >= - |elements|
    //post: immutable && 0 < = R < |elements| && \exist k \in Z: |R+1-x| = k * |elements|
    private int dec(int head) {
        return mod(head - 1);
    }

}