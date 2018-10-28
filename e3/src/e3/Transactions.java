package e3;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;


public class Transactions {

    private static final Person s1 = new Person("Irene", LocalDate.of(1993, 3, 4));
    private static final Person s2 = new Person("Giacomone", LocalDate.of(1987, 4, 3));
    private static final Person s3 = new Person("Alex", LocalDate.of(1978, 9, 9));

    private static final Person b1 = new Person("Sandrina", LocalDate.of(1965, 11, 20));
    private static final Person b2 = new Person("Giulia", LocalDate.of(1936, 12, 4));
    private static final Person b3 = new Person("Sergio", LocalDate.of(1966, 10, 14));
    private static final Person b4 = new Person("Luigi", LocalDate.of(1922, 7, 4));
    
    private static List<Transaction>allTransactions = Arrays.asList(
            new Transaction(s1, b1, new BigDecimal("2.06")),
            new Transaction(s1, b2, new BigDecimal("2.06")),
            new Transaction(s1, b3, new BigDecimal("12.01")),
            new Transaction(s2, b1, new BigDecimal("2.03")),
            new Transaction(s2, b4, new BigDecimal("7.12")),
            new Transaction(s3, b3, new BigDecimal("7.03")),
            new Transaction(s2, b2, new BigDecimal("4.04")),
            new Transaction(s2, b3, new BigDecimal("15.06")),
            new Transaction(s1, b2, new BigDecimal("7.84")),
            new Transaction(s1, b4, new BigDecimal("4.15"))
            );
    
    public static Collection<Transaction> getAllTransactions() {
        return allTransactions;
    }
    
}
