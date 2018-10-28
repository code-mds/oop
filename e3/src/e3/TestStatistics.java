package e3;

import java.math.BigDecimal;

public class TestStatistics {
    public static void main(String args[]) {
        Statistics stats = Transactions.getAllTransactions().stream()
                .parallel()
                .collect(Statistics::new,
                        (s, t) -> {
//                            System.out.println("t: " + t + "\n s: "+s);

                            s.setTotalRevenues(s.getTotalRevenues().add(t.getAmount()));
                            s.setTotalTransactions(s.getTotalTransactions() + 1);
                        },
                        (s1, s2) -> {
//                            System.out.println("s1: "+s1 + "\n s2: "+s2);

                            s1.setTotalRevenues(s1.getTotalRevenues().add(s2.getTotalRevenues()));
                            s1.setTotalTransactions(s1.getTotalTransactions() + s2.getTotalTransactions());
                        } );


        System.out.println(stats);


        ImmutableStatistics immutableStatistics = Transactions.getAllTransactions().stream()
                .map(t -> new ImmutableStatistics(t.getAmount(), 1))
                .reduce(ImmutableStatistics.Empty, ImmutableStatistics::add);

        System.out.println(immutableStatistics);

    }

}
