package com.start.stockdata.identity.dto.request;

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
public class CompanyTypeRequestDto extends AbstractRequestDto {

    private static final long serialVersionUID = 5385003206151795200L;

    @Size(max = 255)
    @NotBlank(message = "First Name cannot be null or empty")
    @ApiModelProperty(value = "Description. Constraints: @Size(max = 255),  @NotBlank", example = "Tech")
    private String type;

}
