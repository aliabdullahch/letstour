package com.tourMaster.letsTour.modals;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="guest")
public class Guest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="guest_id")
    private Integer id;


    @NotNull(message = "First Name is mandatory")
    @Size(min = 1, message = "First Name is mandatory")
    @Column(name="first_name")
    private String firstName;

    @NotNull(message = "Last Name is mandatory")
@Size(min = 1,message = "Last Name is mandatory")
    @Column(name = "last_name")
    private String lastName;

    @NotNull(message = "Email is mandatory")
    @Email(message = "Enter a valid email")
    @Size(min = 1, message = "Email is mandatory")
    @Column(name = "email")
    private String email;


    @Column(name = "country")
    private String country;

    @NotNull(message = "Phone No is required")
    @Size(max = 7, message ="Enter 7 digit phone number" )
    @Column(name = "phone_no")
    private String phoneNo;

    @Column(name = "paperless_confirm")
    private Boolean paperlessConfirm;

    @Column(name = "travel_for_work")
    private Boolean travelForWork;

    public Guest() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public Boolean getPaperlessConfirm() {
        return paperlessConfirm;
    }

    public void setPaperlessConfirm(Boolean paperlessConfirm) {
        this.paperlessConfirm = paperlessConfirm;
    }

    public Boolean getTravelForWork() {
        return travelForWork;
    }

    public void setTravelForWork(Boolean travelForWork) {
        this.travelForWork = travelForWork;
    }

    public Guest(String firstName, String lastName, String country, String email, String phoneNo, Boolean paperlessConfirm, Boolean travelForWork) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
        this.email = email;
        this.phoneNo = phoneNo;
        this.paperlessConfirm = paperlessConfirm;
        this.travelForWork = travelForWork;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
