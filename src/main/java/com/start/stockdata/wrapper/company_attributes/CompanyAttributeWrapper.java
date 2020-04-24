package com.start.stockdata.wrapper.company_attributes;

import com.start.stockdata.identity.dto.request.AbstractRequestDto;
import com.start.stockdata.identity.model.AbstractEntity;
import com.start.stockdata.repository.AbstractEntityRepo;

import java.util.List;

public interface CompanyAttributeWrapper<
        E extends AbstractEntity,
        RQ extends AbstractRequestDto,
        R extends AbstractEntityRepo<E>,
        T
        > {

    E save(final T companyId, RQ requestDto);

    E update(final T id, RQ requestDto);

    E delete(final T id);

    List<E> deleteAllByCompanyId(final T companyId);

    E findById(final T fieldId);

    List<E> findAllByCompanyId(final T companyId);

    T count(boolean includeDeleted);

}
