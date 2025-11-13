package main.model;

/**
 * Represents the borrowing policy applied to all library items.
 * <p>
 * A {@code Policy} defines the rules governing loan operations,
 * including the maximum loan period, the number of renewals allowed,
 * and the daily fine rate for overdue items.
 * </p>
 * <p>
 * This class demonstrates encapsulation and data abstraction by
 * storing policy details in private fields accessible only through
 * getter methods.
 * </p>
 */
public class Policy {

    /** The maximum number of days an item can be borrowed before it is due. */
    private int loanPeriod;

    /** The maximum number of times a user is allowed to renew a loan. */
    private int maxRenewals;

    /** The fine charged per day for overdue items. */
    private double dailyFine;

    /**
     * Constructs a new {@code Policy} with the specified parameters.
     *
     * @param loanPeriod   the allowed loan duration in days
     * @param maxRenewals  the maximum number of allowed renewals
     * @param dailyFine    the fine charged per day for overdue items
     */
    public Policy(int loanPeriod, int maxRenewals, double dailyFine) {
        this.loanPeriod = loanPeriod;
        this.maxRenewals = maxRenewals;
        this.dailyFine = dailyFine;
    }

    /**
     * Returns the number of days permitted for a standard loan.
     *
     * @return the loan period in days
     */
    public int getLoanPeriod() {
        return loanPeriod;
    }

    /**
     * Returns the maximum number of times a loan may be renewed.
     *
     * @return the maximum renewal count
     */
    public int getMaxRenewals() {
        return maxRenewals;
    }

    /**
     * Returns the fine amount charged per overdue day.
     *
     * @return the fine amount in monetary units (e.g., £)
     */
    public double getDailyFine() {
        return dailyFine;
    }
}
