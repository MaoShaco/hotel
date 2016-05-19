package by.maoshaco.webapp.services.service;


import by.maoshaco.webapp.dao.model.Profile;

public interface ProfileService extends CrudService<Profile, Long> {
    Profile findByUsername(String username);
}
