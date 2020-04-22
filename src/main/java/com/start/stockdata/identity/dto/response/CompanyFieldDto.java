package com.start.stockdata.identity.dto.response;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CompanyFieldDto extends AbstractResponseDto {

    private static final long serialVersionUID = 854976592269661261L;

    String asset;
    String displayName;
}
