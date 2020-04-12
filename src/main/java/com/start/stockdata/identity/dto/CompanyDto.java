package com.start.stockdata.identity.dto;

import com.start.stockdata.util.constraints.Company;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@ApiModel("Model, contains information about company")
public class CompanyDto extends AbstractEntityDto {

    private static final long serialVersionUID = 2435885082115582596L;

    @NotBlank
    @Size(max = 255)
    @ApiModelProperty(value = "Company's name. Constraints: @NotEmpty, @Size(max = 255)", required = true)
    private String name;

    @NotBlank(message = "company's type is empty or null")
    @Size(max = 255)
    @Min(2)
    @Company(message = "company's type must be from specified range of values")
    @ApiModelProperty(value = "Company's type. Constraints: @NotBlank, @Size(max = 255)", required = true)
    private String companyType;

    @NotNull
    @ApiModelProperty(value = "User's id. Constraints: @NotNull", required = true)
    private Long userId;
}
