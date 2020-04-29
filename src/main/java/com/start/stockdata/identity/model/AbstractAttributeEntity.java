package com.start.stockdata.identity.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.MappedSuperclass;

@Getter
@Setter
@MappedSuperclass
@NoArgsConstructor
public class AbstractAttributeEntity extends AbstractRemovableEntity {

}
