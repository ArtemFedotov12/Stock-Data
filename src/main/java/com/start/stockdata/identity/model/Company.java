package com.start.stockdata.identity.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "Company")
@Table(name = "company")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Getter
@Setter
public class Company extends AbstractRemovableEntity {

    @Column(name = "name")
    private String name;

    //@Convert(converter = CompanyTypeAttributeConverter.class)
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "company_company_type",
            joinColumns = { @JoinColumn(name = "company_id") },
            inverseJoinColumns = { @JoinColumn(name = "company_type_id") })
    private Set<CompanyType> companyTypes;

    // Nothing must be deleted from db. Just set removal_date
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "company")
    private Set<CompanyField> companyFields;

    // Nothing must be deleted from db. Just set removal_date
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="company_id")
    private Set<CompanyFactor> companyFactors;

    @Column(name = "user_id")
    private Long userId;

}
