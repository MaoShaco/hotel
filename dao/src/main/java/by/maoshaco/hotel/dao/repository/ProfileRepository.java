package by.maoshaco.hotel.dao.repository;

import org.springframework.data.repository.CrudRepository;

import by.maoshaco.hotel.dao.model.Profile;


public interface ProfileRepository extends CrudRepository<Profile, Long> {
	
	Profile findByUsername(String username);
}

