package bank.model;

public class Card {
    private final long ID;
    private final long number;
    private BankAccount bankAccount;
    private double balance = 0;

    public void putMoney(int money) {
        this.balance += money;
    }

    public Card(long ID, long number, BankAccount bankAccount, double balance) {
        this.ID = ID;
        this.number = number;
        this.bankAccount = bankAccount;
        this.balance = balance;
    }

    public long getID() {
        return ID;
    }

    public long getNumber() {
        return number;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
