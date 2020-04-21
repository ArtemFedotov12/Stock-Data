package com.start.stockdata.identity.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel("Model, contains information for creation company's factors")
public class CompanyFactorRequestDto extends AbstractRequestDto {

    private static final long serialVersionUID = 2223384917635270366L;

    @Size(max = 255)
    @NotBlank
    @ApiModelProperty(value = "Description. Constraints: @Size(max = 255),  @NotBlank", example = "Bad weather")
    String asset;

    @NotBlank
    @Size(max = 255)
    @ApiModelProperty(value = "Description. Constraints: @Size(max = 255),  @NotBlank", example = "Weather in the local country")
    String displayName;

}
