package e3;

import java.math.BigDecimal;

public class Transaction {
    
    private final Person seller;
    private final Person buyer;
    private final BigDecimal amount;
    
    public Transaction(Person seller, Person buyer, BigDecimal amount) {
        this.seller = seller;
        this.buyer = buyer;
        this.amount = amount;
    }
    
    public Person getSeller() {
        return seller;
    }
    
    public Person getBuyer() {
        return buyer;
    }
    
    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "e3.Transaction{" +
                "seller=" + seller +
                ", buyer=" + buyer +
                ", amount=" + amount +
                '}';
    }
}
