package expression.exceptions;

public class LogException extends CalculationException {
    public LogException(Number a, Number b) {
        super("Invalid log arguments: " + a + ", " + b);
    }
}
