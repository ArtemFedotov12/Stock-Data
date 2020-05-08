package com.start.stockdata.identity.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "Company")
@Table(name = "company")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Getter
@Setter
public class Company extends AbstractRemovableEntity {

    @Column(name = "name", nullable = false)
    private String name;

    //@Convert(converter = CompanyTypeAttributeConverter.class)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "company_company_type",
            joinColumns = {@JoinColumn(name = "company_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "company_type_id", referencedColumnName = "id")})
    private Set<CompanyType> companyTypes;

    /*
    Was problem if we (mappedBy = "company") and remove @JoinColumn(name="company_id") from here
    and put @JoinColumn(name="company_id") to 'Field' entity(it will be responsible for this bound),
    then when save 'Company' entity -> company_id will be null in 'field' table
     https://stackoverflow.com/questions/43357413/parent-id-null-in-onetomany-mapping-jpa/43361935
    */
    // We need CascadeType.MERGE, for CompanyFieldWrapper.saveField()
    // There 'company' in detached state(it has id from db) and we add new field to company
    // An entity that passed through such serialization/deserialization will appear in a detached state.
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE},
            orphanRemoval = true)
    @JoinColumn(name = "company_id")
    private Set<Field> fields;

    // Nothing must be deleted from db. Just set removal_date
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE},
            orphanRemoval = true)
    @JoinColumn(name = "company_id")
    private Set<Factor> factors;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    //unchecked NullPointer
    public void addField(Field field) {
        this.getFields().add(field);
        field.setCompany(this);
    }

    //unchecked NullPointer
    public void removeField(Field field) {
        field.setCompany(null);
        this.getFields().remove(field);
    }

    //unchecked NullPointer
    public void addFactor(Factor factor) {
        factor.setCompany(this);
        this.getFactors().add(factor);
    }

    //unchecked NullPointer
    public void removeFactor(Factor factor) {
        factor.setCompany(null);
        this.getFactors().remove(factor);
    }

    public void addCompanyType(CompanyType companyType) {
        this.getCompanyTypes().add(companyType);
    }

    public void removeCompanyType(CompanyType companyType) {
        this.getCompanyTypes().remove(companyType);
    }

    public void removeAllCompanyTypes() {
        this.getCompanyTypes().clear();
    }

    public Set<CompanyType> getCompanyTypes() {
       return this.companyTypes == null ? new HashSet<>() : this.companyTypes;
    }

    public Set<Field> getFields() {
        return this.fields == null ? new HashSet<>() : this.fields;
    }

    public Set<Factor> getFactors() {
        return this.factors == null ? new HashSet<>() : this.factors;
    }

}
