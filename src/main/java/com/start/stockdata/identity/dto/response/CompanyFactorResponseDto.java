package com.start.stockdata.identity.dto.response;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CompanyFactorResponseDto extends AbstractResponseDto {

    private static final long serialVersionUID = 8002179021299884086L;

    String asset;
    String displayName;

}
