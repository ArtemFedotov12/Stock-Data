package com.start.stockdata.identity.dto.active;

import com.start.stockdata.identity.model.Company;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CompanyFieldActiveDto extends AbstractActiveDto {

    String asset;
    String displayName;
    String shortName;
    Company company;

}
