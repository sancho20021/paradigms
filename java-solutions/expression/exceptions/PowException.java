package expression.exceptions;

public class PowException extends CalculationException {
    public PowException(Number a, Number b) {
        super("Invalid pow arguments: " + a + ", " + b);
    }
}
