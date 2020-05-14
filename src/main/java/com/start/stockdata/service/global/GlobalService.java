package com.start.stockdata.service.global;

import java.util.List;

public interface GlobalService<
        RQ,
        RS,
        ID // Long or String(UUID)
        > {


    RS save(RQ requestDto);

    RS update(final ID id, RQ requestDto);

    RS deleteById(ID id);

    List<RS> deleteAll();

    List<RS> findAll();

    RS findById(final ID id);

    Long count(boolean includeDeleted);

}
