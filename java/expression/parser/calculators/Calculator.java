package expression.parser.calculators;

import expression.exceptions.DBZException;

public interface Calculator<T extends Number> {
    T add(T a, T b);

    T subtract(T a, T b);

    T divide(T a, T b);

    T multiply(T a, T b);

    T negate(T a);

    T parse(String s);

    T count(T a);

    T min(T a, T b);

    T max(T a, T b);

//    T log(T a, T b);
//
//    T log2(T a);
//
//    T pow(T a, T b);
//
//    T pow2(T a);
}
