package com.epam.training.services.service;

import com.epam.training.dao.model.Authority;

public interface AuthorityService extends CrudService<Authority, Long> {
    Authority findByRole(String role);
}
