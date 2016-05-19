package by.maoshaco.webapp.dao.repository;

import org.springframework.data.repository.CrudRepository;

import by.maoshaco.webapp.dao.model.Profile;


public interface ProfileRepository extends CrudRepository<Profile, Long> {
	
	Profile findByUsername(String username);
}

