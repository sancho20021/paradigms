package expression.expressions;

public interface GenericExpression<T extends Number> {
    public T evaluate(T x, T y, T z);
    public T evaluate(T x);
    public String toMiniString();
}
