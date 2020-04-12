package com.start.stockdata.identity.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(exclude = "id")
public abstract class AbstractEntityDto implements Serializable {

    private static final long serialVersionUID = 1196865354332323549L;

    @ApiModelProperty("Unique identifier of the object")
    private Long id;
}