package expression.expressions;

import expression.parser.calculators.Calculator;

public class Min<T extends Number> extends BinarExp<T> {
    public Min(CommonExpression<T> a, CommonExpression<T> b, final Calculator<T> calculator) {
        super(a, b, calculator);
    }

    @Override
    protected T calculate(T a, T b) {
        return calculator.min(a, b);
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
        return "min";
    }

    @Override
    protected int firstPriority() {
        return 0;
    }
}
