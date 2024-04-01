package ru.netology.molchanov.ApiOperationHistory.domain;

import java.io.Serializable;

public class Operation implements Serializable {
    private int id;
    private double amount;
    private int date;

    public Operation(int id, double amount, int date) {
        this.id = id;
        this.amount = amount;
        this.date = date;

    }
    public int getId() {
        return id;
    }

    public void setOperation_id(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Operation{" +
                "amount=" + amount +
                ", date=" + date +
                ", id=" + id +
                '}';
    }



}
