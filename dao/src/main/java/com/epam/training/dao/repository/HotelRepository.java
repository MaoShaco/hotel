package com.epam.training.dao.repository;

import org.springframework.data.repository.CrudRepository;

import com.epam.training.dao.model.Hotel;


public interface HotelRepository extends CrudRepository<Hotel, Long> {
	
	Hotel findByName(String name);
}

