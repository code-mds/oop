package e5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.function.DoubleBinaryOperator;

public class Doubles {

    private BufferedReader bufferedReader;

    public Doubles(Reader reader) {
        this.bufferedReader = new BufferedReader(reader);
    }


    public Double next() throws IOException {
        this.bufferedReader.mark(1);

        int val;
        String s = "";

        for(;;){
            val = this.bufferedReader.read();

            if(val == '.')
                s += val;

            if(val >= '0' && val <= '9')
                s += val;

            if(Character.isWhitespace(val))
                break;
        }

        if(s.isEmpty())
            return null;

        return Double.valueOf(s);
    }
}
