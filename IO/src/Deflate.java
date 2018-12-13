import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.DeflaterInputStream;

public class Deflate {

    /*
     * This example is meant to show how InputStreams can be wrapped around others to add functionality.
     * Similarly for OutputStreams.
     * And analogously for Readers and Writers (not shown here)
     */
    public static void main(String[] args) {
        printDuration(() -> singleByte(args), "unbuffered");
        printDuration(() -> bufferedSingleByte(args), "buffered");
        printDuration(() -> multiByte(args), "multibyte");
    }

    /*
     * Here, a DeflaterInputStream is wrapped around a FileInputStream.
     * In this way, the bytes obtained by reading from the DeflaterInputStream represent
     * a compressed version of the original FileInputStream.
     * At the same time, these bytes are written to a FileOutputStream.
     * 
     * Fantastically slow!
     */
    private static void singleByte(String[] args) {
        try (DeflaterInputStream in = new DeflaterInputStream(new FileInputStream(args[0]));
                FileOutputStream fout = new FileOutputStream(args[1])) {
            for (int b; (b = in.read()) >= 0;) {
                fout.write(b);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /*
     * Wrapping BufferedInputStreams and BuffereredOutputStreams around the original streams
     * greatly enhances the performance.
     * 
     * In addition, BufferedInputStream fully supports mark/reset functionality (not used here).
     */
    private static void bufferedSingleByte(String[] args) {
        try (BufferedInputStream in = new BufferedInputStream(new DeflaterInputStream(new FileInputStream(args[0])));
                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(args[1]))) {
            for (int b; (b = in.read()) >= 0;) {
                out.write(b);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /*
     * A similar gain in performance can be obtained by operating on blocks of bytes rather than on single bytes.
     * 
     * The method read(byte[] b) of InputStream reads bytes from the current position into b.
     * The count of the bytes actually read is returned, or a negative value is returned when reading beyond the end of the stream.
     * 
     * Analogously, write(byte[] b, int from, int length) writes the bytes in b, from the specified range, out to the OutputStream.
     */
    private static void multiByte(String[] args) {
        try (DeflaterInputStream in = new DeflaterInputStream(new FileInputStream(args[0]));
                FileOutputStream out = new FileOutputStream(args[1])) {
            byte[] b = new byte[8 << 10];
            for (int count; (count = in.read(b)) > 0;) {
                out.write(b, 0, count);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void printDuration(Runnable code, String title) {
        System.out.println(title);
        long begin = System.nanoTime();
        code.run();
        System.out.println((System.nanoTime() - begin) / 1_000_000 + " ms");
        System.out.println();
    }

}
