package by.maoshaco.hotel.dao.services.service.impl;

import by.maoshaco.hotel.dao.services.service.RoomService;
import by.maoshaco.hotel.dao.model.Room;
import by.maoshaco.hotel.dao.repository.RoomRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class RoomServiceImpl extends CrudServiceImpl<Room, Long, RoomRepository> implements RoomService {
}
