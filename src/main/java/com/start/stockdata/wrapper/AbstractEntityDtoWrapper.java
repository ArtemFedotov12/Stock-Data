package com.start.stockdata.wrapper;

import com.start.stockdata.exception.exception.EntityByIdNotFoundException;
import com.start.stockdata.identity.converter.entity_to_dto.ResponseConverter;
import com.start.stockdata.identity.converter.request_to_entity.RequestConverter;
import com.start.stockdata.identity.dto.request.AbstractRequestDto;
import com.start.stockdata.identity.dto.response.AbstractResponseDto;
import com.start.stockdata.identity.model.AbstractEntity;
import com.start.stockdata.repository.AbstractEntityRepo;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class AbstractEntityDtoWrapper<
        E extends AbstractEntity,
        RS extends AbstractResponseDto,
        RQ extends AbstractRequestDto,
        R extends AbstractEntityRepo<E>
        > {

    protected final ResponseConverter<E, RS> responseConverter;
    protected final RequestConverter<E, RQ> requestConverter;
    protected final R repository;

    public AbstractEntityDtoWrapper(ResponseConverter<E, RS> responseConverter,
                                    RequestConverter<E, RQ> requestConverter,
                                    R repository) {
        this.responseConverter = responseConverter;
        this.requestConverter = requestConverter;
        this.repository = repository;
    }

    public RS save(RQ dto) {
        return responseConverter.toDto(repository.save(requestConverter.toEntity(dto)));
    }

    public RS update(final Long id, RQ dto) {
        E entity = requestConverter.toEntity(dto);
        entity.setId(id);
        return responseConverter.toDto(repository.save(entity));
    }

    public RS findById(Long id) {
        Optional<E> entity = repository.findById(id);
        if (entity.isPresent()) {
            return responseConverter.toDto(entity.get());
        } else{
            throw  new EntityByIdNotFoundException();
        }
    }

    public List<RS> findAll() {
        return convert(repository.findAll());
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Long count(boolean includeDeleted) {
        return repository.count();
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

}
