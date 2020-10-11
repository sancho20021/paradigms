package expression.expressions;

import java.util.Objects;

public class Const<T extends Number> implements CommonExpression<T> {
    private T value;

    public Const(T value) {
        this.value = value;
    }

    @Override
    public T evaluate(T x) {
        return value;
    }

    @Override
    public T evaluate(T x, T y, T z) {
        return value;
    }

    @Override
    public boolean equals(Object to) {
        if (to == null || getClass() != to.getClass()) {
            return false;
        }
        return value.equals(((Const<T>) to).value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public String toMiniString() {
        return toString();
    }
}
