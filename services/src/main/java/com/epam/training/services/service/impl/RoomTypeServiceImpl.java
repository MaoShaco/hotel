package com.epam.training.services.service.impl;

import com.epam.training.dao.model.RoomType;
import com.epam.training.dao.repository.RoomTypeRepository;
import com.epam.training.services.service.RoomTypeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class RoomTypeServiceImpl extends CrudServiceImpl<RoomType, Long, RoomTypeRepository> implements RoomTypeService {

}
