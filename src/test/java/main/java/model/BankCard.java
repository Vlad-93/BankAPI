package main.java.model;

public class BankCard {
    private int cardId;
    private int cardHolderId;
    private long cardNumber;

    public BankCard(int cardId, long cardNumber, int cardHolderId) {
        this.cardId = cardId;
        this.cardHolderId = cardHolderId;
        this.cardNumber = cardNumber;
    }


    @Override
    public String toString() {
        return "BankCard{" +
                "cardId=" + cardId +
                ", cardHolderId=" + cardHolderId +
                ", cardNumber=" + cardNumber +
                '}';
    }
}
