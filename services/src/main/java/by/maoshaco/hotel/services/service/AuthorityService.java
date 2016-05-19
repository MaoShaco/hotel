package by.maoshaco.hotel.services.service;

import by.maoshaco.hotel.dao.model.Authority;

public interface AuthorityService extends CrudService<Authority, Long> {
    Authority findByRole(String role);
}
