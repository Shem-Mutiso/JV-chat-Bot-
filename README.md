# JV-chat-Bot-
# Kapush Chatbot

A console-based chatbot that helps employees check their loan eligibility and current loan balance, built for **Kapush**, a company that offers salary-linked loans disbursed via M-Pesa and repaid through payroll deduction.

## What it does

Employees interact with the chatbot by entering their name, then choosing one of two commands:

- **eligibility** — calculates how much the employee is eligible to borrow, based on their salary
- **balance** — looks up their current outstanding loan balance

All data is pulled live from a SQL Server database, not hardcoded, so the chatbot always reflects real, up-to-date records.

## Tech stack

- **Java** — core application logic
- **JDBC** — connects Java to the database
- **Microsoft SQL Server** — stores employee, loan, and repayment records
- **PreparedStatement** — used for all queries to prevent SQL injection

## Database structure

- **Employees** — EmployeeID, Name, Salary
- **Loans** — LoanID, EmployeeID (linked), LoanAmount, Balance, Status
- **Repayments** — RepaymentID, LoanID (linked), AmountPaid, PaymentDate

## Example interaction
Welcome to Kapush Chatbot!
Enter your name:
Mary Wanjiru
Hello Mary Wanjiru! Type 'eligibility' or 'balance'
eligibility
You are eligible for up to: 10500.0


## Why I built this
Kapush's real business relies on employees getting fast, accurate answers about their loans without needing to go through HR manually. This project is a small-scale prototype of that idea a foundation that could later be extended to accept loan applications, track repayments automatically, or even connect to a messaging platform like WhatsApp for real-time employee support.

## What I'd add next

- Let employees apply for a new loan through the chatbot
- Automatically update loan balances as repayments come in
- Connect to M-Pesa for real disbursement and collection
