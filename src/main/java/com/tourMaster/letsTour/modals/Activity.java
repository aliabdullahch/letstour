package com.tourMaster.letsTour.modals;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="activity")
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="activ_id")
    private String activId;

    @Column(name="act_name")
    private String activityName;

    @Column(name="act_desc")
    private String activityDesc;

    @Column(name ="act_icon")
    private String activIcon;

    @Column(name = "act_type")
    private String actType;

    @ManyToMany
    @JoinTable(
            name="package_activity",
            joinColumns = @JoinColumn(name="act_id"),
            inverseJoinColumns = @JoinColumn(name="pckg_id")
    )
    @JsonBackReference("package_activities")
    private List<TourPackage> tourPackages;

    public Activity() {
    }

    public Activity(String activityName, String activityDesc, String activIcon, String actType) {
        this.activityName = activityName;
        this.activityDesc = activityDesc;
        this.activIcon = activIcon;
        this.actType = actType;
    }
    public void addTourPackages (TourPackage tp)
    {
        if(tourPackages==null)
        {
            tourPackages= new ArrayList<>();
        }
        tourPackages.add(tp);
    }

    public String getActivityName() {
        return activityName;
    }

    public String getActivityDesc() {
        return activityDesc;
    }

    public void setActivityDesc(String activityDesc) {
        this.activityDesc = activityDesc;
    }

    public String getActivIcon() {
        return activIcon;
    }

    public void setActivIcon(String activIcon) {
        this.activIcon = activIcon;
    }

    public String getActType() {
        return actType;
    }

    public void setActType(String actType) {
        this.actType = actType;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }
}
