package com.start.stockdata.rest.controller;

import com.start.stockdata.identity.dto.request.CompanyTypeRequestDto;
import com.start.stockdata.identity.dto.response.CompanyTypeDto;
import com.start.stockdata.service.CompanyTypeService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CompanyTypeController extends AbstractController<CompanyTypeRequestDto, CompanyTypeDto, CompanyTypeService>{

    public CompanyTypeController(CompanyTypeService service) {
        super(service);
    }

    public void m() {
        //
    }

}
