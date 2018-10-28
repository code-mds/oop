package e3;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MainTransactions {

    public static void main(String[] args) {
        printWithIterators(Transactions.getAllTransactions());
        printWithStreams(Transactions.getAllTransactions());
        
        System.out.println("double arithmetic: " + totalDoubleRevenues(Transactions.getAllTransactions()));
        System.out.println("BigDecimal arithmetic with 3 arguments reduce: " + totalBigDecimalRevenuesWithReduce3(Transactions.getAllTransactions()));
        System.out.println("BigDecimal arithmetic with 2 arguments reduce: " + totalBigDecimalRevenuesWithReduce2(Transactions.getAllTransactions()));
        System.out.println("seller names as string with 3 arguments reduce: " + sellerNamesAsStringWithReduce3(Transactions.getAllTransactions()));
        System.out.println("seller names as string with 2 arguments reduce: " + sellerNamesWithReduce2(Transactions.getAllTransactions()));
        System.out.println("seller names as string with collect: " + sellerNamesAsStringWithCollect(Transactions.getAllTransactions()));
        System.out.println("seller names as list with lambdas: " + sellersNamesAsListWithLambdas(Transactions.getAllTransactions()));
        System.out.println("seller names as list with method references: " + sellersNamesAsListWithMethodReferences(Transactions.getAllTransactions()));
        System.out.println("seller names as list with collector: " + sellersNamesAsListWithCollector(Transactions.getAllTransactions()));
        System.out.println("sellers by age: " + sellersByAge(Transactions.getAllTransactions()));
        System.out.println("sellers count by age: " + sellerCountByAge(Transactions.getAllTransactions()));
    }

    /**
     *  Prints the names of all distinct sellers that are involved in transactions also involving
     *  senior buyers aged 65 or more. The names are printed in lexicographical order.
     * @param allTransactions
     */
    private static void printWithIterators(Collection<Transaction> allTransactions) {
        System.out.println("with iterators");
        System.out.println("----");

        HashSet<Person> uniqueSellers = new HashSet<>();
        /*
         * A Set is used to keep track of unique sellers
         */
        for (Transaction transaction : allTransactions) {
            if (transaction.getBuyer().getAge() >= 65) {
                uniqueSellers.add(transaction.getSeller());
            }
        }
        
        ArrayList<Person> sorted = new ArrayList<>(uniqueSellers);
        Collections.sort(sorted);
        /*
         * The sellers are sorted according to compareTo() in class e3.Person
         */

        for (Person seller : sorted) {
            System.out.println(seller.getName());
        }
        
        System.out.println();
    }

    /**
     *  Prints the names of all distinct sellers that are involved in transactions also involving
     *  senior buyers aged 65 or more. The names are printed in lexicographical order.
     * @param allTransactions
     */
    private static void printWithStreams(Collection<Transaction> allTransactions) {
        System.out.println("with streams");
        System.out.println("----");
        
        allTransactions.stream()
        /* 
         * stream() starts the pipeline on the source, a collection of e3.Transaction in this case.
         * Returns a stream of e3.Transaction.
         */
            .filter(t -> t.getBuyer().getAge() >= 65)
            /* 
             * filter() is an intermediate, light-weight, stateless operation.
             * Here, it operates on a stream of e3.Transaction.
             * Its argument is a predicate, a function that returns a boolean.
             * The predicate tests each element of the stream to select those that match the condition.
             * filter() returns a stream of e3.Transaction of just the elements that meet the predicate.
             */
            .map(t -> t.getSeller())
            /* 
             * map() is an intermediate, light-weight, stateless operation.
             * Here, it operates on a stream of e3.Transaction.
             * It applies its argument, a λ, on each element of the stream.
             * The λ returns a e3.Person, namely the seller of the the e3.Transaction.
             * Returns a stream of e3.Person.
             */
            .distinct()
            /* 
             * distinct() is an intermediate, heavy-weight, stateful operation.
             * It bases on the equals() method of the elements to get rid of duplicates.
             * Here, it operates on a stream of e3.Person, which overrides both equals() and hashCode().
             * Returns a stream of e3.Person.
             */
            .sorted()
            /* 
             * sorted() is an intermediate, heavy-weight, stateful operation.
             * It sorts the elements based on their override of compareTo() inherited from Comparable.
             * Here, it operates on a stream of e3.Person, which happens to implement Comparable.
             * Returns a stream of e3.Person.
             */
            .forEach(s -> System.out.println(s.getName()));
            /* 
             * forEach() is a terminal operation.
             * Here, it operates on a stream of e3.Person.
             * It returns nothing because its return type is void.
             * Its purpose is to produce side effects, not to return something useful.
             * The λ is applied to each element of the stream for its side effect, here printing a name.
             */
        
        System.out.println();
    }
    
    private static double totalDoubleRevenues(Collection<Transaction> allTransactions) {
    /* 
     * Due to the binary arithmetic of double, the result is counterintuitive and approximate.
     */
    
        return allTransactions.stream()
            .mapToDouble(t -> t.getAmount().doubleValue())
            /* 
             * mapToDouble() is an intermediate, stateless operation.
             * Here, it operates on a stream of e3.Transaction.
             * It applies its argument on each element of the stream.
             * The λ returns a the amount, converted to a double, of a e3.Transaction.
             * mapToDouble() returns a stream of double, a specialized stream.
             */
            .sum();
            /* 
             * sum() is a terminal operation exposed only by some specialized numerical streams.
             * Here, it operates on a stream of double.
             * It returns a double, the sum of all elements of the specialized stream of double.
             */

    }

    private static BigDecimal totalBigDecimalRevenuesWithReduce3(Collection<Transaction> allTransactions) {
    /* 
     * Doing BigDecimal arithmetic is more intuitive,
     * but requires an explicit, more cumbersome reduction terminal operation.
     * 
     * Generally, a reduction transforms a stream to a single, aggregate value.
     * Here, for example, a reduction transform a stream of e3.Transaction to a BigDecimal, the sum of the amounts.
     * 
     * The reduction operation on a Stream<T> specifies:
     * (1) The identity as a value of type U, i.e., the value returned on an empty stream;
     * (2) An associative accumulator binary function which:
     *     takes a value of type U and a value of type T or a supertype of T;
     *     combines them to return a value of type U.
     * (3) An associative combiner binary function which:
     *     takes two values of type U;
     *     combines them to return a value of type U
     */
        
        return allTransactions.stream()
            .reduce(BigDecimal.ZERO, (x, t) -> x.add(t.getAmount()), (x, y) -> x.add(y));
            /* 
             * reduce() is a terminal operation.
             * In this case, it operates on a stream of e3.Transaction.
             * 
             * It starts with BigDecimal.ZERO, the identity of a sum, of type BigDecimal.
             * 
             * The accumulator takes a value of type BigDecimal (the current sum) and a e3.Transaction.
             * It updates the current sum by adding the amount of the e3.Transaction
             * and returns the result updating the sum, a BigDecimal.
             * 
             * The combiner takes the possible partial sums of type BigDecimal and combines them by addition.
             * Here, the combiner can also be expressed more succinctly as BigDecimal::add.
             */
    }
    
    private static BigDecimal totalBigDecimalRevenuesWithReduce2(Collection<Transaction> allTransactions) {
    /* 
     * There's an alternate version of reduce() that works when the accumulator
     * is a binary function that takes two arguments and returns a value, all of the same type as the identity.
     * 
     * Here, however, before using this variant of reduce(), a map() is required to transform the stream of e3.Transaction
     * into a stream of BigDecimal.
     */
        return allTransactions.stream()
                .map(t -> t.getAmount())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private static String sellerNamesAsStringWithReduce3(Collection<Transaction> allTransactions) {
    /*
     * This example is similar to the above, but manipulates String values rather than BigDecimal numbers.
     */
        return allTransactions.stream()
                .reduce("", (s, t) -> s + ">" + t.getSeller().getName(), (s1, s2) -> s1.concat(s2));
                /*
                 * The combiner can also be expressed more succinctly as String::concat
                 */

    }
    
    private static String sellerNamesWithReduce2(Collection<Transaction> allTransactions) {
    /*
     * Similarly for the 2 arguments variant of reduce().
     */
        return allTransactions.stream()
                .map(t -> t.getSeller().getName())
                .reduce("", (s1, s2) -> s1 + ">" + s2);
    }
    
    private static String sellerNamesAsStringWithCollect(Collection<Transaction> allTransactions) {
    /* 
     * A collect() terminal operation maintains the current state of the accumulation process in an object.
     * This contrasts with reduce(), in which the accumulator and the combiner
     * return newly instantiated values for each element in the stream.
     * 
     * In sellersWith*Reduce() above, a new temporary String is instantiated for every element.
     * 
     * Here, the intermediate results are maintained in a (mutable) StringBuilder.
     * Often, this leads to more efficient code.
    
     * A collect() on a Stream<T> specifies:
     * (1) A supplier, i.e., a nullary function that returns a a new mutable instance of the identity of type R.
     * (2) An associative accumulator binary consumer function which:
     *     takes a value r of type R and a value t of type T or a supertype of T
     *     modifies the state of r to reflect the combined value of the previous state in r and of t. 
     *     Returns nothing.
     * (3) An associative combiner binary function which:
     *     takes two values r1 and r2 of type R
     *     modifies the state of r1 to reflect the combined value of the previous state in r1 and of t.
     *     Returns nothing.
     */
        return allTransactions.stream()
                .collect(() -> new StringBuilder(), (sb, t) -> sb.append(">").append(t.getSeller().getName()), (sb1, sb2) -> sb1.append(sb2))
                /* 
                 * collect() is a terminal operation.
                 * In this case, it operates on a stream of e3.Transaction.
                 * 
                 * It starts with a supplier which instantiates an empty StringBuilder, the identity of an append().
                 * 
                 * The accumulator takes a value of type StringBuilder (the current result of the append) and a e3.Transaction.
                 * It updates the current instance of the string builder by appending the name of the seller of the e3.Transaction,
                 * thus mutating the instance rather than returning a new value.
                 * 
                 * The combiner then combines all partial StringBuilder instances
                 * into a final instance.
                 * It does so in a similar way as the accumulator does, by repeatedly mutating a new instance
                 * provided by the supplier and appending them there in the encounter order.
                 * Here, the combiner can also be expressed more succinctly as StringBuilder::append.
                 * 
                 * In this case, collect() returns a StringBuilder.
                 */
                .toString();
    }
    
    private static List<String> sellersNamesAsListWithLambdas(Collection<Transaction> allTransactions) {
    /*
     *  Study the types of the supplier, accumulator and combiner carefully.
     */
        return allTransactions.stream()
                .collect(() -> new ArrayList<>(), (l, t) -> l.add(t.getSeller().getName()), (l1, l2) -> l1.addAll(l2));
    }
    
    private static List<String> sellersNamesAsListWithMethodReferences(Collection<Transaction> allTransactions) {
    /*
     *  The same as above but with method references where possible.
     */
        return allTransactions.stream()
                .collect(ArrayList::new, (l, t) -> l.add(t.getSeller().getName()), List::addAll);
    }
    
    private static List<String> sellersNamesAsListWithCollector(Collection<Transaction> allTransactions) {
    /*
     *  The same as above but with a collector.
     */
        return allTransactions.stream()
                .map(t -> t.getSeller().getName())
                .collect(Collectors.toList());
    }
    
    private static Map<Object, List<Person>> sellersByAge(Collection<Transaction> allTransactions) {
    /* 
     * An example of grouping collector.
     */
        return allTransactions.stream()
                .map(t -> t.getSeller())
                .collect(Collectors.groupingBy(
                        p -> p.getAge()));
    }

    private static Map<Integer, Long> sellerCountByAge(Collection<Transaction> allTransactions) {
    /*
     * An example of grouping collector, here combined with an additional counting downstream.
     */
        return allTransactions.stream()
                .map(t -> t.getSeller())
                .collect(Collectors.groupingBy(
                        p -> p.getAge(),
                        Collectors.counting()));
    }
    
}
