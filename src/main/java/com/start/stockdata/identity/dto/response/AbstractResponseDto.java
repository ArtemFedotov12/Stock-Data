package com.start.stockdata.identity.dto.response;

import com.start.stockdata.identity.dto.AbstractSerializableDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(exclude = "id", callSuper = false)
public abstract class AbstractResponseDto extends AbstractSerializableDto {

    private static final long serialVersionUID = 1196865354332323549L;

    private Long id;

}