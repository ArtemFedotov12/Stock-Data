package com.start.stockdata.identity.dto;

import io.swagger.annotations.ApiModel;
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
public class CompanyFactorCreationDto extends AbstractSerializableDto{

    private static final long serialVersionUID = 2223384917635270366L;

    @Size(max = 255)
    @NotBlank
    String asset;

    @NotBlank
    @Size(max = 255)
    String displayName;

}
