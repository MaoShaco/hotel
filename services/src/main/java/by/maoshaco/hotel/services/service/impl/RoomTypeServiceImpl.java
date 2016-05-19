package by.maoshaco.hotel.services.service.impl;

import by.maoshaco.hotel.dao.model.RoomType;
import by.maoshaco.hotel.dao.repository.RoomTypeRepository;
import by.maoshaco.hotel.services.service.RoomTypeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class RoomTypeServiceImpl extends CrudServiceImpl<RoomType, Long, RoomTypeRepository> implements RoomTypeService {

}
