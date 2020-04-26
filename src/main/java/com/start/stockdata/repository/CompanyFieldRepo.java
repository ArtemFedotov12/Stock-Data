package com.start.stockdata.repository;

import com.start.stockdata.identity.model.CompanyField;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyFieldRepo extends AbstractRemovableEntityRepo<CompanyField>, JpaSpecificationExecutor<CompanyField> {
}
