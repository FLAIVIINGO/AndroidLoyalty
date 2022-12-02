package com.example.loyaltyfirst;

public class TransactionModel4 {
    String family_id;
    String percent_points;
    String total_points;

    public TransactionModel4(String family_id, String percent_points, String total_points) {
        this.family_id = family_id;
        this.percent_points = percent_points;
        this.total_points = total_points;
    }

    public String getFamily_id() {
        return family_id;
    }

    public String getPercent_points() {
        return percent_points;
    }

    public String getTotal_points() {
        return total_points;
    }
}
