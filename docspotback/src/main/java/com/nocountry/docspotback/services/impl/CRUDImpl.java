package com.nocountry.docspotback.services.impl;

import com.nocountry.docspotback.repositories.Deleted;
import com.nocountry.docspotback.repositories.IGenericRepo;
import com.nocountry.docspotback.repositories.NotDeleted;
import com.nocountry.docspotback.services.ICRUDService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class CRUDImpl<T, ID> implements ICRUDService<T, ID> {

    protected abstract IGenericRepo<T, ID> getRepo();

   /* @Autowired
    private NotDeleted<T> notDeletedRepository;

    @Autowired
    private Deleted<T> deletedRepository;
*/
    @Override
    public T save(T t) {
        return getRepo().save(t);
    }

    @Override
    public T update(T t) {
        return getRepo().save(t);
    }

    @Override
    public List<T> findAll() {
        return getRepo().findAll();
    }

    @Override
    public T findById(ID id) {
        return getRepo().findById(id).orElse(null);
    }

    @Override
    public void delete(ID id) {
        getRepo().deleteById(id);
    }

   /* @Override
    public List<T> findAllDeleted() {
        return deletedRepository.findAll();
    }

    @Override
    public List<T> findAllNotDeleted() {
        return notDeletedRepository.findAll();
    }
*/
}