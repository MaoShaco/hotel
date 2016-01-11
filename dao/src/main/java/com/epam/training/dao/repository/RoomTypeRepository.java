package com.epam.training.dao.repository;

import org.springframework.data.repository.CrudRepository;

import com.epam.training.dao.model.RoomType;


public interface RoomTypeRepository extends CrudRepository<RoomType, Long> {
	
	
}

