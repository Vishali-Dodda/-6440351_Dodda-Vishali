import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransactionHandling {

    private static final String URL = "jdbc:mysql://localhost:3306/your_database";
    private static final String USER = "your_username";
    private static final String PASSWORD = "your_password";

    public static boolean transferMoney(int fromAccountId, int toAccountId, double amount) {
        String debitSQL = "UPDATE accounts SET balance = balance - ? WHERE account_id = ?";
        String creditSQL = "UPDATE accounts SET balance = balance + ? WHERE account_id = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            conn.setAutoCommit(false);  // start transaction

            try (PreparedStatement debitStmt = conn.prepareStatement(debitSQL);
                 PreparedStatement creditStmt = conn.prepareStatement(creditSQL)) {

                // Debit from source account
                debitStmt.setDouble(1, amount);
                debitStmt.setInt(2, fromAccountId);
                int debitRows = debitStmt.executeUpdate();

                // Credit to destination account
                creditStmt.setDouble(1, amount);
                creditStmt.setInt(2, toAccountId);
                int creditRows = creditStmt.executeUpdate();

                if (debitRows == 1 && creditRows == 1) {
                    conn.commit();
                    System.out.println("Transfer successful.");
                    return true;
                } else {
                    conn.rollback();
                    System.out.println("Transfer failed. Transaction rolled back.");
                    return false;
                }

            } catch (SQLException e) {
                conn.rollback();
                System.out.println("Error during transfer. Transaction rolled back.");
                e.printStackTrace();
                return false;
            } finally {
                conn.setAutoCommit(true); // restore default
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        // Example transfer: account 101 transfers 500.00 to account 102
        transferMoney(101, 102, 500.00);
    }
}
