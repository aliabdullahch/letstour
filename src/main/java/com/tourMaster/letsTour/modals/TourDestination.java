package com.tourMaster.letsTour.modals;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tour_destinations")
public class TourDestination {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private String id;

    @Column(name="name")
    private String name;

    @Column(name="description")
    private String description;

    @Column(name="imagePath")
    private String imagePath;

    @Column(name = "area_type")
    private String areaType;

    @Column(name="province")
    private String province;

    @Column(name = "city")
    private String city;

    @OneToOne(mappedBy = "tourDestination",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonBackReference
    DestinationDescription destinationDescription;



    public void setDestinationDescription(DestinationDescription destinationDescription) {
        this.destinationDescription=destinationDescription;
    }

    @OneToMany(mappedBy = "tourDestination",cascade = {CascadeType.ALL})
    @JsonManagedReference("destination-images")
    List<DestinationImage> destinationImages;

    public List<DestinationImage> getDestinationImages() {
        return destinationImages;
    }

    public void setDestinationImages(List<DestinationImage> destinationImages)
    {
        this.destinationImages=destinationImages;
    }
    public void addDestinationImage(DestinationImage destImage)
    {
        if (destinationImages==null)
        {
            destinationImages= new ArrayList<>();
        }
        destinationImages.add(destImage);

    }
    @OneToMany(mappedBy = "tourDestination", cascade = {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH,CascadeType.DETACH})
    @JsonManagedReference("offered-packages")
    private List<TourPackage> tourPackages;

    public TourDestination(String city, String province, String areaType, String imagePath, String description, String name) {
        this.city = city;
        this.province = province;
        this.areaType = areaType;
        this.imagePath = imagePath;
        this.description = description;
        this.name = name;
    }

    public List<TourPackage> getTourPackages() {
        return tourPackages;
    }

    public void setTourPackages(List<TourPackage> tourPackages) {
        this.tourPackages = tourPackages;
    }

    public TourDestination(String id, String name, String description, String imagePath, String areaType, String province, String city) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imagePath = imagePath;
        this.areaType = areaType;
        this.province = province;
        this.city = city;
    }

    public String getAreaType() {
        return areaType;
    }

    public void setAreaType(String areaType) {
        this.areaType = areaType;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    @ManyToMany
    @JoinTable(
            name="destination_agency",
            joinColumns = @JoinColumn(name="destination_id"),
            inverseJoinColumns = @JoinColumn(name="agency_id")
    )
    List<TravelAgency> travelAgencies;

    @OneToMany(mappedBy = "reviewedOn", cascade = {CascadeType.ALL})
    @JsonManagedReference("reviews_got")
    private List<Review> destinationReviews;

    public void addReview (Review rev)
    {
        if(this.destinationReviews==null)
        {
            this.destinationReviews = new ArrayList<>();
        }
        this.destinationReviews.add(rev);
        rev.setReviewedOn(this);
    }

    public List<Review> getDestinationReviews() {
        return destinationReviews;
    }

    public void setDestinationReviews(List<Review> destinationReviews) {
        this.destinationReviews = destinationReviews;
    }

    public void addSingleTourPackage(TourPackage tp)
    {
        if(this.tourPackages==null)
        {
            this.tourPackages=new ArrayList<>();
        }
        this.tourPackages.add(tp);
        tp.setTourDestination(this);


    }


    public TourDestination(String id, String name, String description, String imagePath) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imagePath = imagePath;
    }

    public List<TravelAgency> getTravelAgencies() {
        return travelAgencies;
    }

    public void setTravelAgencies(List<TravelAgency> tavelAgencies) {
        if(this.travelAgencies==null)
        {
            this.travelAgencies=new ArrayList<>();
        }
        this.travelAgencies = tavelAgencies;
    }



    public TourDestination(String name, String description, String imagePath) {
        this.name = name;
        this.description = description;
        this.imagePath = imagePath;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TourDestination() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImagePath() {
        return imagePath;
    }

    @Override
    public String toString() {
        return "TourDestination{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", imagePath='" + imagePath + '\'' +
                '}';
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
