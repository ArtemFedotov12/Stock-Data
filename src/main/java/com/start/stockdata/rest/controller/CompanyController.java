package com.start.stockdata.rest.controller;

import com.start.stockdata.identity.dto.CompanyDto;
import com.start.stockdata.service.CompanyService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

import static com.start.stockdata.util.constants.UriPath.COMPANIES_PATH;

@RestController
@RequiredArgsConstructor
@RequestMapping(COMPANIES_PATH)
@Api("Company Management System")
@SwaggerDefinition(securityDefinition = @SecurityDefinition(
        apiKeyAuthDefinitions = {
                @ApiKeyAuthDefinition(key = "custom",
                        name = "authorization",
                        in = ApiKeyAuthDefinition.ApiKeyLocation.HEADER,
                        description = "Bearer Authentication")}))
public class CompanyController {

    private final CompanyService companyService;

    // Get companies by user id. Id will be taken from token
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    @ApiOperation(authorizations = @Authorization("custom"),
            value = "Get all user's companies")
    public ResponseEntity<List<CompanyDto>> getUserCompanies() {
        return new ResponseEntity<>(companyService.findAllByUserId(), HttpStatus.OK);
    }

    @GetMapping({"id"})
    public List<CompanyDto> getAll(@PathVariable String id) {
        return null;
    }

    @PostMapping
    @ApiOperation("Group creation")
    public ResponseEntity<CompanyDto> create(@Valid @RequestBody CompanyDto companyDto) {
        CompanyDto result = companyService.save(companyDto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    //@DeleteMapping("/c")

}