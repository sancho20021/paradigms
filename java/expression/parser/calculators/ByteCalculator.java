package expression.parser.calculators;

import expression.exceptions.DBZException;

public class ByteCalculator implements Calculator<Byte> {
    @Override
    public Byte add(Byte a, Byte b) {
        return (byte) (a + b);
    }

    @Override
    public Byte subtract(Byte a, Byte b) {
        return (byte) (a - b);
    }

    @Override
    public Byte divide(Byte a, Byte b) {
        if (b == 0) {
            throw new DBZException(a);
        }
        return (byte) (a / b);
    }

    @Override
    public Byte multiply(Byte a, Byte b) {
        return (byte) (a * b);
    }

    @Override
    public Byte negate(Byte a) {
        return (byte) -a;
    }

    @Override
    public Byte parse(String s) {
//        return Byte.parseByte(s);
        return (byte)Integer.parseInt(s);
    }

    @Override
    public Byte count(Byte a) {
        return (byte) Integer.bitCount(a & 0xFF);
    }

    @Override
    public Byte min(Byte a, Byte b) {
        return (byte) Integer.min(a, b);
    }

    @Override
    public Byte max(Byte a, Byte b) {
        return (byte) Integer.max(a, b);
    }
}
