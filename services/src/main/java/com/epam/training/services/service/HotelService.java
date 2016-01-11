package com.epam.training.services.service;

import com.epam.training.dao.model.Hotel;

public interface HotelService extends CrudService<Hotel, Long>  {
    Hotel findByName(String name);
}
