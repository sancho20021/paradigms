package expression.expressions;

import expression.parser.calculators.Calculator;

public class Negate<T extends Number> extends UnarExp<T> {

    public Negate(CommonExpression<T> a, final Calculator<T> calculator) {
        super(a, calculator);
    }

    @Override
    protected T calculate(T a) {
        return calculator.negate(a);
    }

    @Override
    protected String getSymbol() {
        return "-";
    }

    @Override
    protected int firstPriority() {
        return 2;
    }

}
