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
    /*
     CascadeType.REMOVE strictly forbidden for @ManyToMany!!!!!!!!!
     No Cascade type here
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "company_company_type",
            joinColumns = {@JoinColumn(name = "company_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "company_type_id", referencedColumnName = "id")})
    private Set<CompanyType> companyTypes = new HashSet<>();


    /*
     We need CascadeType.MERGE, for CompanyFieldWrapper.saveField()
     There 'company' in detached state(it has id from db) and we add new field to company
     An entity that passed through such serialization/deserialization will appear in a detached state.

     CascadeType.REMOVE is allowed here.But will be a lot of queries. One query for one field.
     10 fields -> 10 deletion queries.

     CascadeType.DETACH I think we need here too, if parent is detached, children also will be.
     */
    @OneToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE, CascadeType.DETACH},
            orphanRemoval = true,
            mappedBy = "company")
    private Set<Field> fields = new HashSet<>();


    /*
     We need CascadeType.MERGE, for CompanyFieldWrapper.saveField()
     There 'company' in detached state(it has id from db) and we add new field to company
     An entity that passed through such serialization/deserialization will appear in a detached state.

     CascadeType.REMOVE is allowed here.But will be a lot of queries. One query for one field.
     10 fields -> 10 deletion queries.

     CascadeType.DETACH I think we need here too, if parent is detached, children also will be.
     */
    @OneToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE, CascadeType.DETACH},
            orphanRemoval = true,
            mappedBy = "company")
    private Set<Factor> factors = new HashSet<>();

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

    public void removeAllFields() {
        this.getFields().forEach(item -> item.setCompany(null));
        this.getFields().clear();
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

    public void removeAllFactors() {

    }

    public void addCompanyType(CompanyType companyType) {
        companyType.getCompanies().add(this);
        this.getCompanyTypes().add(companyType);
    }

    public void removeCompanyType(CompanyType companyType) {
        companyType.getCompanies().remove(this);
        this.getCompanyTypes().remove(companyType);
    }

    public void removeAllCompanyTypes() {
        this.getCompanyTypes()
                .forEach(item -> item.getCompanies().remove(this));
        this.getCompanyTypes().clear();
    }


}
