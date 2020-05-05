package com.start.stockdata.identity.dto.request.company;

import com.start.stockdata.identity.dto.different.CompanyTypeIdRequestDto;
import com.start.stockdata.identity.dto.request.AbstractRequestDto;
import com.start.stockdata.identity.dto.request.FactorRequestDto;
import com.start.stockdata.identity.dto.request.FieldRequestDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Valid
public class CompanyRequestUpdateDto extends AbstractRequestDto {

    private static final long serialVersionUID = 6771378425908092730L;

    @NotBlank
    @Size(max = 255)
    @ApiModelProperty(value = "Company's name. Constraints: @NotBlank, @Size(max = 255)", required = true)
    private String name;

    @NotEmpty
    @ApiModelProperty(value = "Company's types. Constraints: @NotEmpty", required = true)
    @Valid
    private Set<CompanyTypeIdRequestDto> types;

    @NotEmpty
    @ApiModelProperty(value = "Company's fields. Constraints: @NotEmpty", required = true)
    @Valid
    private Set<FieldRequestDto> fields;

    @NotEmpty
    @ApiModelProperty(value = "Company's factors. Constraints: @NotEmpty", required = true)
    @Valid
    private Set<FactorRequestDto> factors;

}
