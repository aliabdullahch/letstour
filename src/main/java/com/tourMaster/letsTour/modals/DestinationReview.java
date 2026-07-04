package com.tourMaster.letsTour.modals;

public class DestinationReview {
    private Double averageRating;
    private int noOfReviews;

    public Double getAverageRating() {
        return averageRating;
    }

    public DestinationReview() {
        averageRating=0.0;
        noOfReviews=0;
    }

    @Override
    public String toString() {
        return "DestinationReview{" +
                "averageRating=" + averageRating +
                ", noOfReviews=" + noOfReviews +
                '}';
    }

    public DestinationReview(Double averageRating, int noOfReviews) {
        this.averageRating = averageRating;
        this.noOfReviews = noOfReviews;
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;

    }

    public int getNoOfReviews() {
        return noOfReviews;
    }

    public void setNoOfReviews(int noOfReviews) {
        this.noOfReviews = noOfReviews;

    }
}
