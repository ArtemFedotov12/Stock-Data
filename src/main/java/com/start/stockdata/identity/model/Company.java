package com.start.stockdata.identity.model;

import com.start.stockdata.util.enums.CompanyTypeAttributeConverter;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity(name = "Company")
@Table(name = "company")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
public class Company extends AbstractRemovableEntity {

    @Column(name = "name")
    private String name;

    //@Convert(converter = CompanyTypeAttributeConverter.class)
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "company_company_type",
            joinColumns = { @JoinColumn(name = "company_id") },
            inverseJoinColumns = { @JoinColumn(name = "company_type_id") })
    private Set<CompanyType> companyTypes;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="company_id")
    private Set<CompanyField> companyFields;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="company_id")
    private Set<CompanyFactor> companyFactors;

    @Column(name = "user_id")
    private Long userId;

}
