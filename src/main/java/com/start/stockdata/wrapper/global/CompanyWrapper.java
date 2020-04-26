package com.start.stockdata.wrapper.global;

import com.start.stockdata.exception.exception.UserByIdNotFoundException;
import com.start.stockdata.identity.converter.request.RequestConverter;
import com.start.stockdata.identity.dto.request.CompanyFieldRequestDto;
import com.start.stockdata.identity.dto.request.CompanyRequestDto;
import com.start.stockdata.identity.model.Company;
import com.start.stockdata.identity.model.CompanyField;
import com.start.stockdata.repository.AbstractEntityRepo;
import com.start.stockdata.repository.CompanyRepo;
import com.start.stockdata.repository.projection.CompanyName;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static com.start.stockdata.util.SecurityContextUtil.getUserIdFromSecurityContext;

/**
 * Pay attention we can call repository.findAllByUserId(id)
 * because of CompanyRepo last parameter
 */
@Component
public class CompanyWrapper extends AbstractEntityDtoWrapper<
        Company,
        CompanyRequestDto,
        CompanyRepo
        >  {

    public CompanyWrapper(RequestConverter<Company, CompanyRequestDto> requestConverter, CompanyRepo repository) {
        super(requestConverter, repository);
    }

    public List<Company> findAllByUserId() {
        Optional<Long> optionalUserId = getUserIdFromSecurityContext();

        if (!optionalUserId.isPresent()) {
            throw new UserByIdNotFoundException();
        } else {
            return repository.findAllByUserId(optionalUserId.get());
        }

    }

    public List<Company> deleteAllCompanies() {
        Optional<Long> optionalUserId = getUserIdFromSecurityContext();

        if (!optionalUserId.isPresent()) {
            throw new UserByIdNotFoundException();
        } else {
            return repository.deleteByUserId(optionalUserId.get());
        }

    }

    /**
     *
     * @return set of company's names for specific user(user's id will be taken from token)
     */
    public Set<String> findAllNameByUserId() {
        Optional<Long> optionalUserId = getUserIdFromSecurityContext();

        if (!optionalUserId.isPresent()) {
            throw new UserByIdNotFoundException();
        } else {
            List<CompanyName> companyNames = repository.findByUserId(optionalUserId.get());
            return companyNames
                    .stream()
                    .map(CompanyName::getName)
                    .collect(Collectors.toSet());
        }

    }

    @Override
    public Company save(CompanyRequestDto companyRequestDto) {

        Optional<Long> optionalUserId = getUserIdFromSecurityContext();
        if (!optionalUserId.isPresent()) {
            throw new UserByIdNotFoundException();
        } else {
            Company company = requestConverter.toEntity(companyRequestDto);
            company.setUserId(optionalUserId.get());
            return repository.save(company);
        }

    }

}
