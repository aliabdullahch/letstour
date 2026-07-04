package com.tourMaster.letsTour.modals;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;


    @JsonProperty("first_name")
    @Column(name="first_name")
    private String firstName;


    @JsonProperty("last_name")
    @Column(name="last_name")
    private String lastName;

    @Column(name="email")
    private String email;

    @Column(name="address")
    private String address;

@JsonProperty("phone_no")
    @Column(name="phone_no")
    private String phoneNo;

@Column(name = "Role")
@Enumerated(EnumType.STRING)
  private Role role;

    public void setRole(Role role) {
        this.role = role;
    }

    public Role getRole() {
        return role;
    }

    @Column(name="password")
  private String  password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    @OneToMany(mappedBy = "reviewedBy", cascade = CascadeType.ALL)
    @JsonBackReference("reviews_by_user")
    List<Review> reviews;

    public void addReview(Review rev)
    {
        if (this.reviews==null)
        {
            reviews= new ArrayList<>();
        }
        reviews.add(rev);
        rev.setReviewedBy(this);
    }

    @OneToMany(mappedBy = "bookedBy", cascade = {CascadeType.ALL})
    private List<Booking> myBookings;

    public void addMyBooking(Booking booking)
    {
        if(this.myBookings==null){
            myBookings = new ArrayList<>();
        }
        myBookings.add(booking);
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }






    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public User()
    {

    }

    public User(String firstName,  String lastName, String email, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public User(String firstName, String lastName, String email, String address, String phoneNo,String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.phoneNo = phoneNo;
        this.password=password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                '}';
    }
}
