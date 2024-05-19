package com.nocountry.docspotback.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.annotations.Filter;
import org.springframework.stereotype.Repository;

import java.lang.reflect.ParameterizedType;
import java.util.List;

@Repository
public class NotDeleted<T>  {

    @PersistenceContext
    private EntityManager entityManager;

    @Filter(name = "notDeletedFilter", condition = "is_deleted = false")
    public List<T> findAll(){
        return entityManager.createQuery("SELECT e FROM " + getEntityClass().getSimpleName() + " e WHERE e.isDeleted = false", getEntityClass()).getResultList();

    }

     Class<T> getEntityClass() {
        // get the entity class from the type parameter
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
}