package com.epam.training.controller;

import com.epam.training.dao.model.Authority;
import com.epam.training.dao.model.Booking;
import com.epam.training.dao.model.CustomProfileDetail;
import com.epam.training.dao.model.Profile;
import com.epam.training.security.AllowedForManageUser;
import com.epam.training.security.SecurityConfig;
import com.epam.training.services.service.AuthorityService;
import com.epam.training.services.service.BookingService;
import com.epam.training.services.service.ProfileService;
import com.epam.training.services.service.impl.CustomUserDetailsService;
import com.epam.training.services.util.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    ProfileService users;

    @Autowired
    BookingService bookings;

    @Autowired
    AuthorityService authorities;

    @Autowired
    private AuthenticationManager authMgr;

    @Autowired
    private CustomUserDetailsService customUserDetailsSvc;

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newUser(Model model) {
        model.addAttribute("profile", new Profile());
        return "users/create";
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    @AllowedForManageUser
    public String show(@PathVariable("id") long id, Model model) {
        Profile profile = users.findOne(id);
        model.addAttribute("profile", profile);
        model.addAttribute("bookings", getUserBookings(profile.getId()));
        return "users/show";
    }

    @RequestMapping(value = "/me", method = RequestMethod.GET)
    public String showActiveProfile(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomProfileDetail myUser = (CustomProfileDetail) auth.getPrincipal();
        Profile profile = users.findOne(myUser.getProfile().getId());
        model.addAttribute("bookings", getUserBookings(profile.getId()));
        model.addAttribute("profile", profile);
        return "users/show";
    }

    public Iterable<Booking> getUserBookings(long user_id) {
        Iterator<Booking> itbookings = bookings.findAll().iterator();
        List<Booking> bookingsList = new ArrayList<>();

        while (itbookings.hasNext()) {
            Booking b = itbookings.next();
            if (b.getProfile().getId() == user_id)
                bookingsList.add(b);
        }

        return bookingsList;
    }

    @RequestMapping(value = "{id}/remove", method = RequestMethod.GET)
    @AllowedForManageUser
    public String remove(@PathVariable("id") long id, Model model) {
        Profile profile = users.findOne(id);
        if (profile == null)
            throw new UserNotFoundException();
        users.delete(profile);
        model.addAttribute("users", users.findAll());
        return "redirect:/admin";
    }

    @AllowedForManageUser
    @RequestMapping(value = "{id}/edit", method = RequestMethod.GET)
    public String edit(@PathVariable("id") long id, Model model) {
        Profile profile = users.findOne(id);
        model.addAttribute("profile", profile);
        model.addAttribute("authorities", authorities.findAll());
        return "users/edit";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public String edit(@PathVariable("id") long id, @ModelAttribute Profile profile, Model model) {
        Profile dbuser = users.findOne(profile.getId());
        if (Objects.equals(dbuser.getUsername(), profile.getUsername()) || users.findByUsername(profile.getUsername()) == null) {
            if(profile.getAuthority() == null)
                profile.setAuthority(authorities.findByRole("ROLE_USER"));

            if(!profile.getPassword().equals(dbuser.getPassword()))
                profile.setPassword(SecurityConfig.encoder.encode(profile.getPassword()));

            users.save(profile);
            model.addAttribute("profile", profile);
            model.addAttribute("authorities", authorities.findAll());
            return "redirect:/admin";
        }
        model.addAttribute("profile", profile);
        model.addAttribute("authorities", authorities.findAll());
        return "users/edit";

    }

    @RequestMapping(method = RequestMethod.POST)
    public String saveProfile(@ModelAttribute Profile profile, Model model) {
        if (users.findByUsername(profile.getUsername()) != null) {
            model.addAttribute("profile", profile);
            return "users/create";
        }
        Authority authority = authorities.findByRole("ROLE_USER");
        profile.setAuthority(authority);
        String pass = profile.getPassword();
        profile.setPassword(SecurityConfig.encoder.encode(profile.getPassword()));
        users.save(profile);

        UserDetails userDetails = customUserDetailsSvc.loadUserByUsername(profile.getUsername());

        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, pass, userDetails.getAuthorities());
        authMgr.authenticate(auth);
        SecurityContextHolder.getContext().setAuthentication(auth);
        return "redirect:/users/me";
    }
}