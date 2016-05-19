package by.maoshaco.webapp.services.service;

import by.maoshaco.webapp.dao.model.Hotel;

public interface HotelService extends CrudService<Hotel, Long>  {
    Hotel findByName(String name);
}
