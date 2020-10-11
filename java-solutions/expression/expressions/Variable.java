package expression.expressions;

import java.util.Objects;

public class Variable<T extends Number> implements CommonExpression<T> {
    private String name;

    public Variable(String name) {
        this.name = name;
    }

    @Override
    public T evaluate(T x) {
        return x;
    }

    @Override
    public T evaluate(T x, T y, T z) {
        switch (name) {
            case "x":
                return x;
            case "y":
                return y;
            default:
                return z;
        }
    }

    @Override
    public boolean equals(Object to) {
        if (to == null || to.getClass() != this.getClass()) {
            return false;
        }
        return name.equals(((Variable<T>) to).name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public String toMiniString() {
        return toString();
    }
}
