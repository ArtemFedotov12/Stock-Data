package com.start.stockdata.service;

import com.start.stockdata.identity.dto.AbstractEntityDto;
import com.start.stockdata.identity.model.AbstractEntity;
import com.start.stockdata.wrapper.AbstractEntityDtoWrapper;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class AbstractService<E extends AbstractEntity, D extends AbstractEntityDto> {
    //protected final AbstractEntityDtoWrapper<E, D> wrapper;
}
