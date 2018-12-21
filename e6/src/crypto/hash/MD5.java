package crypto.hash;

import java.io.InputStream;

public class MD5 implements Algorithm {
    private final long id;

    public MD5(long id) {
        this.id = id;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public byte[] hash(InputStream stream) {
        return new byte[16];
    }
}
