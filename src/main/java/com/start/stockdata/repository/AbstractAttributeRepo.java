package com.start.stockdata.repository;

import com.start.stockdata.identity.model.AbstractAttributeEntity;
import com.start.stockdata.identity.model.CompanyField;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AbstractAttributeRepo <T extends AbstractAttributeEntity> extends AbstractEntityRepo<T> {

  /*  @Query("select e from #{#entityName} e" +
            " where :includeDeleted = true or m.removalDate is null ")
    List<T> findAllByMainEntityId(@Param("mainEntityId") Long mainEntityId);*/
}
