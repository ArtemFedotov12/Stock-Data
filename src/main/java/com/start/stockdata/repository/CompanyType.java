package com.start.stockdata.repository;

import com.start.stockdata.identity.model.Company;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyType extends AbstractRemovableEntityRepository<Company>, JpaSpecificationExecutor<Company> {

}
