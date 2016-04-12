package by.maoshaco.hotel.dao.repository;

import org.springframework.data.repository.CrudRepository;

import by.maoshaco.hotel.dao.model.Booking;


public interface BookingRepository extends CrudRepository<Booking, Long> {
	
}

