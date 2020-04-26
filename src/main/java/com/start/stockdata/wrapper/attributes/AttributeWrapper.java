package com.start.stockdata.wrapper.attributes;

import com.start.stockdata.identity.dto.active.AbstractActiveDto;
import com.start.stockdata.identity.model.AbstractEntity;
import com.start.stockdata.repository.AbstractEntityRepo;

import java.util.List;

public interface AttributeWrapper<
        E extends AbstractEntity,
        A extends AbstractActiveDto,
        R extends AbstractEntityRepo<E>,
        T // Long or UUID(String)
        > {

    E save(A activeDto);

    E update(final T id, A activeDto);

    E delete(final T id);

    List<E> deleteAllByCompanyId(final T mainEntityId);

    E findById(final T id);

    List<E> findAllByCompanyId(final T mainEntityId);

    T count(boolean includeDeleted);

}
