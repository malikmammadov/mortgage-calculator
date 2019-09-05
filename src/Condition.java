package sec;

import java.math.BigDecimal;

public final class Condition {
    public static final int MIN_YEAR = 5;
    public static final int MAX_YEAR = 25;
    public static final int MIN_AGE = 18;
    public static final int MAX_AGE = 65;
    public static final BigDecimal MIN_PRICE = BigDecimal.valueOf(10000);
    public static final BigDecimal MAX_PRICE = BigDecimal.valueOf(150000);
    public static final BigDecimal MIN_INITIAL_PAYMENT_RATE = BigDecimal.valueOf(0.15);
    public static final BigDecimal MAX_INITIAL_PAYMENT_RATE = BigDecimal.valueOf(0.30);
    public static final BigDecimal MIN_INTEREST_RATE = BigDecimal.valueOf(0.04);
    public static final BigDecimal MAX_INTEREST_RATE = BigDecimal.valueOf(0.08);

    // Prevents instantiation
    private Condition() {
    }
}
