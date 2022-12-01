package com.example.loyaltyfirst;

public class TransactionModel2 {
    String p_name;
    String p_quantity;
    String p_points;

    public TransactionModel2(String p_name, String p_quantity, String p_points) {
        this.p_name = p_name;
        this.p_quantity = p_quantity;
        this.p_points = p_points;
    }

    public String getP_name() {
        return p_name;
    }

    public String getP_quantity() {
        return p_quantity;
    }

    public String getP_points() {
        return p_points;
    }
}
