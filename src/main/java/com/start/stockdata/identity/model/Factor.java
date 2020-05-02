package com.start.stockdata.identity.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table//(name = "company_factor")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Getter
@Setter
public class Factor extends AbstractAttributeEntity {

    @Column(name = "short_name")
    String shortName;

    @Column(name = "asset")
    String asset;

    @Column(name = "display_name")
    String displayName;

    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY/*, cascade = CascadeType.ALL*/)
    private Company company;

}
