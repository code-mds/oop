package crypto.hash;

import java.io.InputStream;

public class SHA_1 implements Algorithm {
    private final long id;

    public SHA_1(long id) {
        this.id = id;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public byte[] hash(InputStream stream) {
        return new byte[20];
    }
}
