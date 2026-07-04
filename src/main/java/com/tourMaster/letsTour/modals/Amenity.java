package com.tourMaster.letsTour.modals;


import jakarta.persistence.*;

@Entity
@Table(name="amenity")
public class Amenity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private String id;

    @Column(name="amenity_name")
    private String amenityName;

    @Column(name = "icon_name")
    private String iconName;

    public String getAmenityName() {
        return amenityName;
    }

    public Amenity() {
    }

    public Amenity(String amenityName, String iconName, String id) {
        this.amenityName = amenityName;
        this.iconName = iconName;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Amenity{" +
                "id='" + id + '\'' +
                ", amenityName='" + amenityName + '\'' +
                ", iconName='" + iconName + '\'' +
                '}';
    }

    public void setAmenityName(String amenityName) {
        this.amenityName = amenityName;
    }

    public String getIconName() {
        return iconName;
    }

    public void setIconName(String iconName) {
        this.iconName = iconName;
    }
}
