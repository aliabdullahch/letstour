package com.tourMaster.letsTour.modals;

import jakarta.persistence.*;

@Entity
@Table(name="dest_description")
public class DestinationDescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="dest_desc_id")
    private String id;

    @Column(name="dest_desc_text")
    private String description;

    @OneToOne(cascade ={CascadeType.REFRESH},fetch = FetchType.LAZY)
    @JoinColumn(name="dest_id")
    TourDestination tourDestination;

    public DestinationDescription() {
    }

    public DestinationDescription(String id, String description, TourDestination tourDestination) {
        this.id = id;
        this.description = description;
        this.tourDestination = tourDestination;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TourDestination getTourDestination() {
        return tourDestination;
    }

    public void setTourDestination(TourDestination tourDestination) {
        this.tourDestination = tourDestination;
    }
}
