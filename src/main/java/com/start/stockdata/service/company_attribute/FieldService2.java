package com.start.stockdata.service.company_attribute;

import com.start.stockdata.identity.dto.request.FieldRequestDto;
import com.start.stockdata.identity.dto.response.FieldResponseDto;
import com.start.stockdata.repository.FieldRepo;
import com.start.stockdata.service.stock_global.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FieldService2<
        RQ extends FieldRequestDto,
        RS extends FieldResponseDto
        >
        implements AttributeService<RQ, RS, Long> {

    @Autowired
    private CompanyService companyService;
    @Autowired
    private FieldRepo fieldRepo;


    @Override
    public RS save(Long mainEntityId, RQ requestDto) {
        return null;
    }

    @Override
    public RS update(Long mainEntityId, Long aLong, RQ requestDto) {
        return null;
    }

    @Override
    public RS delete(Long mainEntityId, Long aLong) {
        //companyService.deleteField(companyFieldRepo.findById(aLong).get());
        return null;
    }

    @Override
    public List<RS> deleteAllByMainEntityId(Long mainEntityId) {
        return null;
    }

    @Override
    public RS findById(Long mainEntityId, Long aLong) {
        return null;
    }

    @Override
    public List<RS> findAllByMainEntityId(Long mainEntityId) {
        return null;
    }

    @Override
    public Long count(Long mainEntityId, boolean includeDeleted) {
        return null;
    }
}
