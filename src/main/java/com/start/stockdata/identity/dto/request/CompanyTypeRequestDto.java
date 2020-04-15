package com.start.stockdata.identity.dto.request;

import io.swagger.annotations.ApiModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel("Model, contains information for creation company's type")
public class CompanyTypeRequestDto extends AbstractRequestDto {

    private static final long serialVersionUID = 5385003206151795200L;

    @Size(max = 255)
    private String type;
}
