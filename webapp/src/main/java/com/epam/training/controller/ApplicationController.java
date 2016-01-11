package com.epam.training.controller;

import com.epam.training.dao.model.CustomProfileDetail;
import com.epam.training.dao.model.Profile;
import com.epam.training.security.AllowedForAdminAndHotelManager;
import com.epam.training.security.SecurityConfig;
import com.epam.training.services.service.HotelService;
import com.epam.training.services.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ApplicationController {

    @Autowired
    HotelService hotels;

    @Autowired
    ProfileService users;


    @RequestMapping(value = "/")
    public String root(Model model) {
        model.addAttribute("hotels", hotels.findAll());
        return "landing-page";
    }

    @RequestMapping(value = "/signedin")
    public String signedIn(Model model, Authentication authentication) {

        CustomProfileDetail principal = (authentication != null) ?
                (CustomProfileDetail) authentication.getPrincipal() : null;

        if (principal != null) {
            String a = principal.getAuthorities().iterator().next().getAuthority();
            switch (a) {
                case ("ROLE_ADMIN"):
                    return "redirect:/admin";
                case ("ROLE_USER"):
                    return "redirect:/users/me";
                case "ROLE_HOTEL_MANAGER":
                    return "redirect:/users/me";
            }
        }

        return "/";
    }

    @RequestMapping(value = "/admin")
    @AllowedForAdminAndHotelManager
    public String manageUsers(Model model) {
        model.addAttribute("users", users.findAll());
        model.addAttribute("hotels", hotels.findAll());
        return "admin-dashboard";
    }

    @RequestMapping(value = "/login/{username}/{password}", method = RequestMethod.GET)
    public
    @ResponseBody
    boolean checkLogin(Model model, @PathVariable("username") String username, @PathVariable("password") String password) {
        Profile user = users.findByUsername(username);
        boolean isRight = user != null && SecurityConfig.encoder.matches(password, user.getPassword());
        return  isRight;
    }
}
