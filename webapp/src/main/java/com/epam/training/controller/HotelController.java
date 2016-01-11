package com.epam.training.controller;

import com.epam.training.dao.model.Booking;
import com.epam.training.dao.model.Hotel;
import com.epam.training.dao.model.Room;
import com.epam.training.dao.model.RoomType;
import com.epam.training.security.AllowedForManageHotel;
import com.epam.training.services.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping(value = "/hotels")
public class HotelController {

    @Autowired
    HotelService hotels;

    @Autowired
    RoomTypeService roomTypes;

    @Autowired
    RoomService rooms;

    @Autowired
    ProfileService users;

    @Autowired
    BookingService bookings;

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public String show(@PathVariable("id") long id, Model model) {
        Hotel hotel = hotels.findOne(id);

        model.addAttribute("booking", new Booking());
        model.addAttribute("hotel", hotel);
        model.addAttribute("users", users.findAll());
        model.addAttribute("roomTypes", roomTypes.findAll());

        Map<Long, Room> rmap = hotel.getRooms();
        Map<RoomType, Room> rttemp = new HashMap<>();

        for (Room r : rmap.values()) {
            rttemp.put(r.getType(), r);
        }

        model.addAttribute("hotelRoomTypes", rttemp);
        return "hotels/show";
    }

    @RequestMapping(value = "{id}/edit", method = RequestMethod.GET)
    @AllowedForManageHotel
    public String edit(@PathVariable("id") long id, Model model) {
        Hotel hotel = hotels.findOne(id);
        model.addAttribute("hotel", hotel);
        model.addAttribute("users", users.findAll());
        return "hotels/edit";
    }

    @RequestMapping(value = "{id}", method = RequestMethod.POST)
    @AllowedForManageHotel
    public String editSave(@PathVariable("id") long id, @ModelAttribute("hotel") Hotel hotel) {
        hotels.save(hotel);
        return "redirect:/hotels/{id}";
    }

    @RequestMapping(value = "{id}/map", method = RequestMethod.POST)
    @AllowedForManageHotel
    public String hotelMap(@PathVariable("id") long id, Model model, @ModelAttribute Booking booking) {
        model.addAttribute("begin_date", booking.getBegin_date());
        model.addAttribute("end_date", booking.getEnd_date());
        model.addAttribute("hotel", hotels.findOne(id));
        model.addAttribute("occupancy", getOccupancy(hotels.findOne(id), booking.getBegin_date(), booking.getEnd_date()));
        return "hotels/map";
    }

    public Map<Room, Map<Date, Boolean>> getOccupancy(Hotel hotel, Date begining, Date end) {
        List<Date> dates = new LinkedList<>();
        Calendar calendar = new GregorianCalendar();

        calendar.setTime(begining);

        while (calendar.getTime().getTime() <= end.getTime()) {
            Date result = calendar.getTime();
            dates.add(result);
            calendar.add(Calendar.DATE, 1);
        }

        Map<Room, Map<Date, Boolean>> result = new TreeMap<>();

        for (Room r : hotel.getRooms().values()) {
            Map<Date, Long> days_reserved = r.getDays_reserved();
            Map<Date, Boolean> roomOcc = new TreeMap<>();

            for (Date d : dates) {
                SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
                String date = fmt.format(d);

                for (Date day_reserved : days_reserved.keySet()) {
                    String day = fmt.format(day_reserved);

                    if (date.equals(day)) {
                        roomOcc.put(d, true);
                    } else {
                        if (!(roomOcc.containsKey(d) && roomOcc.get(d)))
                            roomOcc.put(d, false);
                    }
                }

                if (days_reserved.isEmpty())
                    roomOcc.put(d, false);
            }

            result.put(r, roomOcc);
        }

        return result;
    }
}
