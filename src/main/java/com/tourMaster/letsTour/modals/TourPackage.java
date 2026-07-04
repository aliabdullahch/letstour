package com.tourMaster.letsTour.modals;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Package")
public class TourPackage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private String id;

    @Column(name="name")
    private String name;

    @Column(name="description")
    private String description;

    @Column(name="no_of_persons")
    private Integer noOfPersons;

    @Column(name="no_of_nights")
    private Integer noOfNights;

    @Column(name="meals_dinners")
    private boolean mealsDinners;

    @Column(name="camping")
    private boolean camping;

    @Column(name="price")
    private Integer price;

    @Column(name="type")
    private Integer type;

    @Column(name="stay")
    private String stay;

    @Column (name = "pckg_img")
    private String pckgImg;

    public String getStay() {
        return stay;
    }

    public void setStay(String stay) {
        this.stay = stay;
    }

    public String getPckgImg() {
        return pckgImg;
    }

    public void setPckgImg(String pckgImg) {
        this.pckgImg = pckgImg;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public TourDestination getTourDestination() {
        return tourDestination;
    }

    public void setTourDestination(TourDestination tourDestination) {
        this.tourDestination = tourDestination;
    }

    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name="agency_id")
    @JsonBackReference("agency-packages")
    private TravelAgency travelAgency;

    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name="destination_id")
    @JsonBackReference("offered-packages")
    private TourDestination tourDestination;

    @OneToMany(mappedBy = "tourPackage",cascade = CascadeType.ALL)
    @JsonBackReference("package-offer")
    private List<PackageOffer> packageOffers;



    @ManyToMany
    @JsonManagedReference("package_activities")
    @JoinTable(
            name="package_activity",
            joinColumns = @JoinColumn(name="pckg_id"),
            inverseJoinColumns = @JoinColumn(name="act_id")
    )
    private List<Activity> activities;

    public void addPackageActivity(Activity activity)
    {
        if(activities==null)
        {
            activities= new ArrayList<>();
        }
        activities.add(activity);
    }

    public void addPackageOffers(PackageOffer packageOffer)
    {
        if(this.packageOffers==null)
        {
            this.packageOffers=new ArrayList<>();
        }
        this.packageOffers.add(packageOffer);
        packageOffer.setTourPackage(this);
    }

    @OneToMany(mappedBy = "bookedPackage",cascade = CascadeType.ALL)
    private List<Booking> bookings;

    public void  addBooking(Booking booking)
    {
        if(this.bookings==null)
        {
            this.bookings=new ArrayList<>();
        }
        bookings.add(booking);
    }




    public TourPackage( String name, String description, Integer noOfPersons, Integer noOfNights, boolean mealsDinners, boolean camping, Integer price,Integer type) {
        this.name = name;
        this.description = description;
        this.noOfPersons = noOfPersons;
        this.noOfNights = noOfNights;
        this.mealsDinners = mealsDinners;
        this.camping = camping;
        this.price = price;
        this.type=type;

    }
    public TourPackage()
    {

    }

    @Override
    public String toString() {
        return "Tour Package{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", noOfPersons=" + noOfPersons +
                ", noOfNights=" + noOfNights +
                ", mealsDinners=" + mealsDinners +
                ", camping=" + camping +
                ", price=" + price +
                ", travelAgency=" + travelAgency +
                ", type="+type+
                '}';
    }
 public void removeAgency()
 {
     this.travelAgency=null;
 }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Integer getNoOfPersons() {
        return noOfPersons;
    }

    public void setNoOfPersons(Integer noOfPersons) {
        this.noOfPersons = noOfPersons;
    }

    public Integer getNoOfNights() {
        return noOfNights;
    }

    public void setNoOfNights(Integer noOfNights) {
        this.noOfNights = noOfNights;
    }

    public boolean isMealsDinners() {
        return mealsDinners;
    }

    public void setMealsDinners(boolean mealsDinners) {
        this.mealsDinners = mealsDinners;
    }

    public boolean isCamping() {
        return camping;
    }

    public void setCamping(boolean camping) {
        this.camping = camping;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public TravelAgency getTravelAgency() {
        return travelAgency;
    }

    public void setTravelAgency(TravelAgency travelAgency) {
        this.travelAgency = travelAgency;
    }
}
