package com.nocountry.docspotback.services.impl;

import com.nocountry.docspotback.repositories.IGenericRepo;
import com.nocountry.docspotback.services.ICRUDService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

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
        return getRepo().saveAndFlush(t);
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
    @Override
    public List<T> getAllOrder(int page, int size, String sortDir, String sortBy) {
        if (page < 0 || size < 0) {
            throw new IllegalArgumentException("Page and size must be non-negative");
        }

        Sort.Direction direction;
        try {
            direction = Sort.Direction.fromString(sortDir);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid sort direction. Must be ASC or DESC", e);
        }

        PageRequest pageReq = PageRequest.of(page, size, direction, sortBy);
        return getRepo().findAll(pageReq).getContent();
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