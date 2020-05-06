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
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "company_company_type",
            joinColumns = { @JoinColumn(name = "company_id") },
            inverseJoinColumns = { @JoinColumn(name = "company_type_id") })
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
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST,CascadeType.REMOVE, CascadeType.MERGE},
            orphanRemoval = true)
    @JoinColumn(name="company_id")
    private Set<Field> fields;

    // Nothing must be deleted from db. Just set removal_date
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST,CascadeType.REMOVE, CascadeType.MERGE},
            orphanRemoval = true)
    @JoinColumn(name="company_id")
    private Set<Factor> factors;

    @Column(name = "user_id")
    private Long userId;

    public void addField(Field field) {
        fields.add(field);
        field.setCompany(this);
    }

    public void removeField(Field field) {
        fields.remove(field);
        //field.setCompany(null);
    }

}
