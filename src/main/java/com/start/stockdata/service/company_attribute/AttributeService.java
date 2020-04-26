package com.start.stockdata.service.company_attribute;

import java.util.List;

public interface AttributeService<
        RQ,
        RS,
        T  // Long or UUID(String)
        > {

    RS save(final T mainEntityId, RQ requestDto);

    RS update(final T mainEntityId, final T id, RQ requestDto);

    RS delete(final T mainEntityId, final T id);

    List<RS> deleteAllByCompanyId(final T mainEntityId);

    RS findById(final T mainEntityId, final T fieldId);

    List<RS> findAllByCompanyId(final T mainEntityId);

    T count(final T companyId, boolean includeDeleted);

}
