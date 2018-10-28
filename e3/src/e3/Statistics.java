package e3;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Statistics {
    private BigDecimal totalRevenues = BigDecimal.ZERO;
    private int totalTransactions = 0;

    public Statistics() {
    }

    public void setTotalRevenues(BigDecimal totalRevenues) {
        this.totalRevenues = totalRevenues;
    }

    public void setTotalTransactions(int totalTransactions) {
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

    @Override
    public String toString() {
        return "Statistics{" +
                "totalRevenues=" + totalRevenues +
                ", totalTransactions=" + totalTransactions +
                ", average revenues=" + getAverageRevenues() +
                '}';
    }
}
