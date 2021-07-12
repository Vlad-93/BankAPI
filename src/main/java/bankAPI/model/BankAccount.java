package bankAPI.model;

import java.util.Objects;

public class BankAccount {
    private final long ID;
    private long accountNumber;
    private Client client;

    public BankAccount(long ID, long accountNumber, Client client) {
        this.ID = ID;
        this.accountNumber = accountNumber;
        this.client = client;
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "ID=" + ID +
                ", accountNumber=" + accountNumber +
                ", client=" + client +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BankAccount)) return false;
        BankAccount that = (BankAccount) o;
        return ID == that.ID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID);
    }

    public long getID() {
        return ID;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
