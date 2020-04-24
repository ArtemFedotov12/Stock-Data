package com.start.stockdata.service.company_attribute;

import com.start.stockdata.identity.dto.request.AbstractRequestDto;
import com.start.stockdata.identity.dto.response.AbstractResponseDto;
import com.start.stockdata.identity.model.AbstractEntity;
import com.start.stockdata.wrapper.company_attributes.CompanyAttributeWrapper;

import java.util.List;

public interface CompanyAttributeService<
        E extends AbstractEntity,
        RS extends AbstractResponseDto,
        RQ extends AbstractRequestDto,
        WR extends CompanyAttributeWrapper,
        T // Long or UUID(String)
        > {

    RS save(final T companyId, RQ requestDto);

    RS update(final T id, RQ requestDto);

    RS delete(final T id);

    List<RS> deleteAllByCompanyId(final T companyId);

    RS findById(final T fieldId);

    List<RS> findAllByCompanyId(final T companyId);

    T count(boolean includeDeleted);

}
