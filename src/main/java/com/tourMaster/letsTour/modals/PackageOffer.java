package com.tourMaster.letsTour.modals;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

@Entity
@Table(name="package_offers")
public class PackageOffer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private String id;

    @ManyToOne()
    @JoinColumn(name = "package_id")
    @JsonManagedReference("package-offer")
    TourPackage tourPackage;

    @ManyToOne()
    @JoinColumn(name="package_schedule_id")
    @JsonManagedReference("schedule-offer")
    PackageSchedule packageSchedule;

    public PackageOffer() {
    }

    public PackageOffer(String id, TourPackage tourPackage, PackageSchedule packageSchedule) {
        this.id = id;
        this.tourPackage = tourPackage;
        this.packageSchedule = packageSchedule;
    }

    public TourPackage getTourPackage() {
        return tourPackage;
    }

    public void setTourPackage(TourPackage tourPackage) {
        this.tourPackage = tourPackage;
    }

    public PackageSchedule getPackageSchedule() {
        return packageSchedule;
    }

    public void setPackageSchedule(PackageSchedule packageSchedule) {
        this.packageSchedule = packageSchedule;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
