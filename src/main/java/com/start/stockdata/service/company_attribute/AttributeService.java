package com.start.stockdata.service.company_attribute;

import java.util.List;

public interface AttributeService<
        RQ,
        RS,
        ID  // Long or UUID(String)
        > {

    RS save(final ID mainEntityId, RQ requestDto);

    RS update(final ID mainEntityId, final ID id, RQ requestDto);

    RS delete(final ID mainEntityId, final ID id);

    List<RS> deleteAllByCompanyId(final ID mainEntityId);

    RS findById(final ID mainEntityId, final ID id);

    List<RS> findAllByCompanyId(final ID mainEntityId);

    ID count(final ID mainEntityId, boolean includeDeleted);

}
