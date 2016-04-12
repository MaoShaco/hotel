package by.maoshaco.hotel.dao.repository;

import by.maoshaco.hotel.dao.model.Authority;
import org.springframework.data.repository.CrudRepository;


public interface AuthorityRepository extends CrudRepository<Authority, Long> {
	
	Authority findByRole(String role);
}

