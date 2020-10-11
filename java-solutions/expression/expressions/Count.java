package expression.expressions;

import expression.parser.calculators.Calculator;

public class Count<T extends Number> extends UnarExp<T> {
    public Count(CommonExpression<T> a, final Calculator<T> calculator) {
        super(a, calculator);
    }

    @Override
    protected T calculate(T a) {
        return calculator.count(a);
    }

    @Override
    protected String getSymbol() {
        return "count";
    }

    @Override
    protected int firstPriority() {
        return 2;
    }
}
