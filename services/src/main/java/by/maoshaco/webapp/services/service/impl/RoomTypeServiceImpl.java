package by.maoshaco.webapp.services.service.impl;

import by.maoshaco.webapp.dao.model.RoomType;
import by.maoshaco.webapp.dao.repository.RoomTypeRepository;
import by.maoshaco.webapp.services.service.RoomTypeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class RoomTypeServiceImpl extends CrudServiceImpl<RoomType, Long, RoomTypeRepository> implements RoomTypeService {

}
