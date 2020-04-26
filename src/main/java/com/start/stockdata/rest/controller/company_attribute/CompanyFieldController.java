package com.start.stockdata.rest.controller.company_attribute;

import com.start.stockdata.identity.dto.request.CompanyFieldRequestDto;
import com.start.stockdata.identity.dto.response.CompanyFieldResponseDto;
import com.start.stockdata.service.company_attribute.FieldService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.start.stockdata.util.constants.UriPath.COMPANY_FIELD_PATH;

@RestController
@RequestMapping(COMPANY_FIELD_PATH)
@Api
public class CompanyFieldController extends AbstractCompanyAttributeController<
        CompanyFieldRequestDto,
        CompanyFieldResponseDto,
        FieldService
        > {

    public CompanyFieldController(FieldService service) {
        super(service);
    }

}
