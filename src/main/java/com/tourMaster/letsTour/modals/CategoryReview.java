package com.tourMaster.letsTour.modals;


import jakarta.persistence.*;

@Entity
@Table(name = "category_review")
public class CategoryReview {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private String id;

    @Column(name = "rat_value")
    private int ratingValue;


    @OneToOne()
    @JoinColumn(name = "amenity_id")
    private Amenity facility;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User reviewedBy;

    @ManyToOne()
    @JoinColumn(name = "destination_id")
    private TourDestination reviewedOn;

    public CategoryReview() {
    }


    public int getRatingValue() {
        return ratingValue;
    }

    public CategoryReview(String id, int ratingValue, Amenity facility, User reviewedBy, TourDestination reviewedOn) {
        this.id = id;
        this.ratingValue = ratingValue;
        this.facility = facility;
        this.reviewedBy = reviewedBy;
        this.reviewedOn = reviewedOn;
    }

    public void setRatingValue(int ratingValue) {
        this.ratingValue = ratingValue;
    }

    public Amenity getFacility() {
        return facility;
    }

    public void setFacility(Amenity facility) {
        this.facility = facility;
    }

    public User getReviewedBy() {
        return reviewedBy;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setReviewedBy(User reviewedBy) {
        this.reviewedBy = reviewedBy;
    }

    public TourDestination getReviewedOn() {
        return reviewedOn;
    }

    public void setReviewedOn(TourDestination reviewedOn) {
        this.reviewedOn = reviewedOn;
    }

    @Override
    public String toString() {
        return "CategoryReview{" +
                "id='" + id + '\'' +
                ", ratingValue=" + ratingValue +
                ", facility=" + facility +
                ", reviewedBy=" + reviewedBy +
                ", reviewedOn=" + reviewedOn +
                '}';
    }
}
