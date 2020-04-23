package com.start.stockdata.repository;

import com.start.stockdata.identity.model.Company;
import com.start.stockdata.repository.projection.CompanyName;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepo extends AbstractRemovableEntityRepo<Company>, JpaSpecificationExecutor<Company> {
    //@Query(value = "select * from company where user_id = :userId",nativeQuery = true)
    List<Company> findAllByUserId(/*@Param("userId")*/ Long userId);

    //@Query(value = "select name from company where user_id = :userId", nativeQuery = true)
    List<CompanyName> findByUserId(Long userId);
}
