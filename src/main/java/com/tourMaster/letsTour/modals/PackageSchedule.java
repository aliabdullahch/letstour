package com.tourMaster.letsTour.modals;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="package_schedule")
public class PackageSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private String id;

    @Column(name="start_date")
    private String startDate;

    @OneToMany(mappedBy = "packageSchedule",cascade = {CascadeType.ALL})
    @JsonBackReference("schedule-offer")
    private List<PackageOffer> offersContainingSchedule;

    public void addOffersContainingSchedule(PackageOffer packageOffer)
    {
        if (this.offersContainingSchedule==null)
        {
            this.offersContainingSchedule= new ArrayList<>();
        }
        this.offersContainingSchedule.add(packageOffer);
        packageOffer.setPackageSchedule(this);
    }


    public PackageSchedule(String id, String startDate, String endDate) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Column(name="end_date")
    private String endDate;

    public PackageSchedule() {
    }
}
