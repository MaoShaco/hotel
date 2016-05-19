package by.maoshaco.webapp.services.service.impl;

import by.maoshaco.webapp.dao.model.Room;
import by.maoshaco.webapp.dao.repository.RoomRepository;
import by.maoshaco.webapp.services.service.RoomService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class RoomServiceImpl extends CrudServiceImpl<Room, Long, RoomRepository> implements RoomService {
}
