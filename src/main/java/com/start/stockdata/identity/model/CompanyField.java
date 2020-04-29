package com.start.stockdata.identity.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "CompanyField")
@Table(name = "company_field")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Getter
@Setter
public class CompanyField extends AbstractAttributeEntity{

    @EqualsAndHashCode.Exclude
    @Column(name = "short_name")
    private String shortName;

    @EqualsAndHashCode.Exclude
    @Column(name = "asset")
    private String asset;

    @Column(name = "display_name")
    private String displayName;

    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="company_id")
    private Company company;


}
