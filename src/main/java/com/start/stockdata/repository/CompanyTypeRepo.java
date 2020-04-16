package com.start.stockdata.repository;

import com.start.stockdata.identity.model.CompanyType;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyTypeRepo extends AbstractRemovableEntityRepo<CompanyType>, JpaSpecificationExecutor<CompanyType> {

    Optional<CompanyType> findByType(String type);

}
