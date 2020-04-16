package com.start.stockdata.service;

import com.start.stockdata.exception.exception.DeletionEntityByIdNotFoundException;
import com.start.stockdata.exception.exception.EntityAlreadyExistsException;
import com.start.stockdata.identity.converter.creation_dto_to_simple_dto.RequestConverter;
import com.start.stockdata.identity.dto.request.AbstractRequestDto;
import com.start.stockdata.identity.dto.response.AbstractResponseDto;
import com.start.stockdata.wrapper.AbstractEntityDtoWrapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public abstract class AbstractService<
        RQ extends AbstractRequestDto,
        RS extends AbstractResponseDto,
        WR extends AbstractEntityDtoWrapper<?, RS, ?>> {

    protected final WR wrapper;
    protected final RequestConverter<RQ, RS> converter;

    public AbstractService(WR wrapper, RequestConverter<RQ, RS> converter) {
        this.wrapper = wrapper;
        this.converter = converter;
    }

    public RS save(RQ requestDto) {
        if (entityAlreadyExists(this.converter.convert(requestDto))) {
            throw new EntityAlreadyExistsException(requestDto);
        }
        return wrapper.save(this.convert(requestDto));
    }

    public RS delete(Long id) {
        Optional<RS> optional = Optional.ofNullable(findById(id));
        if (optional.isPresent()) {
            wrapper.delete(id);
            return optional.get();
        } else {
            throw new DeletionEntityByIdNotFoundException();
        }
    }

    public List<RS> findAll() {
        return wrapper.findAll();
    }

    public RS findById(Long id) {
        return wrapper.findById(id);
    }

    private RS convert(RQ requestBody) {
        return converter.convert(requestBody);
    }

    protected abstract boolean entityAlreadyExists(RS responseDto);


}
