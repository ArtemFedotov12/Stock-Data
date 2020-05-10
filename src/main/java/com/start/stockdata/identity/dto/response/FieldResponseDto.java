package com.start.stockdata.identity.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
public class FieldResponseDto extends AbstractResponseDto {

    private static final long serialVersionUID = 854976592269661261L;

    String asset;
    String displayName;
}
