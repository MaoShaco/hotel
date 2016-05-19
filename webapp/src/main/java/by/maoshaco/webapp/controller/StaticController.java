package by.maoshaco.webapp.controller;

import by.maoshaco.webapp.services.service.RoomTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/fortune")
public class StaticController {

    @Autowired
    RoomTypeService roomTypes;

    @RequestMapping(value="/facilities")
    public String facilities(Model model) {
        model.addAttribute("roomTypes", roomTypes.findAll());
        return "static/facilities";
    }

    @RequestMapping(value="/details")
    public String details() {
        return "static/details";
    }
}
