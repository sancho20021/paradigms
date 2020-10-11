package expression.parser;

public interface Source {
    boolean hasNext();
    char next();
    RuntimeException error(final String message);
}
