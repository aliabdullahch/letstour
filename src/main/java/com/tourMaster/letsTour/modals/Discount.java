package com.tourMaster.letsTour.modals;

public class Discount {
    private String type;
    private String value;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Discount() {
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Discount{" +
                "type='" + type + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
