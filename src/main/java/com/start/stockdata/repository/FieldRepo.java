package com.start.stockdata.repository;

import com.start.stockdata.identity.model.Field;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FieldRepo extends AbstractRemovableEntityRepo<Field>, JpaSpecificationExecutor<Field> {

    @Query(value =
            "select * " +
                    "from field" +
                    " where company_id=:mainEntityId",
            nativeQuery = true)
    List<Field> findAllByCompanyId(@Param("mainEntityId") Long mainEntityId);

    @Query(value =
            "delete " +
                    "from field" +
                    " where company_id=:mainEntityId",
            nativeQuery = true)
    void deleteAllByCompanyId(@Param("mainEntityId") Long mainEntityId);

    @Query(value =
            "delete " +
                    "from field" +
                    " where id=:id",
            nativeQuery = true)
    void deleteById(@Param("id") Long id);

    @Query(value =
            "select count(*)" +
                    " from field" +
                    " where company_id = :mainEntityId",
            nativeQuery = true)
    Long count(@Param("mainEntityId") Long mainEntityId);
}
