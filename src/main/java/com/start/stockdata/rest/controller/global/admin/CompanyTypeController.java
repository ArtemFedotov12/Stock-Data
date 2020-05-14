package com.start.stockdata.rest.controller.global.admin;

import com.start.stockdata.identity.dto.request.CompanyTypeRequestDto;
import com.start.stockdata.identity.dto.response.CompanyTypeResponseDto;
import com.start.stockdata.rest.controller.global.AbstractStockGlobalController;
import com.start.stockdata.service.global.CompanyTypeService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.start.stockdata.util.constants.UriPath.COMPANY_TYPE_PATH;

@RestController
@RequestMapping(COMPANY_TYPE_PATH)
@Api
public class CompanyTypeController extends AbstractStockGlobalController<CompanyTypeRequestDto, CompanyTypeResponseDto, CompanyTypeService> {

    public CompanyTypeController(CompanyTypeService service) {
        super(service);
    }

}
