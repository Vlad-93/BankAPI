package bank.model;

public class BankAccount {
    private final long accountNumber;
    private Client client;

    public BankAccount(long accountNumber, Client client) {
        this.accountNumber = accountNumber;
        this.client = client;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public Client getClient() {
        return client;
    }
}
