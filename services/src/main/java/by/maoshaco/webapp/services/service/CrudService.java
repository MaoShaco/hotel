package by.maoshaco.webapp.services.service;

import java.io.Serializable;

public interface CrudService <T, ID extends Serializable> {
    public long count();

    public void delete(ID id);

    public void delete(Iterable<? extends T> entities);

    public void delete(T entity);

    public void deleteAll();

    public boolean exists(ID id);

    public Iterable<T> findAll();

    public Iterable<T> findAll(Iterable<ID> ids);

    public T findOne(ID id);

    public <S extends T> Iterable<S> save(Iterable<S> entities);

    public <S extends T> S save(S entity);
}