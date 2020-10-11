package queue;

import java.util.function.Predicate;

public abstract class AbstractQueue<E> implements Queue<E> {
    protected int size;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void removeIf(final Predicate<Object> predicate) {
        for (int count = size; count != 0; count--) {
            final E toTest = dequeue();
            if (!predicate.test(toTest)) {
                enqueue(toTest);
            }
        }
    }

    @Override
    public void retainIf(final Predicate<Object> predicate) {
        removeIf(predicate.negate());
    }

    @Override
    public void takeWhile(final Predicate<Object> predicate) {
        takeOrDropWhile(predicate, true);
    }

    @Override
    public void dropWhile(final Predicate<Object> predicate) {
        takeOrDropWhile(predicate, false);
    }

    private void takeOrDropWhile(final Predicate<Object> predicate, final boolean start) {
        boolean save = start;
        for (int count = size; count != 0; count--) {
            final E toTest = dequeue();
            if (!predicate.test(toTest)) {
                save = !start;
            }
            if (save) {
                enqueue(toTest);
            }
        }
    }
}
