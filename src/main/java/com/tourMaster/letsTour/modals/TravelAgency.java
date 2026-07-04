package com.tourMaster.letsTour.modals;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="travel_agency")
public class TravelAgency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private String id;


    @Column(name="name")
    private String name;

    @Column(name="description")
    private String description;

    @Column(name="image_path")
    private String imagePath;

    @Column(name="rating")
    private Integer rating;

    @ManyToMany(mappedBy = "travelAgencies")
    List<TourDestination> tourDestinations;



    @OneToMany(mappedBy = "travelAgency",cascade = {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JsonManagedReference("agency-packages")
    private List<TourPackage> packages;


    public TravelAgency(String id, String name, String description, String imagePath, Integer rating) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imagePath = imagePath;
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "TravelAgency{" +
                "rating=" + rating +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", imagePath='" + imagePath + '\'' +
                '}';
    }

    public TravelAgency()
    {

    }
    public void addOnePackage(TourPackage p)
    {
        if(this.packages==null)
        {
            packages=new ArrayList<>();
        }
        packages.add(p);
        p.setTravelAgency(this);

    }



    public void removePackage(Integer Id)
    {
        if(!(this.packages==null))
        {
            for (TourPackage t: this.packages)
            {
                if ((t.getId()).equals(Id.toString())) {
                    t.removeAgency();
                    this.packages.remove(t);
                }

            }        }


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

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }
}
