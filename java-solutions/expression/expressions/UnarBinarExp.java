package expression.expressions;

import expression.parser.calculators.Calculator;

public abstract class UnarBinarExp<T extends Number> implements CommonExpression<T> {
    protected Calculator<T> calculator;

    protected UnarBinarExp(final Calculator<T> calculator) {
        this.calculator = calculator;
    }

    protected abstract String getSymbol();

    protected int getPriority(CommonExpression<T> a) {
        return a instanceof UnarBinarExp ? ((UnarBinarExp<T>) a).firstPriority() : Integer.MAX_VALUE;
    }

    protected String withBrackets(boolean isNec, String string) {
        return withString(isNec, "(") + string + withString(isNec, ")");
    }

    protected String withString(boolean isNec, String string) {
        return isNec ? string : "";
    }

    protected abstract int firstPriority();
}
