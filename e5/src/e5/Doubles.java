package e5;

import java.io.*;

public class Doubles {

    private BufferedReader bufferedReader;
    private char c;
    private StringBuilder sb;

    public Doubles(Reader reader) {
        this.bufferedReader = new BufferedReader(reader);
        this.sb = new StringBuilder();
    }

    public Double next() throws IOException {
        clearBuffer();
        skipWhiteSpaces();

        boolean foundDecimal = false;
        boolean hasDigit = false;

        for(;;){
            markAndRead(2);

            if(!foundDecimal && isDecimalSeparator()) {
                append();
                foundDecimal = true;
            }
            else if(isDigit()) {
                append();
                hasDigit = true;
            }
            else
                break;
        }
        reset();

        if(sb.length() == 0 || !hasDigit)
            return null;

        return Double.valueOf(sb.toString());
    }

    private boolean isDigit() {
        return Character.isDigit(c);
    }

    private boolean isDecimalSeparator() {
        return c == '.';
    }

    private void append() {
        sb.append(c);
    }

    private void clearBuffer() {
        sb.setLength(0);
    }

    private void skipWhiteSpaces() throws IOException {
        markAndRead(1);
        while(isWhiteSpace())
            skipWhiteSpaces();
        reset();
    }

    private void reset() throws IOException {
        this.bufferedReader.reset();
    }

    private boolean isWhiteSpace() {
        return Character.isWhitespace(c);
    }

    private void markAndRead(int i) throws IOException {
        this.bufferedReader.mark(i);
        c = (char)this.bufferedReader.read();
    }

    public static void main(String[] args) throws IOException {
        testParser(" 1. .2 1.2   23  3..14 ab 3.14");
        testParser(" 1..1");
    }

    private static void testParser(String input) throws IOException {
        System.out.println("TEST Parser: " + input);
        try(Reader reader = new StringReader(input)) {
            Doubles doubles = new Doubles(reader);
            Double d;
            while((d = doubles.next()) != null)
                System.out.println(d);
        }
    }
}
