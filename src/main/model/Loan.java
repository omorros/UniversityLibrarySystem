package main.model;

import java.time.LocalDate;

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

    public Product getItem() {
        return item;
    }

    public User getBorrower() {
        return borrower;
    }

    public void setReturnDate(LocalDate date) {
        this.returnDate = date;
    }

    public boolean isOverdue(LocalDate date) {
        return dueDate.isBefore(date);
    }

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
}
