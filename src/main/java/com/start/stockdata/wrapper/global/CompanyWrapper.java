package com.start.stockdata.wrapper.global;

import com.start.stockdata.exception.exception.UserIdFromSecurityContextNotFoundException;
import com.start.stockdata.identity.model.Company;
import com.start.stockdata.identity.model.Field;
import com.start.stockdata.repository.CompanyRepo;
import com.start.stockdata.repository.projection.CompanyName;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static com.start.stockdata.util.SecurityContextUtil.getUserIdFromSecurityContext;

/**
 * Pay attention we can call companyRepo.findAllByUserId(id)
 * because of CompanyRepo last parameter
 */
@Component
public class CompanyWrapper implements GlobalWrapper<
        Company,
        Long
        > {

    private final CompanyRepo companyRepo;

    public CompanyWrapper(CompanyRepo companyRepo) {
        this.companyRepo = companyRepo;
    }

    @Override
    public Company save(Company entity) {
        return companyRepo.save(entity);
    }

    @Override
    public Company update(Company entity) {
        return companyRepo.save(entity);
    }

    @Override
    public Optional<Company> findById(Long id) {
        return companyRepo.findById(id);
    }

    @Override
    public List<Company> findAll() {

        Optional<Long> userIdFromSecurityContext = getUserIdFromSecurityContext();
        if (userIdFromSecurityContext.isPresent()) {
            return companyRepo.findAllByUserId(userIdFromSecurityContext.get());
        } else {
            throw new UserIdFromSecurityContextNotFoundException();
        }

    }

    @Override
    public void deleteById(Long id) {
        companyRepo.deleteById(id);
    }

    @Override
    public void deleteAll() {

        Optional<Long> userIdFromSecurityContext = getUserIdFromSecurityContext();

        if (userIdFromSecurityContext.isPresent()) {
            companyRepo.deleteByUserId(userIdFromSecurityContext.get());
        } else {
            throw new UserIdFromSecurityContextNotFoundException();
        }

    }

    @Override
    public Long count(boolean includeDeleted) {

        Optional<Long> userIdFromSecurityContext = getUserIdFromSecurityContext();
        if (userIdFromSecurityContext.isPresent()) {
            return companyRepo.countByUserId(userIdFromSecurityContext.get());
        } else {
            throw new UserIdFromSecurityContextNotFoundException();
        }

    }



    /**
     * @return set of company's names for specific user(user's id will be taken from token)
     */
    public Set<String> findAllNamesByUserId(Long userId) {

        List<CompanyName> companyNames = companyRepo.findByUserId(userId);
        return companyNames
                .stream()
                .map(CompanyName::getName)
                .collect(Collectors.toSet());

    }

    public Optional<Company> findByName(String name) {
        return companyRepo.findByName(name);
    }


}
