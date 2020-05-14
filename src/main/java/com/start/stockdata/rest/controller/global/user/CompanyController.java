package com.start.stockdata.rest.controller.global.user;

import com.start.stockdata.identity.dto.request.company.CompanyRequestDto;
import com.start.stockdata.identity.dto.response.CompanyResponseDto;
import com.start.stockdata.rest.controller.global.AbstractStockGlobalController;
import com.start.stockdata.service.global.CompanyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.start.stockdata.util.constants.UriPath.COMPANIES_PATH;

@RestController
@RequestMapping(COMPANIES_PATH)
@Api
/*@SwaggerDefinition(securityDefinition = @SecurityDefinition(
        apiKeyAuthDefinitions = {
                @ApiKeyAuthDefinition(key = "custom",
                        name = "Authorization",
                        in = ApiKeyAuthDefinition.ApiKeyLocation.HEADER,
                        description = "Bearer Authentication")}))*/
public class CompanyController extends AbstractStockGlobalController<CompanyRequestDto, CompanyResponseDto, CompanyService> {
    

    public CompanyController(CompanyService service) {
        super(service);
    }

    @ApiOperation(
            value = "Delete all user's companies",
            notes = "Method allows to delete all user's companies"
    )
    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CompanyResponseDto>> deleteAll() {
        return new ResponseEntity<>(service.deleteAll(), HttpStatus.OK);
    }

}
