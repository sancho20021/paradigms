package expression.exceptions;

public class DBZException extends CalculationException {
    public DBZException(Number a) {
        super("Division by zero: " + a + "/ 0");
    }
}
