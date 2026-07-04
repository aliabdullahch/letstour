package com.tourMaster.letsTour.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.tourMaster.letsTour.DTOs.DestinationDescriptionDTO;
import com.tourMaster.letsTour.DTOs.SignUpRequestDTO;
import com.tourMaster.letsTour.modals.*;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

import java.util.List;

public interface TourDestService {
    List<TourDestination> getAllTourDestinationPlaces();
    void saveDestinationWithAgencies(TourDestination tourDestination);
    TourDestination getDestinationWithAgencies(Integer Id);
    TourDestination getDestinationById(Integer Id);
    TravelAgency getTravelAgencyById(Integer Id);
    void updateAgencyWithPackage(TravelAgency ta);
    TravelAgency getAgencyWithPackages(Integer Id );
    void removePackageFromAgency(Integer Id);
    List<TourPackage> getPackageByAgencyIdDestinationId(Integer agencyId,Integer destinationId);
    TourDestination getDestinationWithPackages(Integer id);

    void updateDestinationWithPackage(TourDestination td);

    void saveUser(User myUser);


    Integer userExists(String email);
    ResponseEntity<?> signUpUser(SignUpRequestDTO obj);
    List<TourDestination> advanceSearchDestinations(String keyword);
    Mono<JsonNode> makeRequestForPlaceId(String placeName);
    Mono<String> getPlaceIdByName(String placeName);
    Mono<String> getUserReviewsByPlaceName(String placeName);
    DestinationReview getDestinationReviewByPlaceName(String placeName);
    List<String> getAllPossibleAreaTypes();
    List<TourDestination> getSideFilteredData(String type,String value);
    void updateImagePathInDB(String previousName, String newName);
    TourDestination getTourDestinationWithImages(Integer destId);
    TourDestination getDestinationWithImagesAgencies(Integer id);
    List<Review> getReviewsByDestinationId(Integer  id);
    List<CategoryReview> getCategoryReviewsByDestinationIds(Integer id);
   List<TourPackage> getTourPackageByDestinationId(Integer Id);
    public void updatePackage(TourPackage tp);
    TourPackage getPackageByDestinationId(Integer packageId, Integer destId);
    Guest  createNewGuest(Guest guest);
    Integer createBooking(Guest guest, Integer packageId, Integer destId);
    User getUserWithBookingById(Integer id);
    Guest getGuestByService(String email);
    List<TourDestination> getDestinationsByDateAndPersons(String date, String persons);
    List<TourDestination> getDestinationsByNameDatePerson(String name, String date, String persons);
    DestinationDescriptionDTO getdetailedDescription(String destId);
}
