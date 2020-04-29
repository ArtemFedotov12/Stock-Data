package com.start.stockdata.wrapper.attributes;

import com.start.stockdata.identity.dto.active.AbstractActiveDto;
import com.start.stockdata.identity.model.AbstractEntity;

import java.util.List;
import java.util.Optional;

public interface AttributeWrapper<
        E extends AbstractEntity,
        A extends AbstractActiveDto,
        ID // Long or UUID(String)
        > {

    E save(A activeDto);

    E update(final ID id, A activeDto);

    void delete(final ID id);

    void deleteAllByCompanyId(final ID mainEntityId);

    Optional<E> findById(final ID id);

    List<E> findAllByCompanyId(final ID mainEntityId);

    ID count(ID mainEntityId, boolean includeDeleted);

}
