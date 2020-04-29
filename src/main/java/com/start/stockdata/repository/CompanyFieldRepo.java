package com.start.stockdata.repository;

import com.start.stockdata.identity.model.Company;
import com.start.stockdata.identity.model.CompanyField;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyFieldRepo extends AbstractRemovableEntityRepo<CompanyField>, JpaSpecificationExecutor<CompanyField> {

    @Query(value =
            "select * " +
                    "from company_field" +
                    " where company_id=:mainEntityId",
            nativeQuery = true)
    List<CompanyField> findAllByCompanyId(@Param("mainEntityId") Long mainEntityId);

    @Query(value =
            "select count(*)" +
                    " from company_field" +
                    " where company_id = :mainEntityId",
            nativeQuery = true)
    Long count(@Param("mainEntityId") Long mainEntityId);
}
