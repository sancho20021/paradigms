package expression.expressions;

import expression.parser.calculators.Calculator;

public class Subtract<T extends Number> extends BinarExp<T> {
    public Subtract(CommonExpression<T> a, CommonExpression<T> b, final Calculator<T> calculator) {
        super(a, b, calculator);
    }

    @Override
    protected String getSymbol() {
        return "-";
    }

    @Override
    protected T calculate(T a, T b) {
        return calculator.subtract(a, b);
    }

    @Override
    protected int firstPriority() {
        return 1;
    }

    @Override
    protected boolean hasEps() {
        return false;
    }

    @Override
    protected boolean isOrderImportant() {
        return true;
    }
}
