package e3;

import java.math.BigDecimal;
import java.math.RoundingMode;


public class TestStatistics {

    public static void main(String args[]) {
        Statistics stats = Transactions.getAllTransactions().stream()
                .parallel()
                .collect(Statistics::new,
                        (s, t) -> {
                              s.accumulate(t.getAmount(), 1);
//                            s.setTotalRevenues(s.getTotalRevenues().add(t.getAmount()));
//                            s.setTotalTransactions(s.getTotalTransactions() + 1);
                        },
                        (s1, s2) -> {
                            s1.accumulate(s2.getTotalRevenues(), s2.getTotalTransactions());
                           // s1.setTotalRevenues(s1.getTotalRevenues().add(s2.getTotalRevenues()));
                           // s1.setTotalTransactions(s1.getTotalTransactions() + s2.getTotalTransactions());
                        } );

        System.out.println("mutable: " + stats);
        ImmutableStatistics immutableStatistics = Transactions.getAllTransactions().stream()
                .map(t -> new ImmutableStatistics(t.getAmount(), 1))
                .reduce(ImmutableStatistics.Empty, ImmutableStatistics::add);

        System.out.println("immutable: " + immutableStatistics);
    }
}

class Statistics {
    private BigDecimal totalRevenues = BigDecimal.ZERO;
    private int totalTransactions = 0;

    public Statistics() {
    }

    public void accumulate(BigDecimal revenues, int transactions) {
        totalRevenues = totalRevenues.add(revenues);
        totalTransactions += transactions;
    }

    public int getTotalTransactions() {
        return totalTransactions;
    }

    public BigDecimal getTotalRevenues() {
        return totalRevenues;
    }

    public BigDecimal getAverageRevenues() {
        return totalTransactions == 0 ? BigDecimal.ZERO :
                totalRevenues.divide( BigDecimal.valueOf(totalTransactions), RoundingMode.CEILING);
    }

    @Override
    public String toString() {
        return "Statistics{" + "totalRevenues=" + totalRevenues +
                ", totalTransactions=" + totalTransactions +
                ", average revenues=" + getAverageRevenues() + '}';
    }
}
