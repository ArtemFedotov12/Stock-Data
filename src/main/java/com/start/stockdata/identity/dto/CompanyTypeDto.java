package com.start.stockdata.identity.dto;

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
@ApiModel("Model, contains information about company's type")
public class CompanyTypeDto extends AbstractEntityDto{

    private static final long serialVersionUID = 5481306025689592009L;

    @Size(max = 255)
    private String type;

}
