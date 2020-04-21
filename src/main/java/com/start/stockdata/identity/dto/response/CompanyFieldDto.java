package com.start.stockdata.identity.dto.response;

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
public class CompanyFieldDto extends AbstractResponseDto {

    private static final long serialVersionUID = 854976592269661261L;

    @Size(max = 255)
    @NotBlank
    String asset;

    @NotBlank
    @Size(max = 255)
    String displayName;
}
