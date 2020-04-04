package com.start.stockdata.wrapper;

import com.start.stockdata.identity.converter.IConverter;
import com.start.stockdata.identity.model.AbstractEntity;
import com.start.stockdata.repository.AbstractEntityRepository;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class AbstractEntityDtoWrapper<E extends AbstractEntity, D> {

    protected final IConverter<E, D> converter;
    protected final AbstractEntityRepository<E> repository;

    public AbstractEntityDtoWrapper(IConverter<E, D> converter, AbstractEntityRepository<E> repository) {
        this.converter = converter;
        this.repository = repository;
    }

    public D save(D dto) {
        return converter.toDto(repository.save(converter.toEntity(dto)));
    }

    public void update(D dto) {
        repository.save(converter.toEntity(dto));
    }

    public D findById(Long id) {
        return repository.findById(id).map(converter::toDto).orElse(null);
    }

    public List<D> findAll() {
        return convert(repository.findAll());
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Long count(boolean includeDeleted) {
        return repository.count();
    }

    protected Set<D> convert(Set<E> entitySet) {
        return entitySet.stream()
                .map(converter::toDto)
                .collect(Collectors.toSet());
    }

    protected Page<D> convert(Page<E> entitySet) {
        return entitySet
                .map(converter::toDto);
    }

    protected List<D> convert(List<E> entityList) {
        return entityList.stream()
                .map(converter::toDto)
                .collect(Collectors.toList());
    }

}
