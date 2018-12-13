package e5;

import java.io.*;

public class TestUTF8 {
    public static void main(String[] args) throws IOException {
        String input = "test_cp1252.txt";
        String output = "test_utf8.txt";

        createCP1252file(input);
        convertToUTF8(input, output);
    }

    private static void convertToUTF8(String input, String output) throws IOException {
        try (Reader in = new InputStreamReader(new FileInputStream(input), "CP1252");
             Writer out = new OutputStreamWriter(new FileOutputStream(output), "UTF-8")) {
            for (int c; (c = in.read()) >= 0;) {
                out.write(c);
            }
        }
    }

    private static void createCP1252file(String filename) throws IOException {
        try (Writer out = new OutputStreamWriter(new FileOutputStream(filename), "CP1252")) {
            out.write("\u20AC" + "\u0160" + "\u2122");
        }
    }
}
