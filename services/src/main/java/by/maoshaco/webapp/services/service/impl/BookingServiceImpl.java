package by.maoshaco.webapp.services.service.impl;

import by.maoshaco.webapp.dao.model.Booking;
import by.maoshaco.webapp.dao.repository.BookingRepository;
import by.maoshaco.webapp.services.service.BookingService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BookingServiceImpl extends CrudServiceImpl<Booking, Long, BookingRepository> implements BookingService {

}
