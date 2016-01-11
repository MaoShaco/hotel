package com.epam.training.services.service.impl;

import com.epam.training.dao.model.Authority;
import com.epam.training.dao.repository.AuthorityRepository;
import com.epam.training.services.service.AuthorityService;
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
