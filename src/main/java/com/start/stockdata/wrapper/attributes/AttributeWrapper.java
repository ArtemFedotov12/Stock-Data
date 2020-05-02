package com.start.stockdata.wrapper.attributes;

import java.util.List;
import java.util.Optional;

public interface AttributeWrapper<
        E,
        ID // Long or UUID(String)
        > {

    E save(ID mainEntity, E entity);

    E update(ID mainEntity, final ID id, E entity);

    void delete(ID mainEntity, final E entity);

    void deleteAllByMainEntityId(final ID mainEntityId);

    Optional<E> findById(final ID id);

    List<E> findAllByMainEntityId(final ID mainEntityId);

    ID count(ID mainEntityId, boolean includeDeleted);

}
