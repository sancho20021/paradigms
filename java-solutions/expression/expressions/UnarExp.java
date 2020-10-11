package expression.expressions;

import expression.parser.calculators.Calculator;

import java.util.Objects;

public abstract class UnarExp<T extends Number> extends UnarBinarExp<T> {
    private CommonExpression<T> a;

    protected UnarExp(CommonExpression<T> a, Calculator<T> calculator) {
        super(calculator);
        this.a = a;
    }

    protected abstract T calculate(T a);

    @Override
    public T evaluate(T x, T y, T z) {
        return calculate(a.evaluate(x, y, z));
    }

    @Override
    public T evaluate(T x) {
        return calculate(a.evaluate(x));
    }

    @Override
    public boolean equals(Object to) {
        if (to == null || getClass() != to.getClass()) {
            return false;
        }
        final UnarExp<T> other = (UnarExp<T>) to;
        return Objects.equals(a, other.a);
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, getClass());
    }

    @Override
    public String toString() {
        return "(" + getSymbol() + a + ")";
    }

    @Override
    public String toMiniString() {
        int aPr = getPriority(a);
        return getSymbol() + " " + withBrackets(aPr < firstPriority(), a.toMiniString());
    }
}
