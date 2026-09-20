import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class KapushChatbot {

    public static void main(String[] args) {
        String connectionUrl = "jdbc:sqlserver://localhost\\SQLEXPRESS;databaseName=Kapush;encrypt=true;trustServerCertificate=true;";
        String username = "kapushuser";
        String password = "Kapush123!";

        Scanner scanner = new Scanner(System.in);

        try {
            Connection conn = DriverManager.getConnection(connectionUrl, username, password);
            System.out.println("Connected to Kapush database!");

            System.out.println("Welcome to Kapush Chatbot!");
            System.out.println("Enter your name:");
            String userName = scanner.nextLine();

            PreparedStatement empStmt = conn.prepareStatement(
                "SELECT EmployeeID, Salary FROM Employees WHERE Name = ?"
            );
            empStmt.setString(1, userName);
            ResultSet empResult = empStmt.executeQuery();

            if (empResult.next()) {
                int employeeId = empResult.getInt("EmployeeID");
                double salary = empResult.getDouble("Salary");

                System.out.println("Hello " + userName + "! Type: 'eligibility' or 'balance'");
                String userInput = scanner.nextLine();

                if (userInput.equalsIgnoreCase("eligibility")) {
                    double eligibleAmount = salary * 0.3;
                    System.out.println("You are eligible for up to: " + eligibleAmount);
                } else if (userInput.equalsIgnoreCase("balance")) {
                    PreparedStatement loanStmt = conn.prepareStatement(
                        "SELECT Balance FROM Loans WHERE EmployeeID = ?"
                    );
                    loanStmt.setInt(1, employeeId);
                    ResultSet loanResult = loanStmt.executeQuery();

                    if (loanResult.next()) {
                        System.out.println("Your current loan balance is: " + loanResult.getDouble("Balance"));
                    } else {
                        System.out.println("You have no active loans.");
                    }
                } else {
                    System.out.println("Sorry, I didn't understand that.");
                }
            } else {
                System.out.println("Sorry, we don't recognize you.");
            }

            conn.close();

        } catch (Exception e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}