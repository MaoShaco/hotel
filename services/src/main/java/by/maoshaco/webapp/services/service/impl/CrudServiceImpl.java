package by.maoshaco.webapp.services.service.impl;

import by.maoshaco.webapp.services.service.CrudService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

@Service
@Transactional
public abstract class CrudServiceImpl<T, ID extends Serializable, REPO extends CrudRepository<T, ID>> implements CrudService<T, ID> {

    private static final Logger LOGGER = LoggerFactory.getLogger(CrudServiceImpl.class);

    @SuppressWarnings({"unchecked"})
    protected final String simpleTypeName = ((Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
            .getActualTypeArguments()[0]).getSimpleName();


    @Autowired
    protected REPO repository;

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    public void delete(ID id) {
        LOGGER.info("Deleting {} with id={}", simpleTypeName, id);
        repository.delete(id);
    }

    @Override
    public void delete(Iterable<? extends T> entities) {
        LOGGER.info("Deleting {} sequence", simpleTypeName);
        repository.delete(entities);
    }

    @Override
    public void delete(T entity) {
        LOGGER.info("Deleting {}", entity);
        repository.delete(entity);
    }

    @Override
    public void deleteAll() {
        LOGGER.info("Deleting all {} entities", simpleTypeName);
        repository.deleteAll();
    }

    @Override
    public boolean exists(ID id) {
        return repository.exists(id);
    }

    @Override
    public Iterable<T> findAll() {
        LOGGER.debug("Finding all {} entities", simpleTypeName);
        Iterable<T> found = repository.findAll();
        LOGGER.trace("Search results: {}", found);

        return found;
    }

    @Override
    public Iterable<T> findAll(Iterable<ID> ids) {
        LOGGER.debug("Finding all {} entities with ids {}", simpleTypeName, ids);
        Iterable<T> found = repository.findAll(ids);
        LOGGER.trace("Search results: {}", found);

        return found;
    }

    @Override
    public T findOne(ID id) {
        LOGGER.debug("Finding {} entity with id {}", simpleTypeName, id);
        T found = repository.findOne(id);
        LOGGER.trace("Search results: {}", found);

        return found;
    }

    @Override
    public <S extends T> Iterable<S> save(Iterable<S> entities) {
        LOGGER.info("Saving {} sequence into database", simpleTypeName);
        Iterable<S> saved = repository.save(entities);
        LOGGER.debug("Successfully saved: {}", saved);

        return saved;
    }


    @Override
    public <S extends T> S save(S entity) {
        LOGGER.info("Saving {} into database", entity);
        S saved = repository.save(entity);
        LOGGER.debug("Successfully saved: {}", saved);

        return saved;
    }
}
