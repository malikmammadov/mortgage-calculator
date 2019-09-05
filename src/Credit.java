package sec;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.StringJoiner;

public class Credit {
    private BigDecimal homePrice;
    private BigDecimal initialPayment;
    private BigDecimal creditAmount;
    private BigDecimal interestAmount;
    private LocalDate firstPaymentDate;
    private LocalDate lastPaymentDate;
    private LocalDate actionDate;

    public Credit(BigDecimal homePrice, BigDecimal initialPayment, BigDecimal creditAmount, BigDecimal interestAmount, LocalDate firstPaymentDate, LocalDate lastPaymentDate, LocalDate actionDate) {
        this.homePrice = homePrice;
        this.initialPayment = initialPayment;
        this.creditAmount = creditAmount;
        this.interestAmount = interestAmount;
        this.firstPaymentDate = firstPaymentDate;
        this.lastPaymentDate = lastPaymentDate;
        this.actionDate = actionDate;
    }

    public Credit() {
        this(BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, LocalDate.now(), LocalDate.now(), LocalDate.now());
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Credit.class.getSimpleName() + "[", "]")
                .add("homePrice=" + homePrice)
                .add("initialPayment=" + initialPayment)
                .add("creditAmount=" + creditAmount)
                .add("interestAmount=" + interestAmount)
                .add("firstPaymentDate=" + firstPaymentDate)
                .add("lastPaymentDate=" + lastPaymentDate)
                .add("actionDate=" + actionDate)
                .toString();
    }

    public BigDecimal getHomePrice() {
        return homePrice;
    }

    public void setHomePrice(BigDecimal homePrice) {
        this.homePrice = homePrice;
    }

    public BigDecimal getInitialPayment() {
        return initialPayment;
    }

    public void setInitialPayment(BigDecimal initialPayment) {
        this.initialPayment = initialPayment;
    }

    public BigDecimal getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(BigDecimal creditAmount) {
        this.creditAmount = creditAmount;
    }

    public BigDecimal getInterestAmount() {
        return interestAmount;
    }

    public void setInterestAmount(BigDecimal interestAmount) {
        this.interestAmount = interestAmount;
    }

    public LocalDate getFirstPaymentDate() {
        return firstPaymentDate;
    }

    public void setFirstPaymentDate(LocalDate firstPaymentDate) {
        this.firstPaymentDate = firstPaymentDate;
    }

    public LocalDate getLastPaymentDate() {
        return lastPaymentDate;
    }

    public void setLastPaymentDate(LocalDate lastPaymentDate) {
        this.lastPaymentDate = lastPaymentDate;
    }

    public LocalDate getActionDate() {
        return actionDate;
    }

    public void setActionDate(LocalDate actionDate) {
        this.actionDate = actionDate;
    }
}
