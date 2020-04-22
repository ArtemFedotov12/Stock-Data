package com.start.stockdata.identity.dto.response;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CompanyTypeResponseDto extends AbstractResponseDto {

    private static final long serialVersionUID = 5481306025689592009L;

    private String type;

}
