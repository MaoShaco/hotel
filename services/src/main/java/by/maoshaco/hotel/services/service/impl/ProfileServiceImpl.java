package by.maoshaco.hotel.services.service.impl;

import by.maoshaco.hotel.dao.model.Profile;
import by.maoshaco.hotel.dao.repository.ProfileRepository;
import by.maoshaco.hotel.services.service.ProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class ProfileServiceImpl extends CrudServiceImpl<Profile, Long, ProfileRepository> implements ProfileService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorityServiceImpl.class);

    @Override
    public Profile findByUsername(String username) {
        LOGGER.debug("Finding {} entity with username= {}", simpleTypeName, username);
        return repository.findByUsername(username);
    }
}
