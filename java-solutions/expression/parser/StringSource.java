package expression.parser;

import expression.exceptions.ParserException;

public class StringSource implements Source {
    private final String string;
    private int pos;

    public StringSource(final String string) {
        this.string = string;
        pos = 0;
    }

    @Override
    public boolean hasNext() {
        return pos < string.length();
    }

    @Override
    public char next() {
        return string.charAt(pos++);
    }

    @Override
    public ParserException error(final String message) {
        return new ParserException((pos - 1) + ": " + message);
    }
}
