package by.maoshaco.hotel.dao.services.service.impl;

import by.maoshaco.hotel.dao.model.Authority;
import by.maoshaco.hotel.dao.repository.AuthorityRepository;
import by.maoshaco.hotel.dao.services.service.AuthorityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AuthorityServiceImpl extends CrudServiceImpl<Authority, Long, AuthorityRepository> implements AuthorityService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorityServiceImpl.class);

    @Override
    public Authority findByRole(String role) {
        LOGGER.debug("Finding {} entity with role= {}", simpleTypeName, role);
        return repository.findByRole(role);
    }
}
