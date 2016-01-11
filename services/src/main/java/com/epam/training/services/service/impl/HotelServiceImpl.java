package com.epam.training.services.service.impl;

import com.epam.training.dao.model.Hotel;
import com.epam.training.dao.repository.HotelRepository;
import com.epam.training.services.service.HotelService;
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
