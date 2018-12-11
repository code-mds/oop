package e5;

public class TestCharset {
    public static void main(String[] args) {
        java.nio.charset.Charset.availableCharsets().forEach((k, c) -> System.out.println(k));
    }
}
