package com.start.stockdata.identity.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "CompanyField")
@Table(name = "company_field")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Getter
@Setter
public class CompanyField extends AbstractRemovableEntity{

    @Column(name = "shortName")
    String shortName;
    @Column(name = "asset")
    String asset;
    @Column(name = "display_name")
    String displayName;

}
