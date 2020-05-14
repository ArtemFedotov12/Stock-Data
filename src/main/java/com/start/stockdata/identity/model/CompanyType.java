package com.start.stockdata.identity.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "CompanyType")
@Table(name = "company_type")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
public class CompanyType extends AbstractRemovableEntity {

    @Column(name = "type", unique = true, nullable = false)
    private String type;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "companyTypes")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Company> companies;

    public Set<Company> getCompanies() {
        return this.companies == null ? new HashSet<>() : this.companies;
    }

}
