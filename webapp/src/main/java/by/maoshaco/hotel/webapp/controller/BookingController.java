package by.maoshaco.hotel.webapp.controller;

import by.maoshaco.hotel.dao.model.*;
import by.maoshaco.hotel.dao.repository.ProfileRepository;
import by.maoshaco.hotel.dao.repository.RoomRepository;
import by.maoshaco.hotel.dao.repository.RoomTypeRepository;
import by.maoshaco.hotel.webapp.security.AllowedForApprovingBookings;
import by.maoshaco.hotel.webapp.security.AllowedForHotelManager;
import by.maoshaco.hotel.webapp.security.AllowedForRemovingBookings;
import by.maoshaco.hotel.webapp.security.AllowedForSystemUsers;
import by.maoshaco.hotel.services.service.BookingService;
import by.maoshaco.hotel.services.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.Map.Entry;

@Controller
@RequestMapping(value = "/bookings")
@SessionAttributes({"booking", "numberRooms", "roomType"})
public class BookingController {

    @Autowired
    BookingService bookings;

    @Autowired
    HotelService hotels;

    @Autowired
    RoomRepository rooms;

    @Autowired
    ProfileRepository users;

    @Autowired
    RoomTypeRepository roomTypes;

    @RequestMapping(method = RequestMethod.GET)
    @AllowedForHotelManager
    public String index(Model model) {
        Profile profile = getCurrentUser();
        List<Booking> books = new ArrayList<>();
        for (Booking book : bookings.findAll()) {
            if (book.getHotel().getManager().getId() == profile.getId()) {
                books.add(book);
            }
        }
        model.addAttribute("bookings", books);
        return "bookings/index";
    }

    @RequestMapping(value = "/new/{hotel_id}", method = RequestMethod.GET)
    @AllowedForSystemUsers
    public String bookRoom(Model model, @PathVariable("hotel_id") long hotel_id, @ModelAttribute("booking") Booking booking, @ModelAttribute("numberRooms") int numberRooms,
                           @ModelAttribute("roomType") long roomType, Authentication authentication) {

        RoomType rt = roomTypes.findOne(roomType);
        List<Date> dates = getDates(booking);

        booking.setProfile(getCurrentUser());
        Hotel hotel = hotels.findOne(hotel_id);
        Map<Long, Room> roomsFromHotel = hotel.getRooms();
        List<Room> rooms_available = new ArrayList<>();
        int counter = 1;
        for (Long entry : roomsFromHotel.keySet()) {
            Room r = roomsFromHotel.get(entry);
            Map<Date, Long> room_bookings = r.getDays_reserved();
            boolean found = false;

            for (Date day : dates) {
                if (room_bookings.get(day) != null) {
                    found = true;
                    break;
                }
            }
            if (!found && r.getType() == rt && counter <= numberRooms) {
                rooms_available.add(r);
                for (Date date : dates)
                    room_bookings.put(date, booking.getId());
                counter++;
            } else if (counter > numberRooms)
                break;
        }
        Set<Room> roomsBooking = new HashSet<>(rooms_available);
        booking.setRooms(roomsBooking);
        bookings.save(booking);
        model.addAttribute("bookings", bookings.findAll());

        CustomProfileDetail principal = (authentication != null) ? (CustomProfileDetail) authentication.getPrincipal() : null;
        if (principal != null) {
            String a = principal.getAuthorities().iterator().next().getAuthority();

            if (a.equals("ROLE_USER") || a.equals("ROLE_ADMIN"))
                return "redirect:/users/me";
        }

        return "redirect:/bookings";
    }

    @AllowedForSystemUsers
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newBooking(Model model) {
        model.addAttribute("booking", new Booking());
        model.addAttribute("roomTypes", roomTypes.findAll());
        return "bookings/create";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @AllowedForSystemUsers
    public String searchRooms(@ModelAttribute Booking booking, Model model, @RequestParam("roomType") long roomType, @RequestParam("numberRooms") int numberRooms) {

        RoomType rt = roomTypes.findOne(roomType);
        List<Room> rooms_available = new ArrayList<>();
        List<Date> dates = getDates(booking);

        for (Hotel hotel : hotels.findAll()) {
            Map<Long, Room> rooms = hotel.getRooms();
            int counter = 0;
            Room currentRoom = null;
            for (Entry<Long, Room> room : rooms.entrySet()) {
                Room r = room.getValue();
                Map<Date, Long> room_bookings = r.getDays_reserved();
                boolean found = false;

                for (Date day : dates) {
                    if (room_bookings.get(day) != null) {
                        found = true;
                        break;
                    }
                }

                if (!found && r.getType().getDescription().equals(rt.getDescription())) {
                    counter++;
                    currentRoom = r;
                }
            }
            if (counter >= numberRooms)
                rooms_available.add(currentRoom);
        }

        model.addAttribute("rooms", rooms_available);
        model.addAttribute("booking", booking);
        model.addAttribute("roomType", rt);
        model.addAttribute("numberRooms", numberRooms);
        return "bookings/results";
    }

    private List<Date> getDates(Booking booking) {

        List<Date> dates = new ArrayList<Date>();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(booking.getBegin_date());

        while (calendar.getTime().getTime() <= booking.getEnd_date().getTime()) {
            Date result = calendar.getTime();
            dates.add(result);
            calendar.add(Calendar.DATE, 1);
        }
        return dates;
    }

    @RequestMapping(value = "/{booking_id}/approve", method = RequestMethod.GET)
    @AllowedForApprovingBookings
    public String approveBooking(Model model, @PathVariable("booking_id") long booking_id) {

        Booking booking = bookings.findOne(booking_id);

        booking.setState(true);
        bookings.save(booking);
        return "redirect:/bookings/";
    }

    @RequestMapping(value = "/{booking_id}/remove", method = RequestMethod.GET)
    @AllowedForRemovingBookings
    public String removeBooking(Model model, @PathVariable("booking_id") long booking_id, Authentication authentication) {
        Booking booking = bookings.findOne(booking_id);

        Set<Room> rooms = booking.getRooms();

        for (Room room : rooms) {
            Map<Date, Long> daysReserved = room.getDays_reserved();

            List<Date> dates = getDates(booking);

            for (Date d : dates)
                daysReserved.remove(d);

            room.setDays_reserved(daysReserved);
        }

        bookings.delete(booking);

        CustomProfileDetail principal = (authentication != null) ? (CustomProfileDetail) authentication.getPrincipal() : null;

        if (principal != null) {
            String a = principal.getAuthorities().iterator().next().getAuthority();

            if (a.equals(("ROLE_USER")))
                return "redirect:/users/me";
        }

        return "redirect:/bookings";
    }

    private Profile getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomProfileDetail myUser = (CustomProfileDetail) auth.getPrincipal();
        return myUser.getProfile();
    }

}
