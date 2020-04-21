package com.start.stockdata.identity.dto.response;

import com.start.stockdata.identity.dto.different.CompanyTypeIdDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CompanyDto extends AbstractResponseDto {

    private static final long serialVersionUID = 2435885082115582596L;

    @NotBlank
    @Size(max = 255)
    @ApiModelProperty(value = "Company's name. Constraints: @NotBlank, @Size(max = 255)", required = true)
    private String name;

    @NotEmpty
    @ApiModelProperty(value = "Company's types. Constraints: @NotEmpty", required = true)
    private Set<CompanyTypeIdDto> types;

    @NotEmpty
    @ApiModelProperty(value = "Company's fields. Constraints: @NotEmpty", required = true)
    private Set<CompanyFieldDto> fields;

    @NotEmpty
    @ApiModelProperty(value = "Company's factors. Constraints: @NotEmpty", required = true)
    private Set<CompanyFactorDto> factors;

    @NotNull
    @ApiModelProperty(value = "User's id. Company belongs to this user. Constraints: @NotNull", required = true)
    private Long userId;

}
