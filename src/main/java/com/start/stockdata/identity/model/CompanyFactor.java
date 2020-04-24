package com.start.stockdata.identity.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "company_factor")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Getter
@Setter
public class CompanyFactor extends AbstractRemovableEntity {

    @Column(name = "short_name")
    String shortName;

    @Column(name = "asset")
    String asset;

    @Column(name = "display_name")
    String displayName;

}
