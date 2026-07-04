package com.tourMaster.letsTour.Controller;

import com.tourMaster.letsTour.DTOs.SignUpRequestDTO;
import com.tourMaster.letsTour.Service.TourDestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    TourDestService tourDestService;

    @Autowired
    public LoginController(TourDestService tourDestService) {
        this.tourDestService = tourDestService;
    }


    @PostMapping("/api/v1/register")
    public String signUpUser(@ModelAttribute SignUpRequestDTO signUpUser)
    {
        ResponseEntity responseEntity=tourDestService.signUpUser(signUpUser);
        return responseEntity.getStatusCode()== HttpStatus.OK?"redirect:/login.html":"redirect:/Registration.html";
    }

    @GetMapping("/login")
    public String getLoginPage()
    {
        return "redirect:/login.html";
    }


    @GetMapping("/bookingScreen")
    public String getBookingPage(@RequestParam Integer destId, @RequestParam Integer packageId)
    {
        return "redirect:/bookPage.html?destId="+destId+"&packageId="+packageId;
    }

}
