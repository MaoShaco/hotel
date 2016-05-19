package by.maoshaco.hotel.services.service.impl;

import by.maoshaco.hotel.services.service.BookingService;
import by.maoshaco.hotel.dao.model.Booking;
import by.maoshaco.hotel.dao.repository.BookingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BookingServiceImpl extends CrudServiceImpl<Booking, Long, BookingRepository> implements BookingService {

}
