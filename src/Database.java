package sec;

import java.math.RoundingMode;
import java.sql.*;
import java.util.List;

public class Database {
    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private String sql = "";

    private void createConnection() {
        String username = "hr";
        String password = "hr";
        String url = "jdbc:oracle:thin:@//172.16.161.128:1521/orcl";
        String driver = "oracle.jdbc.driver.OracleDriver";
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, username, password);
            connection.setAutoCommit(false);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public long createCustomer(Customer customer) {
        long id = 0;
        try {
            createConnection();
            sql = "insert into customer(id, name, surname, birth_date) values(customer_seq.nextval, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql, new String[]{"id"});
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2, customer.getSurname());
            preparedStatement.setDate(3, Date.valueOf(customer.getBirthday()));
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                id = resultSet.getLong(1);
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return id;
    }

    public long createCredit(Credit credit, long customerId) {
        long id = 0;
        try {
            sql = "insert into credit(id, customer_id, home_price, initial_payment, " +
                    "credit_amount, interest_amount, first_payment_date, last_payment_date, action_date) " +
                    "values (credit_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?)";
            createConnection();
            preparedStatement = connection.prepareStatement(sql, new String[]{"id"});
            preparedStatement.setLong(1, customerId);
            preparedStatement.setBigDecimal(2, credit.getHomePrice().setScale(2, RoundingMode.HALF_EVEN));
            preparedStatement.setBigDecimal(3, credit.getInitialPayment().setScale(2, RoundingMode.HALF_EVEN));
            preparedStatement.setBigDecimal(4, credit.getCreditAmount().setScale(2, RoundingMode.HALF_EVEN));
            preparedStatement.setBigDecimal(5, credit.getInterestAmount().setScale(2, RoundingMode.HALF_EVEN));
            preparedStatement.setDate(6, Date.valueOf(credit.getFirstPaymentDate()));
            preparedStatement.setDate(7, Date.valueOf(credit.getLastPaymentDate()));
            preparedStatement.setDate(8, Date.valueOf(credit.getActionDate()));
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                id = resultSet.getLong(1);
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return id;
    }

    public long createMonthlyPayment(List<MonthlyPayment> monthlyPayments, long creditId) {
        long id = 0;
        try {
            sql = "insert into monthly_payment (id, credit_id, payment_date, base_amount, interest_amount, total_amount) " +
                    "values(monthly_payment_seq.nextval, ?, ?, ?, ?, ?)";
            createConnection();
            preparedStatement = connection.prepareStatement(sql, new String[]{"id"});
            for (MonthlyPayment payment : monthlyPayments) {
                preparedStatement.setLong(1, creditId);
                preparedStatement.setDate(2, Date.valueOf(payment.getPaymentDate()));
                preparedStatement.setBigDecimal(3, payment.getBaseAmount().setScale(2, RoundingMode.HALF_EVEN));
                preparedStatement.setBigDecimal(4, payment.getInterestAmount().setScale(2, RoundingMode.HALF_EVEN));
                preparedStatement.setBigDecimal(5, payment.getTotalAmount().setScale(2, RoundingMode.HALF_EVEN));
                preparedStatement.executeUpdate();
                resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    id = resultSet.getLong(1);
                }
                connection.commit();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return id;
    }
}
