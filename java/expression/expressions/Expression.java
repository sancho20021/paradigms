package expression.expressions;

import expression.ToMiniString;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public interface Expression<T extends Number> extends ToMiniString {
    T evaluate(T x);
}
