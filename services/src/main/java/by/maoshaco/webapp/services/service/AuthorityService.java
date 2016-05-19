package by.maoshaco.webapp.services.service;

import by.maoshaco.webapp.dao.model.Authority;

public interface AuthorityService extends CrudService<Authority, Long> {
    Authority findByRole(String role);
}
