package com.start.stockdata.wrapper;

import com.start.stockdata.exception.exception.EntityByIdNotFoundException;
import com.start.stockdata.identity.converter.entity_to_dto.ResponseConverter;
import com.start.stockdata.identity.converter.request_to_entity.RequestConverter;
import com.start.stockdata.identity.dto.request.CompanyRequestDto;
import com.start.stockdata.identity.dto.response.CompanyResponseDto;
import com.start.stockdata.identity.model.Company;
import com.start.stockdata.repository.CompanyRepo;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.start.stockdata.util.SecurityContextUtil.getUserIdFromSecurityContext;

/**
 * Pay attention we can call repository.findAllByUserId(id)
 * because of CompanyRepo last parameter
 */
@Component
public class CompanyWrapper extends AbstractEntityDtoWrapper<
        Company,
        CompanyResponseDto,
        CompanyRequestDto,
        CompanyRepo
        > {

    public CompanyWrapper(ResponseConverter<Company, CompanyResponseDto> responseConverter,
                          RequestConverter<Company, CompanyRequestDto> requestConverter,
                          CompanyRepo repository) {

        super(responseConverter, requestConverter, repository);
    }

    public List<CompanyResponseDto> findAllByUserId() {
        Optional<Long> optionalUserId = getUserIdFromSecurityContext();

        if (!optionalUserId.isPresent()) {
            throw new EntityByIdNotFoundException();
        } else {
            return repository.findAllByUserId(optionalUserId.get())
                    .stream()
                    .map(responseConverter::toDto)
                    .collect(Collectors.toList());
        }

    }

    @Override
    public CompanyResponseDto save(CompanyRequestDto companyRequestDto) {

        Optional<Long> optionalUserId = getUserIdFromSecurityContext();

        if (!optionalUserId.isPresent()) {
            throw new EntityByIdNotFoundException();
        } else {
            Company company = requestConverter.toEntity(companyRequestDto);
            company.setUserId(optionalUserId.get());
            return responseConverter.toDto(repository.save(company));
        }


    }
}
