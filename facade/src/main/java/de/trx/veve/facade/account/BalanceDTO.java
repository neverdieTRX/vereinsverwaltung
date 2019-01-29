package de.trx.veve.facade.account;

public class BalanceDTO {

    private double balance;

    BalanceDTO(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return this.balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
