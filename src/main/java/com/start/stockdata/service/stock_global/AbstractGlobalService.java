package com.start.stockdata.service.stock_global;

import com.start.stockdata.exception.exception.DeletionAllNotFoundException;
import com.start.stockdata.exception.exception.DeletionEntityByIdNotFoundException;
import com.start.stockdata.exception.exception.EntityAlreadyExistsException;
import com.start.stockdata.exception.exception.EntityByIdNotFoundException;
import com.start.stockdata.identity.converter.request.RequestConverter;
import com.start.stockdata.identity.converter.response.ResponseConverter;
import com.start.stockdata.identity.dto.request.AbstractRequestDto;
import com.start.stockdata.identity.dto.response.AbstractResponseDto;
import com.start.stockdata.identity.model.AbstractEntity;
import com.start.stockdata.wrapper.global.GlobalWrapper;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Transactional
public abstract class AbstractGlobalService<
        E extends AbstractEntity,
        RQ extends AbstractRequestDto,
        RS extends AbstractResponseDto,
        WR extends GlobalWrapper<E, Long>
        >
        implements GlobalService<RQ, RS, Long> {

    protected final WR wrapper;
    protected final ResponseConverter<E, RS> responseConverter;
    protected final RequestConverter<E, RQ> requestConverter;

    public AbstractGlobalService(WR wrapper, ResponseConverter<E, RS> responseConverter, RequestConverter<E, RQ> requestConverter) {
        this.wrapper = wrapper;
        this.responseConverter = responseConverter;
        this.requestConverter = requestConverter;
    }

    public RS save(RQ requestDto) {
        if (entityAlreadyExistsSave(requestDto)) {
            throw new EntityAlreadyExistsException(requestDto);
        }
        E entity = wrapper.save(requestConverter.toEntity(requestDto));
        return responseConverter.toDto(entity);
    }

    public RS update(final Long id, RQ requestDto) {
        Optional<E> entityOptional = wrapper.findById(id);
        if (!entityOptional.isPresent()) {
            throw new EntityByIdNotFoundException(id);
        }

        if (entityAlreadyExistsUpdate(id, requestDto)) {
            throw new EntityAlreadyExistsException(requestDto);
        }

        E entity = requestConverter.toEntity(requestDto);
        entity.setId(id);
        return responseConverter.toDto(wrapper.update(entity));
    }

    public RS deleteById(Long id) {
        Optional<E> optional = wrapper.findById(id);
        if (optional.isPresent()) {
            wrapper.deleteById(id);
            return responseConverter.toDto(optional.get());
        } else {
            throw new DeletionEntityByIdNotFoundException(String.valueOf(id));
        }
    }

    @Override
    public List<RS> deleteAll() {
        List<RS> responseEntityList = this.findAll();
        if (responseEntityList.isEmpty()) {
            throw new DeletionAllNotFoundException();
        } else {
            wrapper.deleteAll();
            return responseEntityList;
        }

    }

    public List<RS> findAll() {
        return convert(wrapper.findAll());
    }

    public RS findById(final Long id) {
        Optional<E> entityOptional = wrapper.findById(id);
        if (entityOptional.isPresent()) {
            return responseConverter.toDto(entityOptional.get());
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
                .map(responseConverter::toDto)
                .collect(Collectors.toSet());
    }

    protected Page<RS> convert(Page<E> entitySet) {
        return entitySet
                .map(responseConverter::toDto);
    }

    protected List<RS> convert(List<E> entityList) {
        return entityList.stream()
                .map(responseConverter::toDto)
                .collect(Collectors.toList());
    }

    protected abstract boolean entityAlreadyExistsSave(RQ requestDto);

    //Entity that must be updated, must be excluded from check
    protected abstract boolean entityAlreadyExistsUpdate(final Long id, RQ requestDto);


}
