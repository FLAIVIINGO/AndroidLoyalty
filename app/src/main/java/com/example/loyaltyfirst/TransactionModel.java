package com.example.loyaltyfirst;

public class TransactionModel {
    String trans_id;
    String date;
    String points;
    String total;

    public TransactionModel(String trans_id, String date, String points, String total) {
        this.trans_id = trans_id;
        this.date = date;
        this.points = points;
        this.total = total;
    }

    public String getTrans_id() {
        return trans_id;
    }

    public String getDate() {
        return date;
    }

    public String getPoints() {
        return points;
    }

    public String getTotal(){return total;}
}
