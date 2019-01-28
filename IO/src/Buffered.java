import java.io.*;

public class Buffered {

    /*
     * When dealing with streams that represent OS resources, like files,
     * it is often useful to wrap them into buffered streams to enhance performance.
     * 
     *  Here's an example without and with buffered streams.
     *  
     *  On long files, the speedup in performance can reach 2-3 orders of magnitude.
     */
    public static void main(String[] args) {
        printDuration(() -> unbuffered(args[0]), "unbuffered");
        printDuration(() -> buffered(args[0]), "buffered");
        printDuration(() -> multiByte(args[0]), "multibyte");
    }

    private static void multiByte(String path) {
        try (FileInputStream in = new FileInputStream(path)) {
            byte[] b = new byte[8 << 10];
            for (; in.read(b) >= 0;);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void buffered(String path) {
        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(path))) {
            for (; in.read() >= 0;);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void unbuffered(String path) {
        //java.io.RandomAccessFile

        try (InputStream in = new FileInputStream(path)) {
            for (; in.read() >= 0;);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void printDuration(Runnable code, String title) {
        System.out.print(title + "... ");
        System.out.flush();
        long begin = System.nanoTime();
        code.run();
        System.out.println((System.nanoTime() - begin) / 1_000_000 + " ms");
    }

}
