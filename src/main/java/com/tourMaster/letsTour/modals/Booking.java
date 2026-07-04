package com.tourMaster.letsTour.modals;


import com.tourMaster.letsTour.enums.BookingStatus;
import jakarta.persistence.*;

@Entity
@Table(name = "booking")
public class Booking {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private Integer id;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User bookedBy;

    @ManyToOne
    @JoinColumn(name = "package_id")
    private TourPackage bookedPackage;

    @OneToOne
    @JoinColumn(name = "guest_id")
    private Guest bookedFor;

    @Column(name = "booking_date_time")
    private String bookingDateTime;

    @Column(name = "checkin_date")
    private String checkInDate;

    @Column(name = "checkout_date")
    private String checkOutDate;

    @Column(name = "totalAmount")
    private String totalAmount;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private BookingStatus bookingStatus;

    public Booking() {
    }

    public Booking(String bookingDateTime, String checkInDate, String checkOutDate, String totalAmount, BookingStatus bookingStatus) {
        this.bookingDateTime = bookingDateTime;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.totalAmount = totalAmount;
        this.bookingStatus = bookingStatus;
    }


    public User getBookedBy() {
        return bookedBy;
    }

    public void setBookedBy(User bookedBy) {
        this.bookedBy = bookedBy;
    }

    public TourPackage getBookedPackage() {
        return bookedPackage;
    }

    public void setBookedPackage(TourPackage bookedPackage) {
        this.bookedPackage = bookedPackage;
    }

    public Guest getBookedFor() {
        return bookedFor;
    }

    public void setBookedFor(Guest bookedFor) {
        this.bookedFor = bookedFor;
    }

    public String getBookingDateTime() {
        return bookingDateTime;
    }

    public void setBookingDateTime(String bookingDateTime) {
        this.bookingDateTime = bookingDateTime;
    }

    public String getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(String checkInDate) {
        this.checkInDate = checkInDate;
    }

    public String getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(String checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
