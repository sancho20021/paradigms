package expression.expressions;

import expression.parser.calculators.Calculator;

import java.util.Objects;

public abstract class BinarExp<T extends Number> extends UnarBinarExp<T> {
    private CommonExpression<T> a, b;

    protected BinarExp(CommonExpression<T> a, CommonExpression<T> b, Calculator<T> calculator) {
        super(calculator);
        this.a = a;
        this.b = b;
    }

    protected abstract T calculate(T a, T b);

    public T evaluate(T x, T y, T z) {
        return calculate(a.evaluate(x, y, z), b.evaluate(x, y, z));
    }

    public T evaluate(T x) {
        return calculate(a.evaluate(x), b.evaluate(x));
    }

    @Override
    public boolean equals(Object to) {
        if (to == null || getClass() != to.getClass()) {
            return false;
        }
        final BinarExp<T> other = (BinarExp<T>) to;
        return Objects.equals(a, other.a)
                && Objects.equals(b, other.b);
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b, getClass());
    }

    @Override
    public String toString() {
        return "(" + a + " " + getSymbol() + " " + b + ")";
    }

    @Override
    public String toMiniString() {
        int aPr = getPriority(a);
        int bPr = getPriority(b);
        boolean hasBEps = b instanceof BinarExp && ((BinarExp<T>) b).hasEps();
        StringBuilder str = new StringBuilder();
        str.append(withBrackets(aPr < firstPriority(), a.toMiniString()));
        str.append(" ").append(getSymbol()).append(" ");
        str.append(withBrackets(
                bPr < firstPriority() || (bPr == firstPriority()) && (hasBEps || isOrderImportant()),
                b.toMiniString()
        ));
        return str.toString();
    }

    protected abstract boolean isOrderImportant();

    protected abstract boolean hasEps();
}
