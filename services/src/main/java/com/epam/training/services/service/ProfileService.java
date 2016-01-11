package com.epam.training.services.service;


import com.epam.training.dao.model.Profile;

public interface ProfileService extends CrudService<Profile, Long> {
    Profile findByUsername(String username);
}
