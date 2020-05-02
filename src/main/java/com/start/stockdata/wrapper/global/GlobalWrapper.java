package com.start.stockdata.wrapper.global;

import java.util.List;
import java.util.Optional;

public interface GlobalWrapper<E, ID> {

    E save(E entity);

    E update(E entity);

    Optional<E> findById(ID id);

    List<E> findAll();

    void deleteById(ID id);

    void deleteAll();

    Long count(boolean includeDeleted);

}
