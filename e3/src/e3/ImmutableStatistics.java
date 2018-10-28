package e3;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ImmutableStatistics {
    public static final ImmutableStatistics Empty = new ImmutableStatistics(BigDecimal.ZERO, 0);
    private final BigDecimal totalRevenues;
    private final int totalTransactions;

    public ImmutableStatistics(BigDecimal totalRevenues, int totalTransactions) {
        this.totalRevenues = totalRevenues;
        this.totalTransactions = totalTransactions;
    }

    public int getTotalTransactions() {
        return totalTransactions;
    }

    public BigDecimal getTotalRevenues() {
        return totalRevenues;
    }

    public BigDecimal getAverageRevenues() {
        return totalTransactions == 0 ? BigDecimal.ZERO : totalRevenues.divide( BigDecimal.valueOf(totalTransactions), RoundingMode.CEILING);
    }

    public ImmutableStatistics add(ImmutableStatistics other) {
        return new ImmutableStatistics(this.totalRevenues.add(other.getTotalRevenues()),
                        this.totalTransactions + other.totalTransactions);
    }

    @Override
    public String toString() {
        return "ImmutableStatistics{" +
                "totalRevenues=" + totalRevenues +
                ", totalTransactions=" + totalTransactions +
                ", average revenues=" + getAverageRevenues() +
                '}';
    }
}
