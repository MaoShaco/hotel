package com.epam.training.services.service.impl;

import com.epam.training.dao.model.Booking;
import com.epam.training.dao.repository.BookingRepository;
import com.epam.training.services.service.BookingService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BookingServiceImpl extends CrudServiceImpl<Booking, Long, BookingRepository> implements BookingService {

}
