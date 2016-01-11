package com.epam.training.dao.repository;

import org.springframework.data.repository.CrudRepository;

import com.epam.training.dao.model.Booking;


public interface BookingRepository extends CrudRepository<Booking, Long> {
	
}

