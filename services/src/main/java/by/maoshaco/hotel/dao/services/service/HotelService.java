package by.maoshaco.hotel.dao.services.service;

import by.maoshaco.hotel.dao.model.Hotel;

public interface HotelService extends CrudService<Hotel, Long>  {
    Hotel findByName(String name);
}
