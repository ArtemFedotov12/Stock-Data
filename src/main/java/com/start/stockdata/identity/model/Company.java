package com.start.stockdata.identity.model;

import com.start.stockdata.util.enums.CompanyType;
import com.start.stockdata.util.enums.CompanyTypeAttributeConverter;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity(name = "Company")
@Table(name = "company")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
public class Company extends AbstractRemovableEntity{

    @NotBlank(message = "Please provide a name")
    @Column(name = "name")
    private String name;

    @NotNull(message = "Please provide company's type")
    @Convert(converter = CompanyTypeAttributeConverter.class)
    @Column(name = "company_type")
    private CompanyType companyType;

    @NotNull(message = "Please specify 'id' of the company's owner")
    @Column(name = "user_id")
    private Long userId;

}
