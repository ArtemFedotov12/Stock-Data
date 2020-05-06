package com.start.stockdata.identity.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Entity(name = "CompanyType")
@Table(name = "company_type")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
public class CompanyType extends AbstractRemovableEntity {

    @EqualsAndHashCode.Exclude
    @Column(name = "type", unique = true, nullable = false)
    private String type;

}
