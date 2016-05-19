package by.maoshaco.hotel.webapp.security;

import by.maoshaco.hotel.dao.model.CustomProfileDetail;
import by.maoshaco.hotel.dao.model.Hotel;
import by.maoshaco.hotel.services.service.BookingService;
import by.maoshaco.hotel.services.service.HotelService;
import by.maoshaco.hotel.dao.model.Booking;
import by.maoshaco.hotel.dao.model.Profile;
import by.maoshaco.hotel.services.service.ProfileService;
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
