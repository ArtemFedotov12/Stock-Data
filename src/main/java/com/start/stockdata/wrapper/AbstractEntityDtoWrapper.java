package com.start.stockdata.wrapper;

import com.start.stockdata.identity.converter.entity_to_dto.EntityDtoConverter;
import com.start.stockdata.identity.dto.response.AbstractResponseDto;
import com.start.stockdata.identity.model.AbstractEntity;
import com.start.stockdata.repository.AbstractEntityRepo;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class AbstractEntityDtoWrapper<
        E extends AbstractEntity,
        RS extends AbstractResponseDto,
        R extends AbstractEntityRepo<E>
        > {

    protected final EntityDtoConverter<E, RS> converter;
    protected final R repository;

    public AbstractEntityDtoWrapper(EntityDtoConverter<E, RS> converter, R repository) {
        this.converter = converter;
        this.repository = repository;
    }

    public RS save(RS dto) {
        return converter.toDto(repository.save(converter.toEntity(dto)));
    }

    public void update(RS dto) {
        repository.save(converter.toEntity(dto));
    }

    public RS findById(Long id) {
        return repository.findById(id).map(converter::toDto).orElse(null);
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

}
