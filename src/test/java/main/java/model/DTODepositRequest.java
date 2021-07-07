package main.java.model;

import com.fasterxml.jackson.annotation.JsonValue;

public class DTODepositRequest {
    @JsonValue
    private double deposit;


    public double getDeposit() {
        return deposit;
    }

    public void setDeposit(double deposit) {
        this.deposit = deposit;
    }
}
