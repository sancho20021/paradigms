package expression.parser;

import queue.ArrayQueue;
import queue.IndexedQueue;

public class BaseParser {
    private Source source;
    protected char ch;
    private IndexedQueue<Character> buf;

    public BaseParser() {
        buf = new ArrayQueue<>();
    }
    //ch is always the element previous to first element of buf;

    protected void setSource(final Source source) {
        this.source = source;
    }

    private char getCharFromSource() {
        return source.hasNext() ? source.next() : '\0';
    }

    protected void nextChar() {
        ch = buf.size() > 0 ? buf.dequeue() : getCharFromSource();
    }

    private void ensureBuf(int size) {
        while (buf.size() < size) {
            buf.enqueue(getCharFromSource());
        }
    }

    protected boolean test(char expected) {
        if (ch == expected) {
            nextChar();
            return true;
        }
        return false;
    }


    protected void expect(final char c) {
        if (ch != c) {
            throw error("Expected '" + c + "', found '" + ch + "'");
        }
        nextChar();
    }

    protected boolean test(String s) {
        if (ch != s.charAt(0)) {
            return false;
        }
        if (compareBuf(s.substring(1))) {
            for (int i = 0; i < s.length(); i++) {
                nextChar();
            }
            return true;
        }
        return false;
    }

    private boolean compareBuf(String s) {
        ensureBuf(s.length());
        for (int i = 0; i < s.length(); i++) {
            if (!buf.get(i).equals(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    protected void expect(final String value) {
        for (char c : value.toCharArray()) {
            expect(c);
        }
    }

    protected RuntimeException error(final String message) {
        return source.error(message);
    }

    protected boolean between(final char from, final char to) {
        return from <= ch && ch <= to;
    }
}
