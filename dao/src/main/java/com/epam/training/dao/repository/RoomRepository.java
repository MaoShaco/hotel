package com.epam.training.dao.repository;

import org.springframework.data.repository.CrudRepository;

import com.epam.training.dao.model.Room;


public interface RoomRepository extends CrudRepository<Room, Long> {
	
	
}

