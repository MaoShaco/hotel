package by.maoshaco.hotel.dao.repository;

import by.maoshaco.hotel.dao.model.Hotel;
import org.springframework.data.repository.CrudRepository;


public interface HotelRepository extends CrudRepository<Hotel, Long> {
	
	Hotel findByName(String name);
}

