package by.maoshaco.webapp.dao.repository;

import by.maoshaco.webapp.dao.model.Hotel;
import org.springframework.data.repository.CrudRepository;


public interface HotelRepository extends CrudRepository<Hotel, Long> {
	
	Hotel findByName(String name);
}

