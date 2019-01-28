import crypto.hash.Algorithm;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Hashing {
    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        String algo = "crypto.hash.MD5"; //args[0];
        String fileName = "C:\\Users\\massi\\source\\repos\\oop\\LICENSE"; //args[1]

        Algorithm algorithm = Hashing.instanceFor(algo, 313_115);
        try (FileInputStream data = new FileInputStream(fileName)) {
            System.out.println("id: " + algorithm.getId() +
                    ", length: " + algorithm.hash(data).length);
        }
    }

    public static Algorithm instanceFor(String algo, long id) throws NoSuchMethodException, ClassNotFoundException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<Algorithm> cls = (Class<Algorithm>)Class.forName(algo);
        Constructor<Algorithm> c = cls.getConstructor(long.class);
        Algorithm o = c.newInstance(id);
        return o;
    }
}
