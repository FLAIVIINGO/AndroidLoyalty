package com.example.loyaltyfirst;

public class TransactionModel3 {
    String prize_desc;
    String points_needed;
    String redeem_date;
    String ex_center;

    public TransactionModel3(String prize_desc, String points_needed, String redeem_date, String ex_center) {
        this.prize_desc = prize_desc;
        this.points_needed = points_needed;
        this.redeem_date = redeem_date;
        this.ex_center = ex_center;
    }

    public String getPrize_desc() {
        return prize_desc;
    }

    public String getPoints_needed() {
        return points_needed;
    }

    public String getRedeem_date() {
        return redeem_date;
    }

    public String getEx_center() {
        return ex_center;
    }
}
