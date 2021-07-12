package bankAPI.model;

import java.util.Objects;

public class Card {
    private final long ID;
    private final long number;
    private long bankAccountID;
    private double balance = 0;

    public void putMoney(int money) {
        this.balance += money;
    }

    public Card(long ID, long number, long bankAccountID, double balance) {
        this.ID = ID;
        this.number = number;
        this.bankAccountID = bankAccountID;
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Card{" +
                "ID=" + ID +
                ", number=" + number +
                ", bankAccountID=" + bankAccountID +
                ", balance=" + balance +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Card)) return false;
        Card card = (Card) o;
        return ID == card.ID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID);
    }

    public long getID() {
        return ID;
    }

    public long getNumber() {
        return number;
    }

    public long getBankAccount() {
        return bankAccountID;
    }

    public void setBankAccount(long bankAccountID) {
        this.bankAccountID = bankAccountID;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
