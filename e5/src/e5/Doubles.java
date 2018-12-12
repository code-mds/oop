package e5;

import java.io.IOException;
import java.io.Reader;

public class Doubles {

    private Reader reader;

    public Doubles(Reader reader) {
        this.reader = reader;
    }


    public Double next() {
        try {
            int val = this.reader.read();


        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
