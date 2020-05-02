package com.start.stockdata.repository;

import com.start.stockdata.identity.model.AbstractAttributeEntity;

public interface AbstractAttributeRepo <T extends AbstractAttributeEntity> extends AbstractEntityRepo<T> {

  /*  @Query("select e from #{#entityName} e" +
            " where :includeDeleted = true or m.removalDate is null ")
    List<T> findAllByMainEntityId(@Param("mainEntityId") Long mainEntityId);*/
}
