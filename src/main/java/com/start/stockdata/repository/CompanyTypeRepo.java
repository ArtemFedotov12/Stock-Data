package com.start.stockdata.repository;

import com.start.stockdata.identity.model.Company;
import com.start.stockdata.identity.model.CompanyType;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyTypeRepo extends AbstractRemovableEntityRepo<CompanyType>, JpaSpecificationExecutor<CompanyType> {

}
