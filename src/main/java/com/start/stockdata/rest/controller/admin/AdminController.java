package com.start.stockdata.rest.controller.admin;

import com.start.stockdata.identity.dto.response.CompanyDto;
import com.start.stockdata.service.CompanyService;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.start.stockdata.util.constants.UriPath.ADMIN_PATH;

@RequestMapping(ADMIN_PATH)
@RestController
@Api("Admin Management System")
@SwaggerDefinition(securityDefinition = @SecurityDefinition(
        apiKeyAuthDefinitions = {
                @ApiKeyAuthDefinition(key = "custom",
                        name = "authorization",
                        in = ApiKeyAuthDefinition.ApiKeyLocation.HEADER,
                        description = "Bearer Authentication")}))
public class AdminController {

    private final CompanyService companyService;

    public AdminController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping(value = "companies")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(authorizations = @Authorization("custom"),
            value = "Get all companies")
    public ResponseEntity<List<CompanyDto>> getAllCompanies() {
        return new ResponseEntity<>(companyService.findAll(), HttpStatus.OK);
    }

}