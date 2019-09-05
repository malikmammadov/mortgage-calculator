package sec;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class MainClass {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Customer customer = new Customer();
        BigDecimal homePrice;
        BigDecimal rate;
        int year;

        Calculator calculator = new Calculator();

        System.out.println("Name: ");
        customer.setName(scanner.next());
        System.out.println("Surname: ");
        customer.setSurname(scanner.next());
        System.out.println("Birthday(dd.MM.yyyy e.g. 14.02.1992): ");
        customer.setBirthday(LocalDate.parse(scanner.next(), DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        System.out.println("Home Price: ");
        homePrice = scanner.nextBigDecimal();
        System.out.println("Rate(min 4%, max 8%): ");
        rate = scanner.nextBigDecimal();
        System.out.println("Mortgage term(min 5 year, max 25 year) ");
        year = scanner.nextInt();

        calculator.calculate(customer, homePrice, rate, year);
    }
}
