package e3;

import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

public class Sqrt {
    public static double sqrt(double x) {

        double res = IntStream.rangeClosed(1, 5)
                                .mapToDouble( i -> Double.valueOf(i))
                                .reduce(1.0, (s, t) -> (s + x / s) / 2);

        return res;
    }
}
