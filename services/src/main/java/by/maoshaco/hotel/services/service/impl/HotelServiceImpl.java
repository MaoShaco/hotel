package by.maoshaco.hotel.services.service.impl;

import by.maoshaco.hotel.dao.model.Hotel;
import by.maoshaco.hotel.services.service.HotelService;
import by.maoshaco.hotel.dao.repository.HotelRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class HotelServiceImpl extends CrudServiceImpl<Hotel, Long, HotelRepository> implements HotelService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorityServiceImpl.class);

    @Override
    public Hotel findByName(String name) {
        LOGGER.debug("Finding {} entity with name= {}", simpleTypeName, name);
        return repository.findByName(name);
    }
}
