package com.start.stockdata.repository;

import com.start.stockdata.identity.model.CompanyField;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CompanyFieldRepo extends AbstractRemovableEntityRepo<CompanyField>, JpaSpecificationExecutor<CompanyField> {
}
