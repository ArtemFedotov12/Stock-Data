package com.start.stockdata.service;

import com.start.stockdata.exception.exception.DeletionEntityByIdNotFoundException;
import com.start.stockdata.exception.exception.EntityAlreadyExistsException;
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
        WR extends AbstractEntityDtoWrapper<?, RS, RQ, ?>> {

    protected final WR wrapper;

    public AbstractService(WR wrapper) {
        this.wrapper = wrapper;
    }

    public RS save(RQ requestDto) {
        if (entityAlreadyExistsSave(requestDto)) {
            throw new EntityAlreadyExistsException(requestDto);
        }
        return wrapper.save(requestDto);
    }

    public RS update(final Long id, RQ requestDto) {
        if (entityAlreadyExistsUpdate(id,requestDto)) {
            throw new EntityAlreadyExistsException(requestDto);
        }
        return wrapper.update(id, requestDto);
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

    public Long count(boolean includeDeleted) {
        return wrapper.count(includeDeleted);
    }

    /**
     *
     * @param id id entity to be fount
     * @throws com.start.stockdata.exception.exception.EntityByIdNotFoundException if such entity doesn't exist
     */
    private void checkIfEntityByIdExists(Long id) {
        Optional<RS> optional = Optional.ofNullable(findById(id));

    }

    protected abstract boolean entityAlreadyExistsSave(RQ requestDto);

    //Entity that must be updated, must be excluded from check
    protected abstract boolean entityAlreadyExistsUpdate(final Long id, RQ requestDto);


}
