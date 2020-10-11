package expression.parser.calculators;

import expression.exceptions.DBZException;

public class FloatCalculator implements Calculator<Float> {
    @Override
    public Float add(Float a, Float b) {
        return a + b;
    }

    @Override
    public Float subtract(Float a, Float b) {
        return a - b;
    }

    @Override
    public Float divide(Float a, Float b) {
        /*if (b == 0) {
            throw new DBZException(a);
        }*/
        return a / b;
    }

    @Override
    public Float multiply(Float a, Float b) {
        return a * b;
    }

    @Override
    public Float negate(Float a) {
        return -a;
    }

    @Override
    public Float parse(String s) {
        return Float.parseFloat(s);
    }

    @Override
    public Float count(Float a) {
        return (float) Integer.bitCount(Float.floatToIntBits(a));
    }

    @Override
    public Float min(Float a, Float b) {
        return Float.min(a, b);
    }

    @Override
    public Float max(Float a, Float b) {
        return Float.max(a, b);
    }
}
