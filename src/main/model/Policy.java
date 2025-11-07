package main.model;

public class Policy {
    private int loanPeriod;  // days
    private int maxRenewals;
    private double dailyFine;

    public Policy(int loanPeriod, int maxRenewals, double dailyFine) {
        this.loanPeriod = loanPeriod;
        this.maxRenewals = maxRenewals;
        this.dailyFine = dailyFine;
    }

    public int getLoanPeriod() { return loanPeriod; }
    public int getMaxRenewals() { return maxRenewals; }
    public double getDailyFine() { return dailyFine; }
}
