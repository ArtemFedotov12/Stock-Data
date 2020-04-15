package com.start.stockdata.repository;

import com.start.stockdata.identity.model.AbstractRemovableEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

@NoRepositoryBean
public interface AbstractRemovableEntityRepo<T extends AbstractRemovableEntity> extends AbstractEntityRepo<T> {
    @Query("select count(m) from #{#entityName} m where :includeDeleted = true or m.removalDate is null ")
    Long count(@Param("includeDeleted") boolean includeDeleted);
}
