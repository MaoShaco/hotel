package com.epam.training.dao.repository;

import org.springframework.data.repository.CrudRepository;

import com.epam.training.dao.model.Profile;


public interface ProfileRepository extends CrudRepository<Profile, Long> {
	
	Profile findByUsername(String username);
}

