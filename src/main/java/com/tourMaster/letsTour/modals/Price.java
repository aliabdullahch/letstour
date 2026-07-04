package com.tourMaster.letsTour.modals;

import java.util.ArrayList;

public class Price {
    private Integer amount;

    public Integer getAmount() {
        return amount;
    }

    public Price() {
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public ArrayList<Discount> getdiscounts() {
        return discounts;
    }

    public void setdiscounts(ArrayList<Discount> discounts) {
        this.discounts = discounts;
    }

    private String currency;
    private ArrayList<Discount> discounts;

    @Override
    public String toString() {
        return "Price{" +
                "amount=" + amount +
                ", currency='" + currency + '\'' +
                ", discounts=" + discounts +
                '}';
    }
}
