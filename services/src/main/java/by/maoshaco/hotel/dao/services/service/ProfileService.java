package by.maoshaco.hotel.dao.services.service;


import by.maoshaco.hotel.dao.model.Profile;

public interface ProfileService extends CrudService<Profile, Long> {
    Profile findByUsername(String username);
}
