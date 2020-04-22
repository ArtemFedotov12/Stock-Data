package com.start.stockdata.identity.dto.response;

import com.start.stockdata.identity.dto.different.CompanyTypeIRequestdDto;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CompanyResponseDto extends AbstractResponseDto {

    private static final long serialVersionUID = 2435885082115582596L;

    private String name;
    private Set<CompanyTypeIRequestdDto> types;
    private Set<CompanyFieldDto> fields;
    private Set<CompanyFactorDto> factors;
    private Long userId;

}
