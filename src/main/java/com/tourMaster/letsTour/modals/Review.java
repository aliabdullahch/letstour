package com.tourMaster.letsTour.modals;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

@Entity
@Table(name = "destination_review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private String id;

    @Column(name = "review_desc")
    private String reviewDescription;


    @OneToOne()
    @JoinColumn(name = "review_type")
    private ReviewType reviewType;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    @JsonManagedReference ("reviews_by_user")
    private User reviewedBy;

    @ManyToOne()
    @JoinColumn(name= "destination_id")
    @JsonBackReference("reviews_got")
    private TourDestination reviewedOn;

    public Review(String reviewDescription, ReviewType reviewType, User reviewedBy, TourDestination reviewedOn) {
        this.reviewDescription = reviewDescription;
        this.reviewType = reviewType;
        this.reviewedBy = reviewedBy;
        this.reviewedOn = reviewedOn;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id='" + id + '\'' +
                ", reviewDescription='" + reviewDescription + '\'' +
                ", reviewType=" + reviewType +
                ", reviewedBy=" + reviewedBy +
                ", reviewedOn=" + reviewedOn +
                '}';
    }

    public Review() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReviewDescription() {
        return reviewDescription;
    }

    public void setReviewDescription(String reviewDescription) {
        this.reviewDescription = reviewDescription;
    }

    public ReviewType getReviewType() {
        return reviewType;
    }

    public void setReviewType(ReviewType reviewType) {
        this.reviewType = reviewType;
    }

    public User getReviewedBy() {
        return reviewedBy;
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

}
