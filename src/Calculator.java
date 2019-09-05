package sec;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class Calculator {

    private LocalDate today = LocalDate.now();

    public void calculate(Customer customer, BigDecimal homePrice, BigDecimal rate, int year) {
        int ageLimit = getYear(customer.getBirthday());
        rate = rate.divide(BigDecimal.valueOf(100));
        int month = year * 12;
        if (ageLimit >= year
                && year >= Condition.MIN_YEAR
                && year <= Condition.MAX_YEAR
                && (today.getYear() - customer.getBirthday().getYear() ) >= Condition.MIN_AGE
                && homePrice.compareTo(Condition.MIN_PRICE) >= 0
                && rate.compareTo(Condition.MIN_INTEREST_RATE) >= 0
                && rate.compareTo(Condition.MAX_INTEREST_RATE) <= 0) {
            BigDecimal initialPayment = initialPayment(homePrice);
            BigDecimal creditAmount = creditAmount(homePrice, initialPayment);
            BigDecimal monthlyPayment = monthlyPayment(creditAmount, rate, month);

            Database database = new Database();
            long customerId = database.createCustomer(customer);
            long creditId = database.createCredit(new Credit(
                            homePrice,
                            initialPayment,
                            creditAmount,
                            interestAmount(monthlyPayment, month, creditAmount),
                            LocalDate.now().plusMonths(1),
                            LocalDate.now().plusYears(year),
                            LocalDate.now()),
                    customerId);
            database.createMonthlyPayment(paymentTable(creditAmount, monthlyPayment, rate, month), creditId);
        } else {
            System.out.println("invalid input!");
        }
    }

    private BigDecimal monthlyRate(BigDecimal rate) {
        return rate.divide(BigDecimal.valueOf(12), 6, RoundingMode.HALF_EVEN);
    }

    public BigDecimal initialPayment(BigDecimal homePrice) {
        BigDecimal extraInitialPayment = BigDecimal.ZERO;
        if (homePrice.compareTo(Condition.MAX_PRICE) > 0) {
            extraInitialPayment = homePrice.subtract(Condition.MAX_PRICE);
        }
        return homePrice.multiply(Condition.MAX_INITIAL_PAYMENT_RATE).add(extraInitialPayment);
    }

    public BigDecimal monthlyPayment(BigDecimal creditAmount, BigDecimal rate, int term) {
        BigDecimal df = discountFactor(monthlyRate(rate), term);
        return creditAmount.divide(df, 6, RoundingMode.HALF_EVEN);
    }

    public BigDecimal creditAmount(BigDecimal homePrice, BigDecimal initialPayment) {
        return homePrice.subtract(initialPayment);
    }

    public BigDecimal interestAmount(BigDecimal monthlyPayment, int term, BigDecimal creditAmount) {
        return monthlyPayment.multiply(BigDecimal.valueOf(term)).subtract(creditAmount);
    }

    public int getYear(LocalDate birthday) {
        LocalDate date = birthday.plusYears(Condition.MAX_AGE);
        Period period = Period.between(today, date);
        int year = period.getYears();
        if (year > Condition.MAX_YEAR) {
            year = Condition.MAX_YEAR;
        }
        return year;
    }

    public List<MonthlyPayment> paymentTable(BigDecimal creditAmount, BigDecimal monthlyPayment, BigDecimal rate, int term) {
        List<MonthlyPayment> paymentTable = new ArrayList<>();
        BigDecimal monthlyInterestAmount;
        BigDecimal monthlyCreditAmount;
        int month = 0;
        while (++month <= term) {
            monthlyInterestAmount = creditAmount.multiply(monthlyRate(rate)).setScale(6, RoundingMode.HALF_EVEN);
            monthlyCreditAmount = monthlyPayment.subtract(monthlyInterestAmount);
            paymentTable.add(new MonthlyPayment(
                    LocalDate.now().plusMonths(month)
                    , monthlyCreditAmount
                    , monthlyInterestAmount
                    , monthlyPayment));
            creditAmount = creditAmount.subtract(monthlyCreditAmount);
        }
        return paymentTable;
    }

    private BigDecimal discountFactor(BigDecimal monthlyRate, int term) {
        return monthlyRate
                .add(BigDecimal.ONE)
                .pow(term)
                .subtract(BigDecimal.ONE)
                .divide(monthlyRate
                        .multiply(BigDecimal.ONE
                                .add(monthlyRate)
                                .pow(term)), 6, RoundingMode.HALF_EVEN);
    }

//    public void display(List<MonthlyPayment> paymentList) {
//        int i = 0;
//        DecimalFormat decimalFormat = new DecimalFormat("0.00");
//        System.out.println("No.\tDate\t\tMainAmount\tInterestAmount\tTotalAmount");
//        for (MonthlyPayment payment : paymentList) {
//            System.out.println(++i + "\t"
//                    + payment.getPaymentDate() + "\t"
//                    + decimalFormat.format(payment.getBaseAmount()) + "\t\t"
//                    + decimalFormat.format(payment.getInterestAmount()) + "\t\t\t"
//                    + decimalFormat.format(payment.getTotalAmount()));
//        }
//    }
}
