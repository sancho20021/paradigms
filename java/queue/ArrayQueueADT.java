package queue;

import java.util.Arrays;

public class ArrayQueueADT {
    //a[1]...a[n]
    //operations with first and last elements

    //inv: n>=0
    //\forall i = 1..n a[i]!=null

    private int head, size;
    private Object[] elements = new Object[5];


    //pre: element!=null
    public static void enqueue(ArrayQueueADT queue, Object element) {
        assert element != null;

        ensureCapacity(queue, queue.size + 1);
        queue.elements[mod(queue, queue.head + queue.size)] = element;
        queue.size++;
    }
    //post: n' = n + 1 && \forall i = 1..n: a'[i] = a[i] && a[n'] = element

    //pre: n>0
    public static Object dequeue(ArrayQueueADT queue) {
        assert queue.size > 0;
        Object element = queue.elements[queue.head];
        queue.elements[queue.head] = null;
        queue.head = inc(queue, queue.head);
        queue.size--;
        return element;
    }
    // post: ℝ = a[1] ∧ n = n' − 1 ∧ ∀i=1..n : a[i] = a'[i+1]

    //pre: n>0
    public static Object element(ArrayQueueADT queue) {
        assert queue.size > 0;

        return queue.elements[queue.head];
    }
    // post: ℝ = a[1] ∧ n = n' ∧ ∀i=1..n : a[i]' = a[i]

    public static int size(ArrayQueueADT queue) {
        return queue.size;
    }
    // post: ℝ = n ∧ n = n' c ∀i=1..n : a[i]' = a[i]

    public static boolean isEmpty(ArrayQueueADT queue) {
        return queue.size == 0;
    }
    // post: ℝ = n > 0 ∧ n = n' ∧ ∀i=1..n : a[i]' = a[i]

    public static void clear(ArrayQueueADT queue) {
        Arrays.fill(queue.elements, null);
        queue.head = 0;
        queue.size = 0;
    }
    // post: ∀i=1..n : a[i]' = null ∧ n' = 0

    //pre: 1 <= i <= n
    public static Object get(ArrayQueueADT queue, int i) {
        assert 0 <= i && i < queue.size;
        return queue.elements[mod(queue, queue.head + i)];
    }
    //post: ℝ = a[i+1] ∧ n = n' ∧ ∀i=1..n : a[i]' = a[i]

    //pre: 1 <= i <=n & element != null
    public static void set(ArrayQueueADT queue, int i, Object element) {
        assert queue.size > i && i >= 0 && element != null;
        queue.elements[mod(queue, queue.head + i)] = element;
    }
    //post: n = n' ∧ ∀j={1..j}, {j+2..n} : a[j]' = a[j] && a[i+1]' = element

    //pre: element!=null
    public static void push(ArrayQueueADT queue, Object element) {
        assert element != null;

        ensureCapacity(queue, queue.size + 1);
        queue.elements[mod(queue, queue.head - 1)] = element;
        queue.size++;
        queue.head = dec(queue, queue.head);
    }
    //post: n' = n + 1 && \forall i = 1..n: a'[i+1] = a[i] && a[1] = element

    //pre: n>0
    public static Object peek(ArrayQueueADT queue) {
        assert queue.size > 0;

        return queue.elements[mod(queue, queue.head + queue.size - 1)];
    }
    // post: ℝ = a[n] ∧ n = n' ∧ ∀i=1..n : a[i]' = a[i]

    //pre: n>0
    public static Object remove(ArrayQueueADT queue) {
        assert queue.size > 0;
        int tail = mod(queue, queue.head + queue.size - 1);
        Object element = queue.elements[tail];
        queue.elements[tail] = null;
        queue.size--;
        return element;
    }
    // post: ℝ = a[n] ∧ n = n' − 1 ∧ ∀i=1..n : a[i] = a'[i]

    private static void ensureCapacity(ArrayQueueADT queue, int capacity) {
        if (capacity > queue.elements.length) {
            Object[] temp = new Object[2 * capacity];
            int firstEnd = Math.min(queue.elements.length, queue.head + queue.size) - queue.head;
            System.arraycopy(queue.elements, queue.head, temp, 0, firstEnd);
            System.arraycopy(queue.elements, mod(queue, queue.head + firstEnd), temp, firstEnd, queue.elements.length - firstEnd);
            queue.elements = temp;
            queue.head = 0;
        }
    }

    private static int inc(ArrayQueueADT queue, int x) {
        return mod(queue, x + 1);
    }

    private static int mod(ArrayQueueADT queue, int x) {
        return (x + queue.elements.length) % queue.elements.length;
    }

    private static int dec(ArrayQueueADT queue, int head) {
        return mod(queue, head - 1);
    }
}
