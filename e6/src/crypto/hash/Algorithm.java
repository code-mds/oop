package crypto.hash;

import java.io.InputStream;

public interface Algorithm {
    long getId();
    byte[] hash(InputStream stream);
}
