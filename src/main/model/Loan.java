package main.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/** Represents a loan transaction between a user and a product. */
public class Loan {
    private int loanId;
    private User borrower;
    private Product item;
    private LocalDate startDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private int renewCount;

    public Loan(int loanId, User borrower, Product item, Policy policy) {
        this.loanId = loanId;
        this.borrower = borrower;
        this.item = item;
        this.startDate = LocalDate.now();
        this.dueDate = startDate.plusDays(policy.getLoanPeriod());
        this.renewCount = 0;
    }

    public Product getItem() { return item; }
    public User getBorrower() { return borrower; }
    public void setReturnDate(LocalDate date) { this.returnDate = date; }
    public boolean isOverdue(LocalDate date) { return dueDate.isBefore(date); }

    public boolean renew(Policy policy) {
        if (renewCount < policy.getMaxRenewals()) {
            dueDate = dueDate.plusDays(policy.getLoanPeriod());
            renewCount++;
            return true;
        }
        return false;
    }

    /**
     * Returns formatted information about the loan,
     * including the product type and borrower details.
     */
    public String getInfo() {
        String productType = item.getClass().getSimpleName();
        String userType = borrower.getClass().getSimpleName();
        return "Loan #" + loanId +
                " | Type: " + productType +
                " | Title: " + item.getTitle() +
                " | Borrower: " + borrower.getName() + " [" + userType + "]" +
                " | Due: " + dueDate +
                " | Renewals: " + renewCount;
    }

    /**
     * Inner class that calculates and shows a due date reminder for this loan.
     * Demonstrates nested class encapsulation.
     */
    public class Reminder {
        public void showReminder() {
            LocalDate today = LocalDate.now();
            long daysLeft = ChronoUnit.DAYS.between(today, dueDate);

            if (daysLeft > 0) {
                System.out.println("Reminder: " + daysLeft + " day(s) left until '" + item.getTitle() + "' is due.");
            } else if (daysLeft == 0) {
                System.out.println("Reminder: '" + item.getTitle() + "' is due today!");
            } else {
                System.out.println("Reminder: '" + item.getTitle() + "' is overdue by " + Math.abs(daysLeft) + " day(s).");
            }
        }
    }
}
