package sec;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.StringJoiner;

public class MonthlyPayment {
    private LocalDate paymentDate;
    private BigDecimal baseAmount;
    private BigDecimal interestAmount;
    private BigDecimal totalAmount;

    public MonthlyPayment(LocalDate paymentDate, BigDecimal baseAmount, BigDecimal interestAmount, BigDecimal totalAmount) {
        this.paymentDate = paymentDate;
        this.baseAmount = baseAmount;
        this.interestAmount = interestAmount;
        this.totalAmount = totalAmount;
    }

    public MonthlyPayment() {
        this(LocalDate.now(), BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", MonthlyPayment.class.getSimpleName() + "[", "]")
                .add("paymentDate=" + paymentDate)
                .add("baseAmount=" + baseAmount)
                .add("interestAmount=" + interestAmount)
                .add("totalAmount=" + totalAmount)
                .toString();
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public BigDecimal getBaseAmount() {
        return baseAmount;
    }

    public void setBaseAmount(BigDecimal baseAmount) {
        this.baseAmount = baseAmount;
    }

    public BigDecimal getInterestAmount() {
        return interestAmount;
    }

    public void setInterestAmount(BigDecimal interestAmount) {
        this.interestAmount = interestAmount;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
}
