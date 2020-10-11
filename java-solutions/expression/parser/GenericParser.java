package expression.parser;

import expression.expressions.GenericExpression;

public interface GenericParser<T extends Number> {
    public GenericExpression<T> parse(String expression);
}
