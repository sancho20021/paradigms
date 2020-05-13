package queue;

import java.util.function.Predicate;

public interface Queue<E> {
    //a[1]...a[n]
    //operations with first and last elements

    //inv: n>=0
    //\forall i = 1..n a[i]!=null

    //pre: element!=null
    //post: n' = n + 1 && \forall i = 1..n: a'[i] = a[i] && a[n']' = element
    void enqueue(E element);

    //pre: n>0
    // post: ℝ = a[1] ∧ n' = n − 1 ∧ ∀i=1..n' : a[i]' = a[i+1]
    E dequeue();

    //pre: n>0
    // post: ℝ = a[1] ∧ n' = n ∧ ∀i=1..n : a[i]' = a[i]
    E element();

    // post: ℝ = n ∧ n = n' c ∀i=1..n : a[i]' = a[i]
    int size();

    // post: ℝ = n > 0 ∧ n = n' ∧ ∀i=1..n : a[i]' = a[i]
    boolean isEmpty();

    // post: n' = 0 ∧ a' = {}
    void clear();

    //post: a' = [a_i1 ... a_in']: ∀ j: 1 <= i_j <= n
    // ∧ (∀  1 <= k <= n: p(a_k) == false <-> ∃ 1 <= j <= n': a'_j == a_k)
    // ∧ ∀ 1 <= j < n': i_j < i_{j+1}
    void removeIf(Predicate<Object> predicate);

    //post: a' = [a_i1 ... a_in']: ∀ j: 1 <= i_j <= n)
    // ∧ (∀ 1 <= k <= n: p(a_k) == true <-> ∃ 1 <= j <= n': a'_j == a_k)
    // ∧ ∀ 1 <= j < n': i_j < i_{j+1}
    void retainIf(Predicate<Object> predicate);

    //post: n' <=n ∧ a' = [a_1 ... a_n'] ∧ ∀ 1 <= i <= n': p(a'_i) == true ∧ (n' < n -> p(a_n'+1) == false)
    void takeWhile(Predicate<Object> predicate);

    //post: n' <= n ∧ a' = [a_i ... a_n'] ∧ ∀ 1 <= j < i: p(a_j) == false ∧ (i < n -> p(a_i) == true)
    void dropWhile(Predicate<Object> predicate);
}
