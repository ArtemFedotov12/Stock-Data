package com.start.stockdata.service;

import com.start.stockdata.exception.exception.DeletionEntityByIdNotFoundException;
import com.start.stockdata.exception.exception.EntityAlreadyExistsException;
import com.start.stockdata.exception.exception.EntityByIdNotFoundException;
import com.start.stockdata.identity.converter.entity_to_dto.ResponseConverter;
import com.start.stockdata.identity.dto.request.AbstractRequestDto;
import com.start.stockdata.identity.dto.response.AbstractResponseDto;
import com.start.stockdata.identity.model.AbstractEntity;
import com.start.stockdata.wrapper.AbstractEntityDtoWrapper;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Transactional
public abstract class AbstractService<
        E extends AbstractEntity,
        RQ extends AbstractRequestDto,
        RS extends AbstractResponseDto,
        WR extends AbstractEntityDtoWrapper<E, RQ, ?>> {

    protected final WR wrapper;
    protected final ResponseConverter<E, RS> converter;

    public AbstractService(WR wrapper, ResponseConverter<E, RS> converter) {
        this.wrapper = wrapper;
        this.converter = converter;
    }

    public RS save(RQ requestDto) {
        if (entityAlreadyExistsSave(requestDto)) {
            throw new EntityAlreadyExistsException(requestDto);
        }
        E entity = wrapper.save(requestDto);
        return converter.toDto(entity);
    }

    public RS update(final Long id, RQ requestDto) {
        if (entityAlreadyExistsUpdate(id, requestDto)) {
            throw new EntityAlreadyExistsException(requestDto);
        }
        E entity = wrapper.update(id, requestDto);
        return converter.toDto(entity);
    }

    public RS delete(Long id) {
        Optional<E> optional = wrapper.findById(id);
        if (optional.isPresent()) {
            wrapper.delete(id);
            return converter.toDto(optional.get());
        } else {
            throw new DeletionEntityByIdNotFoundException();
        }
    }

    public List<RS> findAll() {
        return convert(wrapper.findAll());
    }

    public RS findById(final Long id) {
        Optional<E> entityOptional = wrapper.findById(id);
        if (entityOptional.isPresent()) {
            return converter.toDto(entityOptional.get());
        } else {
            throw new EntityByIdNotFoundException(id);
        }
    }

    public Long count(boolean includeDeleted) {
        return wrapper.count(includeDeleted);
    }

    /**
     * @param id id entity to be found
     * @throws com.start.stockdata.exception.exception.EntityByIdNotFoundException if such entity doesn't exist
     */
    protected void checkIfEntityByIdExists(final Long id) {
        Optional<E> optional = wrapper.findById(id);
        if (!optional.isPresent()) {
            throw new EntityByIdNotFoundException(id);
        }

    }

    protected Set<RS> convert(Set<E> entitySet) {
        return entitySet.stream()
                .map(converter::toDto)
                .collect(Collectors.toSet());
    }

    protected Page<RS> convert(Page<E> entitySet) {
        return entitySet
                .map(converter::toDto);
    }

    protected List<RS> convert(List<E> entityList) {
        return entityList.stream()
                .map(converter::toDto)
                .collect(Collectors.toList());
    }

    protected abstract boolean entityAlreadyExistsSave(RQ requestDto);

    //Entity that must be updated, must be excluded from check
    protected abstract boolean entityAlreadyExistsUpdate(final Long id, RQ requestDto);


}
