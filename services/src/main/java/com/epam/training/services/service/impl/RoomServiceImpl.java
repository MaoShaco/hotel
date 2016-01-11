package com.epam.training.services.service.impl;

import com.epam.training.dao.model.Room;
import com.epam.training.dao.repository.RoomRepository;
import com.epam.training.services.service.RoomService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class RoomServiceImpl extends CrudServiceImpl<Room, Long, RoomRepository> implements RoomService {
}
