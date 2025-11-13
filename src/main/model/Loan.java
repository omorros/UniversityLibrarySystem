package main.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Represents a loan transaction between a {@link User} and a {@link Product}.
 * <p>
 * The {@code Loan} class encapsulates information about borrowing activity,
 * including borrower, item, start date, due date, and renewal count. It
 * demonstrates aggregation (linking users and products) and encapsulation by
 * controlling all loan-related logic within one class.
 * </p>
 * <p>
 * This class also contains the nested inner class {@link Reminder}, which
 * provides real-time due date notifications. The inclusion of the inner
 * class demonstrates advanced OOP principles such as inner class usage and
 * object encapsulation.
 * </p>
 */
public class Loan {

    /** Unique identifier for this loan transaction. */
    private int loanId;

    /** The user who borrowed the product. */
    private User borrower;

    /** The product being borrowed (e.g., book, CD, DVD, audiobook). */
    private Product item;

    /** The date when the loan was created. */
    private LocalDate startDate;

    /** The date when the item is due to be returned. */
    private LocalDate dueDate;

    /** The actual date the item was returned, if applicable. */
    private LocalDate returnDate;

    /** The number of times this loan has been renewed. */
    private int renewCount;

    /**
     * Constructs a new {@code Loan} object using a policy to determine
     * due dates and renewal limits.
     *
     * @param loanId   unique identifier for this loan
     * @param borrower the {@link User} who borrows the product
     * @param item     the {@link Product} being borrowed
     * @param policy   the {@link Policy} defining loan rules (duration, renewals, fines)
     */
    public Loan(int loanId, User borrower, Product item, Policy policy) {
        this.loanId = loanId;
        this.borrower = borrower;
        this.item = item;
        this.startDate = LocalDate.now();
        this.dueDate = startDate.plusDays(policy.getLoanPeriod());
        this.renewCount = 0;
    }

    // -------------------------------------------
    // GETTERS AND BASIC OPERATIONS
    // -------------------------------------------

    /**
     * Returns the product associated with this loan.
     *
     * @return the borrowed {@link Product}
     */
    public Product getItem() { return item; }

    /**
     * Returns the user who borrowed the product.
     *
     * @return the {@link User} borrower
     */
    public User getBorrower() { return borrower; }

    /**
     * Sets the return date for the loan when the product is returned.
     *
     * @param date the {@link LocalDate} when the item was returned
     */
    public void setReturnDate(LocalDate date) { this.returnDate = date; }

    /**
     * Checks if the loan is overdue relative to the provided date.
     *
     * @param date the {@link LocalDate} to compare against the due date
     * @return {@code true} if the loan is overdue; otherwise {@code false}
     */
    public boolean isOverdue(LocalDate date) { return dueDate.isBefore(date); }

    // -------------------------------------------
    // RENEWAL LOGIC
    // -------------------------------------------

    /**
     * Attempts to renew the loan according to the defined policy.
     * <p>
     * Each renewal extends the due date by the loan period defined in
     * {@link Policy}, up to the maximum number of renewals allowed.
     * </p>
     *
     * @param policy the {@link Policy} specifying renewal limits
     * @return {@code true} if the loan was successfully renewed;
     *         {@code false} if the maximum renewals have been reached
     */
    public boolean renew(Policy policy) {
        // Only allow renewal if current count is below the allowed maximum.
        if (renewCount < policy.getMaxRenewals()) {
            dueDate = dueDate.plusDays(policy.getLoanPeriod());
            renewCount++;
            return true;
        }
        return false;
    }

    // -------------------------------------------
    // LOAN INFORMATION OUTPUT
    // -------------------------------------------

    /**
     * Returns formatted information about this loan,
     * including borrower and product details.
     *
     * @return a string summarising the loan record
     */
    public String getInfo() {
        String productType = item.getClass().getSimpleName();
        String userType = borrower.getClass().getSimpleName();

        // Combine all key loan information into one readable line.
        return "Loan #" + loanId +
                " | Type: " + productType +
                " | Title: " + item.getTitle() +
                " | Borrower: " + borrower.getName() + " [" + userType + "]" +
                " | Due: " + dueDate +
                " | Renewals: " + renewCount;
    }

    // -------------------------------------------
    // INNER CLASS: REMINDER
    // -------------------------------------------

    /**
     * Inner class that provides due date reminders for this specific loan.
     * <p>
     * Demonstrates encapsulation by operating directly on the outer
     * {@link Loan} class's fields (e.g., {@code dueDate}, {@code item})
     * and showing how nested classes can provide contextual functionality.
     * </p>
     */
    public class Reminder {

        /**
         * Displays a reminder message showing how many days remain
         * until the item is due, or how many days overdue it is.
         * <p>
         * This method uses {@link ChronoUnit#DAYS} to calculate
         * the number of days between the current date and the due date.
         * </p>
         */
        public void showReminder() {
            LocalDate today = LocalDate.now();
            long daysLeft = ChronoUnit.DAYS.between(today, dueDate);

            // Display appropriate message based on the loan status.
            if (daysLeft > 0) {
                System.out.println("Reminder: " + daysLeft +
                        " day(s) left until '" + item.getTitle() + "' is due.");
            } else if (daysLeft == 0) {
                System.out.println("Reminder: '" + item.getTitle() + "' is due today!");
            } else {
                System.out.println("Reminder: '" + item.getTitle() +
                        "' is overdue by " + Math.abs(daysLeft) + " day(s).");
            }
        }
    }
}
