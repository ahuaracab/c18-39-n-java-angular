package com.nocountry.docspotback.repositories;

import org.hibernate.annotations.Filter;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Deleted<T> {
    @Filter(name = "deletedFilter", condition = "is_deleted = true")
    List<T> findAll();
}