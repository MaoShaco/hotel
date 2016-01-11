package com.epam.training.security;

import com.epam.training.dao.model.Booking;
import com.epam.training.dao.model.CustomProfileDetail;
import com.epam.training.dao.model.Hotel;
import com.epam.training.dao.model.Profile;
import com.epam.training.services.service.BookingService;
import com.epam.training.services.service.HotelService;
import com.epam.training.services.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("mySecurityService")
public class MySecurityService {

	@Autowired
	HotelService hotels;
	
	@Autowired
	ProfileService users;
	
	@Autowired
	BookingService bookings;
	
	public boolean canEditHotel(long hotel_id, CustomProfileDetail user){
		Hotel hotel = hotels.findOne(hotel_id);
		return hotel != null && hotel.getManager() != null && user.getProfile().getId() == hotel.getManager().getId();
	}
	
	public boolean canEditHotel(long hotel_id, String s){
		return false;
	}
	
	public boolean canEditUser(long user_id, CustomProfileDetail user){
		Profile profileTmp = users.findOne(user_id);
		return profileTmp != null && user.getProfile() != null && user.getProfile().getId() == profileTmp.getId();
	}
	
	public boolean canApproveBooking(long booking_id, CustomProfileDetail user){
		Booking booking = bookings.findOne(booking_id);
		return booking != null && user != null && booking.getHotel().getManager().getId() == user.getProfile().getId();
	}
	public boolean canRemoveBooking(long booking_id, CustomProfileDetail user){
		Booking booking = bookings.findOne(booking_id);
		return booking != null && user != null 
				&& (booking.getHotel().getManager().getId() == user.getProfile().getId()
				|| booking.getProfile().getId() == user.getProfile().getId());
		
	}
}
