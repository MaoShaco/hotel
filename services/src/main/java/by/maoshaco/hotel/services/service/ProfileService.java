package by.maoshaco.hotel.services.service;


import by.maoshaco.hotel.dao.model.Profile;

public interface ProfileService extends CrudService<Profile, Long> {
    Profile findByUsername(String username);
}
