package com.tourMaster.letsTour.booking.controllers;

import com.google.gson.Gson;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Price;
import com.stripe.model.Product;
import com.stripe.model.checkout.Session;
import com.stripe.param.PriceCreateParams;
import com.stripe.param.ProductCreateParams;
import com.stripe.param.checkout.SessionCreateParams;
import com.tourMaster.letsTour.Service.TourDestService;
import com.tourMaster.letsTour.modals.Guest;
import com.tourMaster.letsTour.modals.TourPackage;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("api/v1")
public class BookingController {
    TourDestService tourDestService;
    @Value("${stripe.secret.api.key}")
    private String apiKey;

@Autowired
    public BookingController(TourDestService tourDestService) {
        this.tourDestService = tourDestService;
    }

    @PostMapping("/createBooking")
    public ResponseEntity<Map<String,String>> createBooking(@Valid @ModelAttribute Guest guest, @RequestParam Integer packageId, @RequestParam Integer destId, BindingResult result)
    {
        if(!result.hasErrors())
        {
            Guest fetchedGuest= this.tourDestService.getGuestByService(guest.getEmail());
            if(fetchedGuest==null)
            {
                // create a guest
                this.tourDestService.createBooking(guest,packageId,destId);
                Map<String,String> responseMap= new HashMap<>();
                responseMap.put("Guest created successfully",guest.getEmail());
                return new ResponseEntity<>(responseMap, HttpStatus.OK);

            }else
            {
                Map<String,String> responseMap= new HashMap<>();
                responseMap.put("Guest already created",guest.getEmail());
                return new ResponseEntity<>(responseMap, HttpStatus.FOUND);
            }


        }
        Map<String,String> responseMap= new HashMap<>();
        responseMap.put("Some Error Occurred while Creating the Guest",guest.getEmail());
        return new ResponseEntity<>(responseMap, HttpStatus.NOT_FOUND);


    }

    @PostMapping("/create-checkout-session")
    public ResponseEntity<Map<String, String>> createCheckoutSession( @RequestParam Integer packageId, @RequestParam Integer destId) throws StripeException, StripeException {
        Stripe.apiKey =this.apiKey;
        TourPackage boughtPackage=this.tourDestService.getPackageByDestinationId(packageId,destId);
        String boughtPackagePrice=boughtPackage.getPrice().toString();
        Product product =Product.create(ProductCreateParams.builder().setName(boughtPackage.getName()).addImage(boughtPackage.getPckgImg()).setDescription(boughtPackage.getDescription()).build());


        Price price = Price.create(
                PriceCreateParams.builder()
                        .setCurrency("pkr")
                        .setUnitAmount((long) (Integer.parseInt(boughtPackagePrice) * 100))  // in cents
                        .setProduct(product.getId())       // existing product ID
                        .build()
        );

        String YOUR_DOMAIN = "http://localhost:8080";
        String checkoutSessionID="";
        SessionCreateParams params =
                SessionCreateParams.builder()
                        .setUiMode(SessionCreateParams.UiMode.EMBEDDED)
                        .setMode(SessionCreateParams.Mode.PAYMENT)
                        .setReturnUrl(YOUR_DOMAIN + "/mainPage.html?session_id="+checkoutSessionID)
                        .addLineItem(
                                SessionCreateParams.LineItem.builder()
                                        .setQuantity(1L)
                                        // Provide the exact Price ID (for example, price_1234) of the product you want to sell
                                        .setPrice(price.getId())
                                        .build())
                        .build();
Session session = Session.create(params);

        Map<String, String> map = new HashMap();
        map.put("clientSecret", session.getClientSecret());
        System.out.println("Following is the response body");
        System.out.println(map);
        return ResponseEntity.ok(map);
    }

}
