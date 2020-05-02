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
public class FieldRequestDto extends AbstractRequestDto {

    private static final long serialVersionUID = 7856897212698563490L;

    @Size(max = 255)
    @NotBlank
    @ApiModelProperty(value = "Description. Constraints: @Size(max = 255),  @NotBlank", example = "Less than 100")
    String asset;

    @NotBlank
    @Size(max = 255)
    @ApiModelProperty(value = "Description. Constraints: @Size(max = 255),  @NotBlank", example = "Number of employee in the company")
    String displayName;

}
