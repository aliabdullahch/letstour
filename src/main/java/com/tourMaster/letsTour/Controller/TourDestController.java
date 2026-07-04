package com.tourMaster.letsTour.Controller;

import com.tourMaster.letsTour.DTOs.DestinationDescriptionDTO;
import com.tourMaster.letsTour.Service.TourDestService;
import com.tourMaster.letsTour.modals.*;
import jdk.jfr.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class TourDestController
{

    TourDestService tourDestService;

    @Autowired
    public TourDestController(TourDestService tourDestService) {
        this.tourDestService = tourDestService;
    }

    @GetMapping("/tourDestinations")
    public List<TourDestination> getAllTourDestinations()
    {
        return this.tourDestService.getAllTourDestinationPlaces();
    }
    @GetMapping("/posts")
    public String getSomePost()
    {
        return "Hello the Posts are being returned";
    }

    @GetMapping("/tourDestinationsAgencies")
    public TourDestination destinationWithAgencies(@RequestParam Integer Id)
    {
        TourDestination destination=this.tourDestService.getDestinationWithImagesAgencies(Id);
        return destination;
    }
    @GetMapping("/packages")
    public List<TourPackage> getPackages(@RequestParam Integer agencyId, @RequestParam Integer destId)
    {
       return this.tourDestService.getPackageByAgencyIdDestinationId(agencyId,destId);
    }
    @PostMapping("/users")
    public void createUser(@ModelAttribute User myUser)
    {
        System.out.println(myUser);
        tourDestService.saveUser(myUser);
        // now creating the booking inside the tour dest service
       // tourDestService.createBooking(myUser.getId(),packId);


    }


    @PostMapping("/user/login")
    public void logonUser(@RequestBody User tempUser)
    {
        this.tourDestService.saveUser(tempUser);

    }
    @GetMapping("/search")
    public List<TourDestination> getSearchedPlaces(@RequestParam String name)
    {
        return this.tourDestService.advanceSearchDestinations(name);
    }
    @GetMapping("/destinations")
    public List<TourDestination> getPackageOffers(@RequestParam String name, @RequestParam String date, @RequestParam String persons)
    {
        return this.tourDestService.getDestinationsByNameDatePerson(name,date,persons);
    }
    @GetMapping("/reviewDetails")
    public DestinationReview getReviewDetails(@RequestParam String  name)
    {
        return this.tourDestService.getDestinationReviewByPlaceName(name);
    }
    @GetMapping("/areaTypes")
    public Filter getAllAreaTypes()
    {
       return new Filter("areaType",this.tourDestService.getAllPossibleAreaTypes()) ;
    }
    @GetMapping("/filterTourDestinations")
    public List<TourDestination> getSideFilteredData(@RequestParam String type, @RequestParam String value)
    {
        return this.tourDestService.getSideFilteredData(type, value);
    }

    @GetMapping("/reviews")
    public List<Review> getReviewsByDestination(@RequestParam Integer destId)
    {
        return  tourDestService.getReviewsByDestinationId(destId);

    }


    @GetMapping("/categoryReviews")
    public List<CategoryReview> getCategoryReviewsByDestination(@RequestParam Integer destId)
    {
        return this.tourDestService.getCategoryReviewsByDestinationIds(destId);
    }

    @GetMapping("/tourPackageDest")
    public TourPackage getTourPackageByDestId(@RequestParam Integer packageId,@RequestParam Integer destId)
    {
        return this.tourDestService.getPackageByDestinationId(packageId,destId);
    }

    @GetMapping("/detailedDesc")
    public DestinationDescriptionDTO getDetailedDescription(@RequestParam String destId)
    {
        return this.tourDestService.getdetailedDescription(destId);
    }








}
