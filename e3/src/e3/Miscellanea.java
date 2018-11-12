package e3;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.IntStream;

public class Miscellanea {

    public static void main(String[] args) {
        System.out.println(almostFactorial(1000));
        System.out.println(unboundedStream());
        System.out.println(noFirst());
        functionObjects();
    }

    private static BigInteger almostFactorial(int n) {
        return IntStream.rangeClosed(1, n)
            .filter(i -> i % 2 != 0)
            .mapToObj(i -> BigInteger.valueOf(i))
            .reduce(BigInteger.ONE, (i1, i2) -> i1.multiply(i2));    
    }
    
    private static int unboundedStream() {
    /*
     * The stream returned on a Random is unbounded.
     * Still, we can filter and map on its elements, again obtaining unbounded streams.
     * The terminal operation, however, must be short-circuiting;
     * that is, its semantics must be defined to return even on unbounded streams.
     * findFirst() is a simple example of a short-circuiting terminal operation.
     * It returns a variant of Optional, though, because a first element
     * is only defined on non empty streams.
     * There's a getter to get at the content of an optional.
     * The getter throws an exception if the optional is empty.
     */
        return new Random().ints()
                .filter(i -> i % 99 == 0) // filtering on multiples of 99
                .map(i -> i * i)
                .findFirst().getAsInt();
    }

    private static int noFirst() {
    /*
     * Since findFirst() might fail to return an element when operating on an empty stream,
     * the optional returned by it might throw an exception, as here.
     * Here, the exception is caught to avoid terminating the program prematurely.
     */
        try {
            return Arrays.stream(new int[1_000])
                    .filter(i -> i % 99 != 0) // filtering on *non* multiples of 99
                    .map(i -> i * i)
                    .findFirst().getAsInt();
        } catch (RuntimeException e) {
            System.err.println(e);
            return -1;
        }
    }

    private static void functionObjects() {
    /*
     * λ expressions are objects on their own that act as functions.
     * Thus, they can be assigned to variables and fields,
     * passed as arguments, obtained as return values,
     * stored in data structures, etc.
     */

        Function<Integer, Function<Integer, Integer>>makeAdder = x -> y -> x + y;
        /*
         * As declared by its type, the λ makeAdder accepts an Integer
         * and returns a function that, in turn, accepts an Integer and returns an Integer.
         * The arrow -> associates to the right.
         * Hence, x -> y -> x + y is really a shorthand for x -> (y -> x + y)
         */
        
        Function<Integer, Integer> fiveAdder = makeAdder.apply(5);
        Function<Integer, Integer> elevenAdder = makeAdder.apply(11);
        /*
         * fiveAdder is a function that accepts an Integer and returns an Integer.
         * The goal of fiveAdder is to add its argument to 5.
         * It is defined as makeAdder.apply(5) which, more or less, results by replacing
         * x with 5 in the right hand side of the λ defining makeAdder.
         * Intuitively, but not rigorously, makeAdder.apply(5) returns
         * a function equivalent to y -> 5 + y.
         * 
         * Similarly, makeAdder.apply(11) returns something equivalent to y -> 11 + y.
         * Thus, the purpose of elevenAdder is to add its argument to 11.
         */
        
        System.out.println(fiveAdder.apply(7));
        System.out.println(fiveAdder.apply(8));
        System.out.println(elevenAdder.apply(7));
        /*
         * The adders are eventually executed by applying the arguments to them.
         * This happens  by means of apply(), the central method exposed by a function.
         */
    }
    
}
