package com.tourMaster.letsTour.modals;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name="tour_destination_images")
public class DestinationImage {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private String id;

    @Column(name="image_url")
    private String imageURL;

    public DestinationImage() {
    }

    @Column(name="image_type")
    private String imageType;

    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "destination_id")
    @JsonBackReference("destination-images")
    TourDestination tourDestination;

    public DestinationImage(String imageURL, String imageType) {
        this.imageURL = imageURL;
        this.imageType = imageType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public TourDestination getTourDestination() {
        return tourDestination;
    }

    public void setTourDestination(TourDestination tourDestination) {
        this.tourDestination=tourDestination;
    }
}
