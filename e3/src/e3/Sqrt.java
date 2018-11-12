package e3;

import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

public class Sqrt {
    public static double sqrt(double x) {

        //double res = DoubleStream.builder().build().reduce(1.0, (s, t) -> calcSqrt(x, s));
        //double res = DoubleStream.iterate(0.0, (s) -> s + 1.0).reduce(1.0, Sqrt::calcSqrt);

        double res = DoubleStream.iterate(1.0, (s) -> calcSqrt(x, s))
                .filter( y -> { double eps = y*y - x; return -0.0000001 < eps && eps < 0.0000001; })
                .findFirst()
                .getAsDouble();

        return res;
    }


    private static double calcSqrt(double x, double s) {
        System.out.println("calcSqrt:" + s);
        return (s + x / s) / 2;
    }

}
