package by.maoshaco.webapp.dao.repository;

import by.maoshaco.webapp.dao.model.Authority;
import org.springframework.data.repository.CrudRepository;


public interface AuthorityRepository extends CrudRepository<Authority, Long> {
	
	Authority findByRole(String role);
}

