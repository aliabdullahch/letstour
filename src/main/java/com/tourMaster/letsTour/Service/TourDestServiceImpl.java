package com.tourMaster.letsTour.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.tourMaster.letsTour.DAO.AdvanceSearchDAO;
import com.tourMaster.letsTour.DAO.TourDestDAO;
import com.tourMaster.letsTour.DTOs.DestinationDescriptionDTO;
import com.tourMaster.letsTour.DTOs.SignUpRequestDTO;
import com.tourMaster.letsTour.enums.BookingStatus;
import com.tourMaster.letsTour.modals.*;
import com.tourMaster.letsTour.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.print.attribute.standard.Destination;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class TourDestServiceImpl implements TourDestService {

    private TourDestDAO tourDestDAO;
    private AdvanceSearchDAO advanceSearchDAO;
    private final WebClient webClient;


    @Autowired
    public TourDestServiceImpl(TourDestDAO tourDestDAO, AdvanceSearchDAO advanceSearchDAO,WebClient.Builder builder) {
        this.tourDestDAO = tourDestDAO;
        this.advanceSearchDAO=advanceSearchDAO;
        this.webClient=builder.baseUrl("https://places.googleapis.com/v1").build();
    }

    @Override
    public List<TourDestination> getAllTourDestinationPlaces() {
        return this.tourDestDAO.getAllTourDestinations();
    }

    @Override
    public void saveDestinationWithAgencies(TourDestination tourDestination) {
        this.tourDestDAO.saveDestinationWithAgencies(tourDestination);
    }

    @Override
    public TourDestination getDestinationWithAgencies(Integer Id) {
        return this.tourDestDAO.getDestinationWithAgencies(Id);
    }

    @Override
    public TourDestination getDestinationById(Integer Id) {
        return this.tourDestDAO.getDestinationById(Id);
    }

    @Override
    public TravelAgency getTravelAgencyById(Integer Id) {
        return this.tourDestDAO.getTravelAgencyById(Id);
    }

    @Override
    public void updateAgencyWithPackage(TravelAgency ta) {
        this.tourDestDAO.updateAgencyWithPackage(ta);
    }

    @Override
    public TravelAgency getAgencyWithPackages(Integer id) {
        return this.tourDestDAO.getAgencyWithPackages(id);
    }

    @Override
    public void removePackageFromAgency(Integer Id) {
        this.tourDestDAO.removePackageFromAgency(Id);
    }

    @Override
    public List<TourPackage> getPackageByAgencyIdDestinationId(Integer agencyId, Integer destinationId) {
        return this.tourDestDAO.getPackagesByAgencyIdDestinationId(agencyId,destinationId);
    }

    @Override
    public TourDestination getDestinationWithPackages(Integer id) {
        return this.tourDestDAO.getDestinationWithPackages(id);
    }

    @Override
    public void updateDestinationWithPackage(TourDestination td) {
        this.tourDestDAO.updateDestinationWithPackage(td);
    }

    @Override
    public TourDestination getDestinationWithImagesAgencies(Integer id) {
        return this.tourDestDAO.getDestinationWithImagesAgencies(id);
    }

    @Override
    public void saveUser(User myUser) {
        this.tourDestDAO.saveUser(myUser);
    }



    @Override
    public Integer userExists(String email) {
        return this.tourDestDAO.isUserExists(email);
    }

    @Override
    public ResponseEntity<?> signUpUser(SignUpRequestDTO obj) {
        if(this.userExists(obj.getEmail())!=-1)
        {
            return ResponseEntity.badRequest().body("Sorry the email has been taken already.");
        }
        PasswordEncoder passwordEncoder =new BCryptPasswordEncoder();
        User newUser = new User(obj.getFirstName(),obj.getLastName(),obj.getEmail(),obj.getAddress(),obj.getPhoneNo(), passwordEncoder.encode(obj.getPassword()));
        newUser.setRole(Role.ROLE_USER);
        this.tourDestDAO.saveUser(newUser);
        return ResponseEntity.ok("Your are signed Up Successfully");

    }

    @Override
    public List<TourDestination> advanceSearchDestinations(String keyword) {

        return this.advanceSearchDAO.searchDestinationsByNameandDesc(keyword);
    }

    @Override
    public Mono<JsonNode> makeRequestForPlaceId(String placeName) {
        return this.webClient.post()
                .uri("/places:searchText")
                .header("X-Goog-Api-Key","AIzaSyC32er8DdSebWReoIuWY3qzXc56oUZLWcs")
                .header("X-Goog-FieldMask","places.id,places.displayName")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(Map.of("textQuery",placeName)).retrieve().bodyToMono(JsonNode.class);

    }

    @Override
    public Mono<String> getPlaceIdByName(String placeName) {
        return this.makeRequestForPlaceId(placeName)
                .map(response->response.get("places").get(0).get("id").asText());
    }

    @Override
    public Mono<String> getUserReviewsByPlaceName(String placeName) {
        return this.getPlaceIdByName(placeName)
                .flatMap(placeId-> this.getReviewsByplaceId(placeId));
    }

    Mono<String> getReviewsByplaceId(String placeId)
    {
       return  this.webClient.get()
                .uri("/places/"+placeId+"?fields=id,displayName,reviews&key=AIzaSyC32er8DdSebWReoIuWY3qzXc56oUZLWcs")
                .retrieve()
                .bodyToMono(JsonNode.class).map(response->
                     response.get("reviews").get(0).get("rating").toString()
                );
    }
     public DestinationReview getDestinationReviewByPlaceName(String placeName)
    {
        DestinationReview dr= new DestinationReview();
        dr.setNoOfReviews(20);
        dr.setAverageRating(3.5);
        return dr;
/*        return this.getPlaceIdByName(placeName)
                .flatMap(placeId->
                {
                   return    this.webClient.get()
                            .uri("/places/"+placeId+"?fields=id,displayName,reviews&key=AIzaSyC32er8DdSebWReoIuWY3qzXc56oUZLWcs")
                            .retrieve()
                            .bodyToMono(JsonNode.class).map(response->
                                    {
                                        Double sum=0.0;
                                        if(response.has("reviews"))
                                        {
                                            JsonNode reviews=response.get("reviews");
                                            if (reviews.isArray())
                                            {
                                                for (JsonNode node:reviews)
                                                {
                                                    sum+=node.get("rating").asInt();
                                                }
                                                sum=sum/reviews.size();
                                            }
                                            dr.setNoOfReviews(20);
                                            dr.setAverageRating(3.5);
                                        }
                                        return dr;
                                    }
                            );



                });*/

    }

    @Override
    public List<TourDestination> getSideFilteredData(String type, String value) {
        return this.tourDestDAO.getSideFilteredData(type,value);
    }
@Transactional
    @Override
    public void updateImagePathInDB(String previousName, String newName) {
this.tourDestDAO.updateImagePath(previousName,newName);

    }

    @Override
    public TourDestination getTourDestinationWithImages(Integer destId) {
        return this.tourDestDAO.getTourDestinationWithImages(destId);
    }

    @Override
    public List<String> getAllPossibleAreaTypes() {
        return this.tourDestDAO.getAllPossibleAreaTypes();
    }

    @Override
    public List<CategoryReview> getCategoryReviewsByDestinationIds(Integer id) {
        return  this.tourDestDAO.getCategoryReviewsByDestinationId(id);
    }

    @Override
    public List<TourPackage> getTourPackageByDestinationId(Integer Id) {
        return tourDestDAO.getTourPackagesByDestinationId(Id);
    }

    @Override
    public void updatePackage(TourPackage tp) {
        this.tourDestDAO.updatePackage(tp);
    }

    @Override
    public TourPackage getPackageByDestinationId( Integer packageId,Integer destId) {
        return this.tourDestDAO.getPackageByDestination(packageId,destId);
    }

    public TourDestDAO getTourDestDAO() {
        return tourDestDAO;
    }

    public void setTourDestDAO(TourDestDAO tourDestDAO) {
        this.tourDestDAO = tourDestDAO;
    }

    @Override
    public List<Review> getReviewsByDestinationId(Integer id) {
        return this.tourDestDAO.getReviewsByDestinationId(id);
    }

    @Override
    @Transactional
    public Guest createNewGuest(Guest guest) {
        return this.tourDestDAO.createNewGuest(guest);
    }

    @Override
    public User getUserWithBookingById(Integer id) {
        return this.tourDestDAO.getUserWithBookingById(id);
    }

    @Override
    public Guest getGuestByService(String email) {
        return this.tourDestDAO.getGuestByEmail(email);
    }

    @Override
    public List<TourDestination> getDestinationsByDateAndPersons(String date,String persons) {
        return this.advanceSearchDAO.getDestinationsByDateAndPersons(date,persons);
    }

    @Override
    public List<TourDestination> getDestinationsByNameDatePerson(String name, String date, String persons) {
        return advanceSearchDAO.getDestinationsByNameDatePerson(name,date,persons);
    }

    @Override
    public DestinationDescriptionDTO getdetailedDescription(String destId) {
        return this.tourDestDAO.getdetailedDescription(destId);
    }

    @Transactional
    @Override
    public Integer createBooking(Guest guest, Integer packageId, Integer destId) {
        // saving and retrieving the newly created guest
        Guest savedGuest=this.createNewGuest(guest);

        // getting the user  who is performing the booking (currently logged in user)
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails customUserDetails=(CustomUserDetails) auth.getPrincipal();
        Integer userId =customUserDetails.getUser().getId();
        User logged_in_user=this.getUserWithBookingById(userId);
        // getting the tour package
        TourPackage tp =this.getPackageByDestinationId(packageId,destId);

        // getting the current system date and time
        String currentDateTime=LocalDateTime.now().toString();
        // totalAmount of Booking
        Integer bookingAmount=tp.getPrice();
        // setting the status of the newly created Booking
        Booking newBooking =new Booking(currentDateTime,"a","b",bookingAmount.toString(), BookingStatus.Pending);
        newBooking.setBookedBy(logged_in_user);
        newBooking.setBookedPackage(tp);
        newBooking.setBookedFor(savedGuest);

        logged_in_user.addMyBooking(newBooking);
        tp.addBooking(newBooking);

        return this.tourDestDAO.createBooking(newBooking);

    }
}
