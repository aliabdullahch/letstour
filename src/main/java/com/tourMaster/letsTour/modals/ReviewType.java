package com.tourMaster.letsTour.modals;


import jakarta.persistence.*;

@Entity
@Table(name="review_type")
public class ReviewType
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private String id;

    @Column(name = "text_value")
    private String  textValue;

    @Column(name = "rat_star")
    private Integer ratStar;

    @Column(name = "rat_value")
    private double ratValue;

    @Column(name = "valc_const")
    private int validationConst;

    public ReviewType(String textValue, Integer ratStar, double ratValue, int validationConst) {
        this.textValue = textValue;
        this.ratStar = ratStar;
        this.ratValue = ratValue;
        this.validationConst = validationConst;
    }

    public ReviewType() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTextValue() {
        return textValue;
    }

    public void setTextValue(String textValue) {
        this.textValue = textValue;
    }

    public Integer getRatStar() {
        return ratStar;
    }

    public void setRatStar(Integer ratStar) {
        this.ratStar = ratStar;
    }

    public double getRatValue() {
        return ratValue;
    }

    public void setRatValue(double ratValue) {
        this.ratValue = ratValue;
    }

    public int getValidationConst() {
        return validationConst;
    }

    public void setValidationConst(int validationConst) {
        this.validationConst = validationConst;
    }

    @Override
    public String toString() {
        return "ReviewType{" +
                "id='" + id + '\'' +
                ", textValue='" + textValue + '\'' +
                ", ratStar=" + ratStar +
                ", ratValue=" + ratValue +
                ", validationConst=" + validationConst +
                '}';
    }
}
