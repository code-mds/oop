import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

public class Input {

    public static void main(String[] args) throws IOException {
        dumpFileInputStream(args);
        dumpByteArrayInputStream();
        dumpFileReader(args);
        dumpByteArrayReader();
    }

    /*
     * The method dumpInputStream(InputStream) commits to a general InputStream.
     * Thus, it can be invoked by passing any concrete implementation of InputStream.
     * 
     * Here, an example of passing a FileInputStream...
     */
    private static void dumpFileInputStream(String[] args) throws IOException {
        try (FileInputStream fin = new FileInputStream(args[0])) {
            dumpInputStream(fin);
        }
    }

    /*
     * ... and here by passing a ByteArrayInputStream.
     */
    private static void dumpByteArrayInputStream() throws IOException {
        try (ByteArrayInputStream bin = new ByteArrayInputStream(new byte[] {0x30, 0x31, 0x32});) {
            dumpInputStream(bin);
        }
    }

    /*
     * UTF-8 is a standard Unicode encoding which maps a Unicode character to 1, 2, 3 or 4 bytes, depending on the character.
     * E.g., in Unicode the code for the Euro symbol € is 0x20AC, which in UTF-8 is expressed by the byte sequence 0xE2, 0x82, 0xAC (3 bytes)
     * 
     * InputStreamReader is a bridge class that translates a stream of bytes (InputStream) to a stream of characters (Reader),
     * according to the name of the encoding passed as argument to the constructor.
     * 
     * A similar OutputStreamWriter does the opposite, translating a Writer to an OutputStream.
     */
    private static void dumpFileReader(String[] args) throws IOException {
        try (Reader in = new InputStreamReader(new FileInputStream(args[0]), "UTF-8")) {
            dumpReader(in);
        }
    }

    /*
     * CP1252 is an encoding that maps characters to single bytes. Thus, it can only accommodate 256 different characters.
     * The code for € in CP1252 is 0x80 (in Unicode it is 0x20AC).
     * 
     * Pay attention: here, decoding the byte 0x80 while reading produces the Java char € with code 0x20AC,
     * since Java characters are Unicode characters.
     * 
     * Thus, an InputStream returns the byte 0x80, while a Reader with encoding CP1252 returns the char 0x20AC,
     * even if CP1252 is a 1 byte/character encoding.
     */
    private static void dumpByteArrayReader() throws IOException {
        try (Reader in = new InputStreamReader(new ByteArrayInputStream(new byte[] {0x30, 0x31, (byte) 0x80}), "CP1252");) {
            dumpReader(in);
        }
    }

    /*
     * The method read() of InputStream returns an int.
     * The value is negative iff the stream's position is at the end.
     * Otherwise the value is in the range [0, 256), representing a byte.
     */
    private static void dumpInputStream(InputStream in) throws IOException {
        System.out.println("input stream");
        for (int b; (b = in.read()) >= 0;) {
            System.out.println(Integer.toHexString(b) + ": " + (char) b);
        }
        System.out.println();
    }

    /*
     * The method read() of Reader returns an int.
     * The value is negative iff the reader's  position is at the end.
     * Otherwise the value is in the interval [0, 65536), representing a char.
     */
    private static void dumpReader(Reader in) throws IOException {
        System.out.println("reader");
        for (int c; (c = in.read()) >= 0;) {
            System.out.println(Integer.toHexString(c) + ": " + (char) c);
        }
        System.out.println();
    }

}
