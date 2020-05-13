package expression.expressions;

import expression.parser.calculators.Calculator;

public class Max<T extends Number> extends BinarExp<T> {
    public Max(CommonExpression<T> a, CommonExpression<T> b, final Calculator<T> calculator) {
        super(a, b, calculator);
    }

    @Override
    protected T calculate(T a, T b) {
        return calculator.max(a, b);
    }

    @Override
    protected boolean isOrderImportant() {
        return false;
    }

    @Override
    protected boolean hasEps() {
        return false;
    }

    @Override
    protected String getSymbol() {
        return "max";
    }

    @Override
    protected int firstPriority() {
        return 0;
    }
}
