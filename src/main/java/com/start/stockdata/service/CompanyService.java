package com.start.stockdata.service;

import com.start.stockdata.config.userDetails.StockUserInfo;
import com.start.stockdata.identity.dto.CompanyDto;
import com.start.stockdata.wrapper.CompanyWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyWrapper companyWrapper;

    public CompanyDto save(CompanyDto companyDto) {
       return companyWrapper.save(companyDto);
    }

    public List<CompanyDto> findAllByUserId() {
        Optional<Long> optionalUserId = getUserIdFromSecurityContext();
        if (!optionalUserId.isPresent()) {
            throw  new RuntimeException("ss");
        } else {
            return  companyWrapper.findAllByUserId(optionalUserId.get());
        }

    }

    private Optional<Long> getUserIdFromSecurityContext() {
        return Optional.ofNullable(
                    ((StockUserInfo) SecurityContextHolder
                    .getContext()
                    .getAuthentication()
                    .getPrincipal())
                    .getUserId()
            );
    }


}
