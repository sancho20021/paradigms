package queue;

import java.util.Arrays;

public class ArrayQueueModule {
    //a[1]...a[n]
    //operations with first and last elements

    //inv: n>=0
    //\forall i = 1..n a[i]!=null

    private static int head, size;
    private static Object[] elements = new Object[5];

    //pre: element!=null
    public static void enqueue(Object element) {
        assert element != null;

        ensureCapacity(size + 1);
        elements[mod(head + size)] = element;
        size++;
    }
    //post: n' = n + 1 && \forall i = 1..n: a'[i] = a[i] && a'[n'] = element

    //pre: n>0
    public static Object dequeue() {
        assert size > 0;
        Object element = elements[head];
        elements[head] = null;
        head = inc(head);
        size--;
        return element;
    }
    // post: ℝ = a[1] ∧ n = n' − 1 ∧ ∀i=1..n : a[i] = a'[i+1]

    //pre: n>0
    public static Object element() {
        assert size > 0;

        return elements[head];
    }
    // post: ℝ = a[1] ∧ n = n' ∧ ∀i=1..n : a[i]' = a[i]

    public static int size() {
        return size;
    }
    // post: ℝ = n ∧ n = n' c ∀i=1..n : a[i]' = a[i]

    public static boolean isEmpty() {
        return size == 0;
    }
    // post: ℝ = n > 0 ∧ n = n' ∧ ∀i=1..n : a[i]' = a[i]

    public static void clear() {
        Arrays.fill(elements, null);
        head = 0;
        size = 0;
    }
    // post: ∀i=1..n : a[i]' = null ∧ n' = 0

    //pre: 1 <= i <= n
    public static Object get(int i) {
        assert 0 <= i && i < size;
        return elements[mod(head + i)];
    }
    //post: ℝ = a[i+1] ∧ n = n' ∧ ∀i=1..n : a[i]' = a[i]

    //pre: 1 <= i <=n & element != null
    public static void set(int i, Object element) {
        assert size > i && i >= 0 && element != null;
        elements[mod(head + i)] = element;
    }
    //post: n = n' ∧ ∀j={1..i}, {i+2..n} : a[j]' = a[j] && a[i+1]' = element

    //pre: element!=null
    public static void push(Object element) {
        assert element != null;

        ensureCapacity(size + 1);
        elements[mod(head - 1)] = element;
        size++;
        head = dec(head);
    }
    //post: n' = n + 1 && \forall i = 1..n: a'[i+1] = a[i] && a[1] = element

    private static int dec(int head) {
        return mod(head - 1);
    }


    //pre: n>0
    public static Object peek() {
        assert size > 0;

        return elements[mod(head + size - 1)];
    }
    // post: ℝ = a[n] ∧ n = n' ∧ ∀i=1..n : a[i]' = a[i]

    //pre: n>0
    public static Object remove() {
        assert size > 0;
        int tail = mod(head + size - 1);
        Object element = elements[tail];
        elements[tail] = null;
        size--;
        return element;
    }
    // post: ℝ = a[n] ∧ n = n' − 1 ∧ ∀i=1..n : a[i] = a'[i]

    private static int inc(int x) {
        return mod(x + 1);
    }

    private static int mod(int x) {
        return (x + elements.length) % elements.length;
    }

    private static void ensureCapacity(int capacity) {
        if (capacity > elements.length) {
            Object[] temp = new Object[2 * capacity];
            int firstEnd = Math.min(elements.length, head + size)-head;
            System.arraycopy(elements, head, temp, 0, firstEnd);
            System.arraycopy(elements, mod(head + firstEnd), temp, firstEnd, elements.length - firstEnd);
            elements = temp;
            head = 0;
        }
    }
}