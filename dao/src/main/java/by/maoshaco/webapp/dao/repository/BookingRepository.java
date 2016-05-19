package by.maoshaco.webapp.dao.repository;

import by.maoshaco.webapp.dao.model.Booking;
import org.springframework.data.repository.CrudRepository;


public interface BookingRepository extends CrudRepository<Booking, Long> {
	
}

