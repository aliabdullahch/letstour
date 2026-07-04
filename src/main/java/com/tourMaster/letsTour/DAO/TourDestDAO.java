package com.tourMaster.letsTour.DAO;

import com.tourMaster.letsTour.DTOs.DestinationDescriptionDTO;
import com.tourMaster.letsTour.modals.*;

import java.util.List;

public interface TourDestDAO {
    List<TourDestination> getAllTourDestinations();
    void saveDestinationWithAgencies(TourDestination tourDestination);
    TourDestination getDestinationWithAgencies(Integer Id);

    TourDestination getDestinationById(Integer id);
    TravelAgency getTravelAgencyById(Integer Id);
    void  updateAgencyWithPackage(TravelAgency ta);
    TravelAgency getAgencyWithPackages(Integer id);
    void removePackageFromAgency(Integer id);
    List<TourPackage> getPackagesByAgencyIdDestinationId(Integer agencyId, Integer destinationId);
    TourDestination getDestinationWithPackages(Integer destId);

    void updateDestinationWithPackage(TourDestination td);

    void saveUser(User myUser);
    User getUserWithBookings(Integer userId);
    TourPackage getPackageWithBookings(Integer packId);
    void updateUserWithBookings(User u);
    void updatePackageWithbokings(TourPackage tp);


    Integer isUserExists(String email);
    User getUserByEmail(String email);
    List<TourDestination> advanceSearchDestinationsByNameandDesc(String name, String desc);
    List<String> getAllPossibleAreaTypes();
    List<TourDestination> getSideFilteredData(String filterType, String filterValue);
    void updateImagePath(String previousName, String newName);
    TourDestination getTourDestinationWithImages(Integer destId);
    TourDestination getDestinationWithImagesAgencies(Integer id);
    List<Review> getReviewsByDestinationId(Integer id);
    List<CategoryReview> getCategoryReviewsByDestinationId(Integer id);
    List<TourPackage>getTourPackagesByDestinationId(Integer Id);
    void updatePackage(TourPackage tp);
    TourPackage getPackageByDestination(Integer packageId, Integer destId);
    Guest  createNewGuest(Guest guest);
    Integer createBooking(Booking booking);
    User getUserWithBookingById(Integer id);
    Guest getGuestByEmail(String email);
    DestinationDescriptionDTO getdetailedDescription(String destId);

}
