package queue;

public interface IndexedQueue<E> extends Queue<E> {
    //pre: 1 <= i <= n
    public E get(int i);
    //post: ℝ = a[i+1] ∧ n = n' ∧ ∀i=1..n : a[i]' = a[i]

    //pre: 1 <= i <=n & element != null
    public void set(int i, E element);
    //post: n = n' ∧ ∀j={1..j}, {j+2..n} : a[j]' = a[j] && a[i+1]' = element
}
