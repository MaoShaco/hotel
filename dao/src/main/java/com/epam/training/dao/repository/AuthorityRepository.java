package com.epam.training.dao.repository;

import org.springframework.data.repository.CrudRepository;

import com.epam.training.dao.model.Authority;


public interface AuthorityRepository extends CrudRepository<Authority, Long> {
	
	Authority findByRole(String role);
}

